package karrotpay.assignment.igloomall.domain.coupon.model

import karrotpay.assignment.igloomall.domain.coupon.error.CouponExceptions

data class Coupon(

    val id: Long = 0,

    val code: String,

    val name: String,

    val amount: Long,

    val totalQuantity: Long,

    val issuedQuantity: Long = 0

) {
    fun calculateRemainingQuantity(): Long = totalQuantity - issuedQuantity

    fun issue(): Coupon {
        if (totalQuantity <= issuedQuantity) {
            throw CouponExceptions.NoLeft()
        }

        return this.copy(
            totalQuantity = totalQuantity.dec(),
            issuedQuantity = issuedQuantity.inc()
        )
    }
}
