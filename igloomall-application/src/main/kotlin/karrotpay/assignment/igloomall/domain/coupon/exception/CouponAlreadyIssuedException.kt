package karrotpay.assignment.igloomall.domain.coupon.exception

import karrotpay.assignment.igloomall.common.error.BaseException
import karrotpay.assignment.igloomall.domain.coupon.error.CouponError

object CouponAlreadyIssuedException : BaseException(
    CouponError.COUPON_ALREADY_ISSUED
)