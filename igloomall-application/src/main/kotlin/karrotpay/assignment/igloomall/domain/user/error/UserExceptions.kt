package karrotpay.assignment.igloomall.domain.user.error

import karrotpay.assignment.igloomall.common.error.BaseException

sealed class UserExceptions(
    override val status: Int,
    override val message: String
) : BaseException(status, message) {

    class NotFound(message: String = NOT_FOUND) : UserExceptions(404, message)

    companion object {
        private const val NOT_FOUND = "User not found"
    }
}
