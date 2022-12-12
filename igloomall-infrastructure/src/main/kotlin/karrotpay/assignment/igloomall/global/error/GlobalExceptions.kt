package karrotpay.assignment.igloomall.global.error

import karrotpay.assignment.igloomall.common.error.BaseException

sealed class GlobalExceptions(
    override val status: Int,
    override val message: String
) : BaseException(status, message) {

    class BadRequest(message: String = BAD_REQUEST) : GlobalExceptions(400, message)

    class InvalidJson(message: String = INVALID_JSON) : GlobalExceptions(401, message)

    class InternalServerError(message: String = INTERNAL_SERVER_ERROR) : GlobalExceptions(500, message)

    companion object {
        private const val BAD_REQUEST = "Bad Request"
        private const val INVALID_JSON = "Invalid JSON"
        private const val INTERNAL_SERVER_ERROR = "Internal Server Error"
    }
}