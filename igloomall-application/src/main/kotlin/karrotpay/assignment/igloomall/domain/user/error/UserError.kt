package karrotpay.assignment.igloomall.domain.user.error

import karrotpay.assignment.igloomall.common.error.ErrorProperty

enum class UserError(
    override val status: Int,
    override val message: String,
) : ErrorProperty {

    USER_NOT_FOUND(404, "User not found")

}