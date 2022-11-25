package karrotpay.assignment.igloomall.common.error

abstract class BaseException(
    val errorProperty: ErrorProperty
) : RuntimeException() {

    override fun fillInStackTrace() = this

}