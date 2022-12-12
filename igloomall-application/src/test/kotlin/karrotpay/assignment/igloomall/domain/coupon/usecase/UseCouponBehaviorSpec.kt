package karrotpay.assignment.igloomall.domain.coupon.usecase

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import karrotpay.assignment.igloomall.common.afterContainer
import karrotpay.assignment.igloomall.domain.coupon.dto.UseCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.error.CouponExceptions
import karrotpay.assignment.igloomall.domain.coupon.spi.CommandCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import karrotpay.assignment.igloomall.domain.createCouponHistory
import karrotpay.assignment.igloomall.domain.createCouponVO
import java.time.LocalDateTime

class UseCouponBehaviorSpec : BehaviorSpec({
    val queryCouponPort = mockk<QueryCouponPort>()
    val commandCouponPort = mockk<CommandCouponPort>()

    val useCoupon = UseCoupon(queryCouponPort, commandCouponPort)

    Given("사용자 ID가 쿠폰 ID에 해당하는 쿠폰을 사용하지 않은 경우") {
        val userId = 1L
        val couponId = 1L
        val usedAt = LocalDateTime.now()
        val couponHistory = createCouponHistory(userId = userId, couponId = couponId)
        val couponVO = createCouponVO(id = couponId, userId = userId, usedAt = usedAt)

        every { queryCouponPort.getCouponHistory(any(), any()) } returns couponHistory
        every { commandCouponPort.saveCouponHistory(any()) } returns couponVO

        When("특정 사용자가 쿠폰 ID에 해당하는 쿠폰을 사용하면") {
            val actual = useCoupon.execute(userId, couponId)

            Then("사용한 시간을 현재 시간으로 설정한다") {
                actual shouldBe UseCouponResponse(couponId, couponVO.coupon.code, couponVO.couponHistory.usedAt!!)
            }
        }
    }

    Given("사용자 ID가 쿠폰 ID에 해당하는 쿠폰을 사용한 경우") {
        val userId = 1L
        val couponId = 1L
        val usedAt = LocalDateTime.now()
        val couponHistory = createCouponHistory(userId = userId, couponId = couponId, usedAt = usedAt)

        every { queryCouponPort.getCouponHistory(any(), any()) } returns couponHistory

        When("특정 사용자가 쿠폰 ID에 해당하는 쿠폰을 사용하면") {
            Then("CouponAlreadyUsedException 예외를 던진다") {
                shouldThrow<CouponExceptions.AlreadyUsed> {
                    useCoupon.execute(userId, couponId)
                }
            }
        }
    }

    Given("사용자 ID가 쿠폰 ID에 해당하는 쿠폰을 발급받지 않은 경우") {
        val userId = 1L
        val couponId = 1L

        every { queryCouponPort.getCouponHistory(any(), any()) } returns null

        When("해당 사용자 ID와 쿠폰 ID로 쿠폰 내역을 조회하면") {
            Then("CouponNotIssuedException 예외를 던진다") {
                shouldThrow<CouponExceptions.NotIssued> {
                    useCoupon.execute(userId, couponId)
                }
            }
        }
    }

    afterContainer()
})