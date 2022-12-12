package karrotpay.assignment.igloomall.domain.coupon.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.coupon.dto.IssueCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.error.CouponExceptions
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.CommandCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort

@UseCase
class IssueCoupon(
    private val queryCouponPort: QueryCouponPort,
    private val commandCouponPort: CommandCouponPort
) {

    fun execute(userId: Long, couponCode: String): IssueCouponResponse {
        val coupon = queryCouponPort.getCoupon(couponCode) ?: throw CouponExceptions.NotFound()

        if (queryCouponPort.isAlreadyGotCoupon(coupon.code, userId)) {
            throw CouponExceptions.AlreadyIssued()
        }

        commandCouponPort.saveCoupon(
            coupon.issue()
        )

        val couponHistory = CouponHistory.of(couponId = coupon.id, userId = userId)

        val (issuedCoupon, issuedCouponHistory) = commandCouponPort.saveCouponHistory(couponHistory)

        return IssueCouponResponse(
            couponId = issuedCoupon.id,
            couponCode = issuedCoupon.code,
            couponIssuedAt = issuedCouponHistory.issuedAt
        )
    }
}