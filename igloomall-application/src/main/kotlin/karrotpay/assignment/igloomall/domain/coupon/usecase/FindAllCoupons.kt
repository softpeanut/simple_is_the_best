package karrotpay.assignment.igloomall.domain.coupon.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.coupon.dto.FindAllCouponsElement
import karrotpay.assignment.igloomall.domain.coupon.dto.FindAllCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort

@UseCase
class FindAllCoupons(
    private val queryCouponPort: QueryCouponPort
) {

    fun execute(): FindAllCouponsResponse {
        val coupons = queryCouponPort.getAllCoupons()

        val response = coupons.map {
            FindAllCouponsElement(
                couponCode = it.code,
                couponName = it.name,
                couponAmount = it.amount,
                totalQuantity = it.totalQuantity,
                remainingQuantity = it.calculateRemainingQuantity()
            )
        }

        return FindAllCouponsResponse(response)
    }
}