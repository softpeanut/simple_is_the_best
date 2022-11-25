package karrotpay.assignment.igloomall.domain.coupon.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.coupon.dto.IssueCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.exception.CouponAlreadyIssuedException
import karrotpay.assignment.igloomall.domain.coupon.exception.CouponNotFoundException
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.CommandCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import java.time.LocalDateTime

@UseCase
class IssueCoupon(
    private val queryCouponPort: QueryCouponPort,
    private val commandCouponPort: CommandCouponPort
) {

    fun execute(userId: Long, couponCode: String): IssueCouponResponse {
        val coupon = queryCouponPort.getCoupon(couponCode) ?: throw CouponNotFoundException

        if (queryCouponPort.isAlreadyGotCoupon(coupon.code, userId)) {
            throw CouponAlreadyIssuedException
        }

        commandCouponPort.saveCoupon(
            coupon.issue()
        )

        val couponHistory = CouponHistory(
            couponId = coupon.id,
            userId = userId,
            issuedAt = LocalDateTime.now()
        )

        val (issuedCoupon, issuedCouponHistory) = commandCouponPort.saveCouponHistory(couponHistory)

        return IssueCouponResponse(
            couponId = issuedCoupon.id,
            couponCode = issuedCoupon.code,
            couponIssuedAt = issuedCouponHistory.issuedAt
        )
    }
}