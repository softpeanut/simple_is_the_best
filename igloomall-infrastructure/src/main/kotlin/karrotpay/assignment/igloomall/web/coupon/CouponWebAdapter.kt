package karrotpay.assignment.igloomall.web.coupon

import karrotpay.assignment.igloomall.domain.coupon.dto.FindAllCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.FindCouponHistoriesResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.FindUserRetainedCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.IssueCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.UseCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.usecase.FindAllCoupons
import karrotpay.assignment.igloomall.domain.coupon.usecase.FindCouponHistories
import karrotpay.assignment.igloomall.domain.coupon.usecase.FindUserRetainedCoupons
import karrotpay.assignment.igloomall.domain.coupon.usecase.IssueCoupon
import karrotpay.assignment.igloomall.domain.coupon.usecase.UseCoupon
import karrotpay.assignment.igloomall.global.annotation.WebAdapter
import karrotpay.assignment.igloomall.web.coupon.dto.IssueCouponWebRequest
import karrotpay.assignment.igloomall.web.coupon.dto.UseCouponWebRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

@RequestMapping("/coupons")
@WebAdapter
class CouponWebAdapter(
    private val issueCouponUseCase: IssueCoupon,
    private val useCouponUseCase: UseCoupon,
    private val findUserRetainedCouponsUseCase: FindUserRetainedCoupons,
    private val findCouponHistoriesUseCase: FindCouponHistories,
    private val findAllCouponsUseCase: FindAllCoupons
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun issueCoupon(
        @Valid @RequestBody webRequest: IssueCouponWebRequest
    ): IssueCouponResponse {
        return issueCouponUseCase.execute(
            webRequest.userId, webRequest.couponCode
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    fun useCoupon(
        @Valid @RequestBody webRequest: UseCouponWebRequest
    ): UseCouponResponse {
        return useCouponUseCase.execute(
            webRequest.userId, webRequest.couponId
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}")
    fun findUserRetainedCoupons(
        @PathVariable("userId") userId: Long
    ): FindUserRetainedCouponsResponse {
        return findUserRetainedCouponsUseCase.execute(userId)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{couponId}")
    fun findCouponHistories(
        @PathVariable("couponId") couponId: Long
    ): FindCouponHistoriesResponse {
        return findCouponHistoriesUseCase.execute(couponId)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun findAllCoupons(): FindAllCouponsResponse {
        return findAllCouponsUseCase.execute()
    }
}