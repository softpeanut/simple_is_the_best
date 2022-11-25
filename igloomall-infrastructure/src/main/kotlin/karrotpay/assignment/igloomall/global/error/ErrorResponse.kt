package karrotpay.assignment.igloomall.global.error

import karrotpay.assignment.igloomall.common.error.ErrorProperty
import org.springframework.core.MethodParameter
import org.springframework.validation.BindingResult

data class ErrorResponse(
    val status: Int,
    val message: Any
) {
    companion object {
        fun of(errorProperty: ErrorProperty) = ErrorResponse(
            errorProperty.status,
            errorProperty.message
        )

        fun of(e: BindingResult): ErrorResponse {
            val errorMap = HashMap<String, String?>()

            for (error in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return ErrorResponse(
                status = GlobalError.BAD_REQUEST.status,
                message = errorMap
            )
        }

        fun of(parameter: MethodParameter) = ErrorResponse(
            status = GlobalError.BAD_REQUEST.status,
            message = "Invalid parameter: ${parameter.parameterName} is '${parameter.parameterType}' type"
        )
    }
}