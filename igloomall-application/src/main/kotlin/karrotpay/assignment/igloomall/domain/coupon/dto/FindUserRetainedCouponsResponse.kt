package karrotpay.assignment.igloomall.domain.coupon.dto

import java.io.Serializable
import java.time.LocalDateTime

data class FindUserRetainedCouponsResponse(
    val coupons: List<FindUserRetainedCouponsElement>
) : Serializable

data class FindUserRetainedCouponsElement(
    val couponId: Long,
    val couponCode: String,
    val couponIssuedAt: LocalDateTime,
    val isUsedCoupon: Boolean,
    val couponUsedAt: LocalDateTime?
) : Serializable