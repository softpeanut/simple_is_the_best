package karrotpay.assignment.igloomall.domain.coupon.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.coupon.dto.FindCouponHistoriesElement
import karrotpay.assignment.igloomall.domain.coupon.dto.FindCouponHistoriesResponse
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort

@UseCase
class FindCouponHistories(
    private val queryCouponPort: QueryCouponPort
) {

    fun execute(couponId: Long): FindCouponHistoriesResponse {
        val histories = queryCouponPort.getCouponHistoriesByCouponId(couponId)

        val response = histories.map {
            val coupon = it.coupon
            val couponHistory = it.couponHistory

            FindCouponHistoriesElement(
                userId = couponHistory.userId,
                couponId = coupon.id,
                couponCode = coupon.code,
                couponIssuedAt = couponHistory.issuedAt,
                isUsedCoupon = couponHistory.isUsed,
                couponUsedAt = couponHistory.usedAt
            )
        }

        return FindCouponHistoriesResponse(response)
    }
}