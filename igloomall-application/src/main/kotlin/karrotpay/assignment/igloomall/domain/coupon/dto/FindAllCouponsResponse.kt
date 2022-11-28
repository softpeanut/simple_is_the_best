package karrotpay.assignment.igloomall.domain.coupon.dto

import java.io.Serializable

data class FindAllCouponsResponse(
    val coupons: List<FindAllCouponsElement>
) : Serializable

data class FindAllCouponsElement(
    val couponCode: String,
    val couponName: String,
    val couponAmount: Long,
    val totalQuantity: Long,
    val remainingQuantity: Long
) : Serializable