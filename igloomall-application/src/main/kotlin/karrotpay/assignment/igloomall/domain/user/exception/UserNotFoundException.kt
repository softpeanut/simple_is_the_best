package karrotpay.assignment.igloomall.domain.user.exception

import karrotpay.assignment.igloomall.common.error.BaseException
import karrotpay.assignment.igloomall.domain.user.error.UserError

object UserNotFoundException : BaseException(
    UserError.USER_NOT_FOUND
)
