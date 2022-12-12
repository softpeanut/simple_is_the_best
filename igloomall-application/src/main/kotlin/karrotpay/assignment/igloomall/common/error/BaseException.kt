package karrotpay.assignment.igloomall.common.error

abstract class BaseException(
    open val status: Int,
    override val message: String
) : RuntimeException() {

    override fun fillInStackTrace() = this

}