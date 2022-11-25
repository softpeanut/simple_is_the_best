package karrotpay.assignment.igloomall.global.exception

import karrotpay.assignment.igloomall.common.error.BaseException
import karrotpay.assignment.igloomall.global.error.GlobalError

object InternalServerErrorException : BaseException(
    GlobalError.INTERNAL_SERVER_ERROR
)