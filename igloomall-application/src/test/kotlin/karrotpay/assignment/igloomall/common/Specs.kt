package karrotpay.assignment.igloomall.common

import io.kotest.core.spec.Spec
import io.mockk.clearAllMocks

fun Spec.afterContainer() {
    afterContainer {
        clearAllMocks()
    }
}