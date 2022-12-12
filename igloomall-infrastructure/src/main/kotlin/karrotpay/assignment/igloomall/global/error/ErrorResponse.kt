package karrotpay.assignment.igloomall.global.error

import karrotpay.assignment.igloomall.common.error.BaseException
import org.springframework.core.MethodParameter
import org.springframework.validation.BindingResult

data class ErrorResponse(
    val status: Int,
    val message: Any
) {
    companion object {
        fun of(exception: BaseException) = ErrorResponse(
            exception.status,
            exception.message
        )

        fun of(e: BindingResult): ErrorResponse {
            val errorMap = HashMap<String, String?>()

            for (error in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return ErrorResponse(
                status = 400,
                message = errorMap
            )
        }

        fun of(parameter: MethodParameter) = ErrorResponse(
            status = 400,
            message = "Invalid parameter: ${parameter.parameterName} is '${parameter.parameterType}' type"
        )
    }
}