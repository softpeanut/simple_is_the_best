package karrotpay.assignment.igloomall.persistence.coupon

import com.querydsl.jpa.impl.JPAQueryFactory
import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.CommandCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.vo.CouponVO
import karrotpay.assignment.igloomall.persistence.coupon.mapper.CouponHistoryMapper
import karrotpay.assignment.igloomall.persistence.coupon.mapper.CouponMapper
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntityId
import karrotpay.assignment.igloomall.persistence.coupon.model.QCouponHistoryJpaEntity.couponHistoryJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.model.QCouponJpaEntity.couponJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.repository.CouponHistoryJpaRepository
import karrotpay.assignment.igloomall.persistence.coupon.repository.CouponJpaRepository
import karrotpay.assignment.igloomall.persistence.coupon.repository.vo.QCouponPersistenceVO
import org.springframework.stereotype.Component

@Component
class CouponPersistenceAdapter(
    private val couponMapper: CouponMapper,
    private val couponHistoryMapper: CouponHistoryMapper,
    private val couponRepository: CouponJpaRepository,
    private val couponHistoryRepository: CouponHistoryJpaRepository,
    private val queryFactory: JPAQueryFactory
) : QueryCouponPort, CommandCouponPort {
    override fun isAlreadyGotCoupon(
        couponCode: String,
        userId: Long
    ) = couponHistoryRepository.existsByCouponCodeAndUserId(couponCode, userId)

    override fun getCouponHistory(userId: Long, couponId: Long): CouponHistory? {
        val couponHistory = couponHistoryRepository.findCouponHistoryJpaEntityById(
            CouponHistoryJpaEntityId(userId = userId, couponId = couponId)
        )

        return couponHistory?.run { couponHistoryMapper.toDomain(this) }
    }

    override fun getCoupon(couponCode: String) = couponRepository.findByCode(
        couponCode
    )?.run { couponMapper.toDomain(this) }

    override fun getAllCoupons() = couponRepository.findAll().map {
        couponMapper.toDomain(it)!!
    }

    override fun getCouponHistoriesByUser(userId: Long): List<CouponVO> {
        return queryFactory
            .select(
                QCouponPersistenceVO(
                    couponJpaEntity,
                    couponHistoryJpaEntity
                )
            )
            .from(couponHistoryJpaEntity)
            .join(couponHistoryJpaEntity.coupon, couponJpaEntity)
            .where(couponHistoryJpaEntity.user.id.eq(userId))
            .orderBy(couponHistoryJpaEntity.issuedAt.desc())
            .fetch()
            .map {
                CouponVO(
                    coupon = couponMapper.toDomain(it.coupon)!!,
                    couponHistory = couponHistoryMapper.toDomain(it.couponHistory)!!
                )
            }
    }

    override fun getCouponHistoriesByCouponId(couponId: Long): List<CouponVO> {
        return queryFactory
            .select(
                QCouponPersistenceVO(
                    couponJpaEntity,
                    couponHistoryJpaEntity
                )
            )
            .from(couponHistoryJpaEntity)
            .join(couponHistoryJpaEntity.coupon, couponJpaEntity)
            .where(couponJpaEntity.id.eq(couponId))
            .orderBy(couponHistoryJpaEntity.issuedAt.desc())
            .fetch()
            .map {
                CouponVO(
                    coupon = couponMapper.toDomain(it.coupon)!!,
                    couponHistory = couponHistoryMapper.toDomain(it.couponHistory)!!
                )
            }
    }

    override fun saveCouponHistory(couponHistory: CouponHistory): CouponVO {
        val issuedCouponHistory = couponHistoryRepository.save(
            couponHistoryMapper.toEntity(couponHistory)
        )

        return issuedCouponHistory.run {
            CouponVO(
                coupon = couponMapper.toDomain(this.coupon)!!,
                couponHistory = couponHistoryMapper.toDomain(this)!!
            )
        }
    }

    override fun saveCoupon(coupon: Coupon) = couponRepository.save(
        couponMapper.toEntity(coupon)
    ).run { couponMapper.toDomain(this)!! }
}