package karrotpay.assignment.igloomall.domain.user.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.user.error.UserExceptions
import karrotpay.assignment.igloomall.domain.user.spi.QueryUserPort

@UseCase
class CheckUserExists(
    private val queryUserPort: QueryUserPort
) {

    fun execute(userId: Long) {
        if (!queryUserPort.existsUserById(userId)) {
            throw UserExceptions.NotFound()
        }
    }
}