package karrotpay.assignment.igloomall.domain.coupon.dto

import java.time.LocalDateTime

data class IssueCouponResponse(
    val couponId: Long,
    val couponCode: String,
    val couponIssuedAt: LocalDateTime
)