package karrotpay.assignment.igloomall.persistence.coupon.repository.vo

import com.querydsl.core.annotations.QueryProjection
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponJpaEntity

data class CouponPersistenceVO @QueryProjection constructor(
    val coupon: CouponJpaEntity,
    val couponHistory: CouponHistoryJpaEntity
)
