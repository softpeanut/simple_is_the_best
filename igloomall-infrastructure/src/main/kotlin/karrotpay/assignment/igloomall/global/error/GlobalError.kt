package karrotpay.assignment.igloomall.global.error

import karrotpay.assignment.igloomall.common.error.ErrorProperty

enum class GlobalError(
    override val status: Int,
    override val message: String
) : ErrorProperty {

    BAD_REQUEST(400, "Bad Request"),
    INVALID_JSON(400, "Invalid JSON"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error")

}