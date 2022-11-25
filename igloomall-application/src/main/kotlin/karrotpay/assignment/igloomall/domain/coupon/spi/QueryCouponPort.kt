package karrotpay.assignment.igloomall.domain.coupon.spi

import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.vo.CouponVO

interface QueryCouponPort {

    fun isAlreadyGotCoupon(couponCode: String, userId: Long): Boolean

    fun getCouponHistory(userId: Long, couponId: Long): CouponHistory?

    fun getCoupon(couponCode: String): Coupon?

    fun getAllCoupons(): List<Coupon>

    fun getCouponHistoriesByUser(userId: Long): List<CouponVO>

    fun getCouponHistoriesByCouponId(couponId: Long): List<CouponVO>

}