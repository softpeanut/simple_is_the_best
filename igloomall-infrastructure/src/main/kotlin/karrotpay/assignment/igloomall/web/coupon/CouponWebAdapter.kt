package karrotpay.assignment.igloomall.web.coupon

import karrotpay.assignment.igloomall.domain.coupon.dto.FindAllCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.FindCouponHistoriesResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.FindUserRetainedCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.IssueCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.UseCouponResponse
import karrotpay.assignment.igloomall.facade.CouponFacade
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
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/coupons")
@RestController
class CouponWebAdapter(
    private val couponFacade: CouponFacade
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun issueCoupon(
        @Valid @RequestBody webRequest: IssueCouponWebRequest
    ): IssueCouponResponse {
        return couponFacade.issueCoupon(
            webRequest.userId, webRequest.couponCode
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    fun useCoupon(
        @Valid @RequestBody webRequest: UseCouponWebRequest
    ): UseCouponResponse {
        return couponFacade.useCoupon(
            webRequest.userId, webRequest.couponId
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}")
    fun findUserRetainedCoupons(
        @PathVariable("userId") userId: Long
    ): FindUserRetainedCouponsResponse {
        return couponFacade.findUserRetainedCoupons(userId)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{couponId}")
    fun findCouponHistories(
        @PathVariable("couponId") couponId: Long
    ): FindCouponHistoriesResponse {
        return couponFacade.findCouponHistories(couponId)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun findAllCoupons(): FindAllCouponsResponse {
        return couponFacade.findAllCoupons()
    }
}