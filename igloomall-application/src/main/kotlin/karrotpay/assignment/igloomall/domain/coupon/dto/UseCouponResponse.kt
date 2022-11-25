package karrotpay.assignment.igloomall.domain.coupon.dto

import java.time.LocalDateTime

data class UseCouponResponse(
    val couponId: Long,
    val couponCode: String,
    val couponUsedAt: LocalDateTime
)