package karrotpay.assignment.igloomall.domain.coupon.model

import karrotpay.assignment.igloomall.domain.coupon.exception.CouponAlreadyUsedException
import java.time.LocalDateTime

data class CouponHistory(

    val couponId: Long,

    val userId: Long,

    val issuedAt: LocalDateTime,

    val usedAt: LocalDateTime? = null

) {
    val isUsed: Boolean = usedAt != null

    fun use(): CouponHistory {
        if (isUsed) {
            throw CouponAlreadyUsedException
        }

        return this.copy(
            usedAt = LocalDateTime.now()
        )
    }
}
