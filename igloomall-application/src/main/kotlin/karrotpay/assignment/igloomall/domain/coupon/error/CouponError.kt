package karrotpay.assignment.igloomall.domain.coupon.error

import karrotpay.assignment.igloomall.common.error.ErrorProperty

enum class CouponError(
    override val status: Int,
    override val message: String
) : ErrorProperty {

    COUPON_NOT_FOUND(404, "Coupon not found"),
    COUPON_NO_LEFT(404, "Coupon no left"),
    COUPON_NOT_ISSUED(404, "Coupon not issued"),

    COUPON_ALREADY_ISSUED(409, "Coupon already issued"),
    COUPON_ALREADY_USED(409, "Coupon already used")

}