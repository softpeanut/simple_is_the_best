package karrotpay.assignment.igloomall.domain.coupon.error

import karrotpay.assignment.igloomall.common.error.BaseException

sealed class CouponExceptions(
    override val status: Int,
    override val message: String
) : BaseException(status, message) {

    class AlreadyIssued(message: String = ALREADY_ISSUED_COUPON) : CouponExceptions(409, message)

    class AlreadyUsed(message: String = ALREADY_USED_COUPON) : CouponExceptions(409, message)

    class NoLeft(message: String = NO_LEFT_COUPON) : CouponExceptions(404, message)

    class NotFound(message: String = NOT_FOUND) : CouponExceptions(404, message)

    class NotIssued(message: String = NOT_ISSUED) : CouponExceptions(404, message)

    companion object {
        private const val ALREADY_ISSUED_COUPON = "Coupon already issued"
        private const val ALREADY_USED_COUPON = "Coupon already used"
        private const val NO_LEFT_COUPON = "Coupon no left"
        private const val NOT_FOUND = "Coupon not found"
        private const val NOT_ISSUED = "Coupon not issued"
    }
}
