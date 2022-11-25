package karrotpay.assignment.igloomall.domain.user.usecase

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import karrotpay.assignment.igloomall.common.afterContainer
import karrotpay.assignment.igloomall.domain.user.exception.UserNotFoundException
import karrotpay.assignment.igloomall.domain.user.spi.QueryUserPort

class CheckUserExistsBehaviorSpec : BehaviorSpec({
    val queryUserPort = mockk<QueryUserPort>()

    val checkUserExists = CheckUserExists(queryUserPort)

    Given("사용자 ID에 해당하는 사용자가 존재하는 경우") {
        val userId = 1L

        every { queryUserPort.existsUserById(any()) } returns true

        When("해당 사용자를 조회하면") {
            Then("예외를 던지지 않는다") {
                shouldNotThrow<UserNotFoundException> {
                    checkUserExists.execute(userId)
                }
            }
        }
    }

    Given("사용자 ID에 해당하는 사용자가 존재하지 않는 경우") {
        val userId = 1L

        every { queryUserPort.existsUserById(any()) } returns false

        When("해당 사용자를 조회하면") {
            Then("UserNotFoundException 예외를 던진다") {
                shouldThrow<UserNotFoundException> {
                    checkUserExists.execute(userId)
                }
            }
        }
    }

    afterContainer()
})