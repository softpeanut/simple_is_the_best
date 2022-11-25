package karrotpay.assignment.igloomall.persistence.coupon.mapper

import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.global.annotation.Mapper
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.repository.CouponJpaRepository
import karrotpay.assignment.igloomall.persistence.user.repository.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull

@Mapper
class CouponHistoryMapper(
    private val couponRepository: CouponJpaRepository,
    private val userRepository: UserJpaRepository
) {

    fun toDomain(entity: CouponHistoryJpaEntity?): CouponHistory? {
        return entity?.run {
            CouponHistory(
                couponId = id.couponId,
                userId = id.userId,
                issuedAt = issuedAt,
                usedAt = usedAt
            )
        }
    }

    fun toEntity(domain: CouponHistory): CouponHistoryJpaEntity {
        val coupon = couponRepository.findByIdOrNull(domain.couponId)
        val user = userRepository.findByIdOrNull(domain.userId)

        return domain.run {
            CouponHistoryJpaEntity(
                couponId = couponId,
                userId = userId,
                coupon = coupon,
                user = user,
                issuedAt = domain.issuedAt,
                usedAt = domain.usedAt
            )
        }
    }
}