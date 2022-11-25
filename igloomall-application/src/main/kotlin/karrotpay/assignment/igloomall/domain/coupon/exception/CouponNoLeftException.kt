package karrotpay.assignment.igloomall.domain.coupon.exception

import karrotpay.assignment.igloomall.common.error.BaseException
import karrotpay.assignment.igloomall.domain.coupon.error.CouponError

object CouponNoLeftException : BaseException(
    CouponError.COUPON_NO_LEFT
)