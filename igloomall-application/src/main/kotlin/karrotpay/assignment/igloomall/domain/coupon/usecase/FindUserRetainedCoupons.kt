package karrotpay.assignment.igloomall.domain.coupon.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.coupon.dto.FindUserRetainedCouponsElement
import karrotpay.assignment.igloomall.domain.coupon.dto.FindUserRetainedCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort

@UseCase
class FindUserRetainedCoupons(
    private val queryCouponPort: QueryCouponPort
) {

    fun execute(userId: Long): FindUserRetainedCouponsResponse {
        val histories = queryCouponPort.getCouponHistoriesByUser(userId)

        val response = histories.map {
            val coupon = it.coupon
            val couponHistory = it.couponHistory

            FindUserRetainedCouponsElement(
                couponId = coupon.id,
                couponCode = coupon.code,
                couponIssuedAt = couponHistory.issuedAt,
                isUsedCoupon = couponHistory.isUsed,
                couponUsedAt = couponHistory.usedAt
            )
        }

        return FindUserRetainedCouponsResponse(response)
    }
}