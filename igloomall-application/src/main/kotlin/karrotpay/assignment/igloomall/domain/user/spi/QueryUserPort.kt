package karrotpay.assignment.igloomall.domain.user.spi

interface QueryUserPort {

    fun existsUserById(userId: Long): Boolean

}