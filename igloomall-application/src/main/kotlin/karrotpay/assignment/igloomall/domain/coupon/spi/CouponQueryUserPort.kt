package karrotpay.assignment.igloomall.domain.coupon.spi

interface CouponQueryUserPort {

    fun existsUserById(userId: Long): Boolean

}