package karrotpay.assignment.igloomall.persistence.coupon

import com.querydsl.jpa.impl.JPAQueryFactory
import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.CouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.vo.CouponVO
import karrotpay.assignment.igloomall.global.annotation.PersistenceAdapter
import karrotpay.assignment.igloomall.persistence.coupon.mapper.CouponHistoryMapper
import karrotpay.assignment.igloomall.persistence.coupon.mapper.CouponMapper
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntityId
import karrotpay.assignment.igloomall.persistence.coupon.model.QCouponHistoryJpaEntity.couponHistoryJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.model.QCouponJpaEntity.couponJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.repository.CouponHistoryJpaRepository
import karrotpay.assignment.igloomall.persistence.coupon.repository.CouponJpaRepository
import karrotpay.assignment.igloomall.persistence.coupon.repository.vo.QCouponPersistenceVO
import org.springframework.data.repository.findByIdOrNull

@PersistenceAdapter
class CouponPersistenceAdapter(
    private val couponMapper: CouponMapper,
    private val couponHistoryMapper: CouponHistoryMapper,
    private val couponRepository: CouponJpaRepository,
    private val couponHistoryRepository: CouponHistoryJpaRepository,
    private val queryFactory: JPAQueryFactory
) : CouponPort {
    override fun isAlreadyGotCoupon(
        couponCode: String,
        userId: Long
    ) = couponHistoryRepository.existsByCouponCodeAndUserId(couponCode, userId)

    override fun getCouponHistory(userId: Long, couponId: Long) = couponHistoryRepository.findByIdOrNull(
        CouponHistoryJpaEntityId(userId = userId, couponId = couponId)
    )?.let { couponHistoryMapper.toDomain(it) }

    override fun getCoupon(couponCode: String) = couponRepository.findByCode(
        couponCode
    )?.let { couponMapper.toDomain(it) }

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

        return issuedCouponHistory.let {
            CouponVO(
                coupon = couponMapper.toDomain(it.coupon)!!,
                couponHistory = couponHistoryMapper.toDomain(it)!!
            )
        }
    }

    override fun saveCoupon(coupon: Coupon) = couponRepository.save(
        couponMapper.toEntity(coupon)
    ).let { couponMapper.toDomain(it)!! }
}