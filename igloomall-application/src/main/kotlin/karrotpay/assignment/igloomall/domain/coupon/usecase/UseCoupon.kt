package karrotpay.assignment.igloomall.domain.coupon.usecase

import karrotpay.assignment.igloomall.common.annotation.WritableUseCase
import karrotpay.assignment.igloomall.domain.coupon.dto.UseCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.exception.CouponNotIssuedException
import karrotpay.assignment.igloomall.domain.coupon.spi.CommandCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort

@WritableUseCase
class UseCoupon(
    private val queryCouponPort: QueryCouponPort,
    private val commandCouponPort: CommandCouponPort
) {

    fun execute(userId: Long, couponId: Long): UseCouponResponse {
        val couponHistory = queryCouponPort.getCouponHistory(userId, couponId)
            ?: throw CouponNotIssuedException

        val (usedCoupon, usedCouponHistory) = commandCouponPort.saveCouponHistory(
            couponHistory.use()
        )

        return UseCouponResponse(
            couponId = usedCoupon.id,
            couponCode = usedCoupon.code,
            couponUsedAt = usedCouponHistory.usedAt!!,
        )
    }
}