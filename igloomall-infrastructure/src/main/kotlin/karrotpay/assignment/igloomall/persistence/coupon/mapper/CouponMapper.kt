package karrotpay.assignment.igloomall.persistence.coupon.mapper

import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.global.annotation.Mapper
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponJpaEntity

@Mapper
class CouponMapper {

    fun toDomain(entity: CouponJpaEntity?): Coupon? {
        return entity?.run {
            Coupon(
                id = id,
                code = code,
                name = name,
                amount = amount,
                totalQuantity = totalQuantity,
                issuedQuantity = issuedQuantity
            )
        }
    }

    fun toEntity(domain: Coupon): CouponJpaEntity {
        return domain.run {
            CouponJpaEntity(
                id = id,
                code = code,
                name = name,
                amount = amount,
                totalQuantity = totalQuantity,
                issuedQuantity = issuedQuantity
            )
        }
    }
}