package karrotpay.assignment.igloomall.domain.user.usecase

import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.domain.user.exception.UserNotFoundException
import karrotpay.assignment.igloomall.domain.user.spi.QueryUserPort

@UseCase
class CheckUserExists(
    private val queryUserPort: QueryUserPort
) {

    fun execute(userId: Long) {
        if (!queryUserPort.existsUserById(userId)) {
            throw UserNotFoundException
        }
    }
}