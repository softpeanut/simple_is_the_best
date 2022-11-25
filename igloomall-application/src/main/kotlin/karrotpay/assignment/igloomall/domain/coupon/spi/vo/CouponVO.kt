package karrotpay.assignment.igloomall.domain.coupon.spi.vo

import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory

data class CouponVO(
    val coupon: Coupon,
    val couponHistory: CouponHistory
)
