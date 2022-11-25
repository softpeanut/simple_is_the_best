package karrotpay.assignment.igloomall.web.coupon.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class IssueCouponWebRequest(

    @field:NotNull
    val userId: Long,

    @field:NotBlank
    val couponCode: String

)
