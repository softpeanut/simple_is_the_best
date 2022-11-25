package karrotpay.assignment.igloomall.domain.coupon.dto

data class FindAllCouponsResponse(
    val coupons: List<FindAllCouponsElement>
)

data class FindAllCouponsElement(
    val couponCode: String,
    val couponName: String,
    val couponAmount: Long,
    val totalQuantity: Long,
    val remainingQuantity: Long
)