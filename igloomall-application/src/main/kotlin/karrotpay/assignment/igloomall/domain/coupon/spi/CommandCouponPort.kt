package karrotpay.assignment.igloomall.domain.coupon.spi

import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.vo.CouponVO

interface CommandCouponPort {

    fun saveCouponHistory(couponHistory: CouponHistory): CouponVO
    fun saveCoupon(coupon: Coupon): Coupon

}