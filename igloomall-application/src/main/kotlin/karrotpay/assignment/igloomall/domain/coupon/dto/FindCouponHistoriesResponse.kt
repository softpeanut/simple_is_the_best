package karrotpay.assignment.igloomall.domain.coupon.dto

import java.io.Serializable
import java.time.LocalDateTime

data class FindCouponHistoriesResponse(
    val coupons: List<FindCouponHistoriesElement>
) : Serializable

data class FindCouponHistoriesElement(
    val userId: Long,
    val couponId: Long,
    val couponCode: String,
    val couponIssuedAt: LocalDateTime,
    val isUsedCoupon: Boolean,
    val couponUsedAt: LocalDateTime?
) : Serializable