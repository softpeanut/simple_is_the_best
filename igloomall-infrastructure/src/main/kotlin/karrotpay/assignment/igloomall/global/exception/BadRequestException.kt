package karrotpay.assignment.igloomall.global.exception

import karrotpay.assignment.igloomall.common.error.BaseException
import karrotpay.assignment.igloomall.global.error.GlobalError

object BadRequestException : BaseException(
    GlobalError.BAD_REQUEST
)