package karrotpay.assignment.igloomall.web.coupon.dto

import javax.validation.constraints.NotNull

data class UseCouponWebRequest(

    @field:NotNull
    val userId: Long,

    @field:NotNull
    val couponId: Long

)
