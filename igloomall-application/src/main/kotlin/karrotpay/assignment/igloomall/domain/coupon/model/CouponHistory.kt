package karrotpay.assignment.igloomall.domain.coupon.model

import karrotpay.assignment.igloomall.domain.coupon.error.CouponExceptions
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
            throw CouponExceptions.AlreadyUsed()
        }

        return this.copy(
            usedAt = LocalDateTime.now()
        )
    }

    companion object {
        fun of(couponId: Long, userId: Long): CouponHistory = CouponHistory(
            couponId = couponId,
            userId = userId,
            issuedAt = LocalDateTime.now()
        )
    }
}
