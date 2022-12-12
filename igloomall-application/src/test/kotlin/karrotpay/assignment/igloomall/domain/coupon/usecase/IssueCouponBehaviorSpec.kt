package karrotpay.assignment.igloomall.domain.coupon.usecase

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import karrotpay.assignment.igloomall.common.afterContainer
import karrotpay.assignment.igloomall.domain.coupon.dto.IssueCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.error.CouponExceptions
import karrotpay.assignment.igloomall.domain.coupon.spi.CommandCouponPort
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import karrotpay.assignment.igloomall.domain.createCoupon
import karrotpay.assignment.igloomall.domain.createCouponVO
import java.time.LocalDateTime

class IssueCouponBehaviorSpec : BehaviorSpec({
    val queryCouponPort = mockk<QueryCouponPort>()
    val commandCouponPort = mockk<CommandCouponPort>()

    val issueCoupon = IssueCoupon(queryCouponPort, commandCouponPort)

    Given("사용자 ID와 쿠폰 코드에 해당하는 쿠폰 내역이 존재하지 않는 경우") {
        val userId = 1L
        val couponCode = "C0001"
        val couponId = 1L
        val issuedAt = LocalDateTime.now()
        val coupon = createCoupon(id = couponId, code = couponCode)
        val couponVO = createCouponVO(id = couponId, code = couponCode, userId = userId, issuedAt = issuedAt)

        every { queryCouponPort.getCoupon(any()) } returns coupon
        every { queryCouponPort.isAlreadyGotCoupon(any(), any()) } returns false
        every { commandCouponPort.saveCoupon(any()) } returns createCoupon()
        every { commandCouponPort.saveCouponHistory(any()) } returns couponVO

        When("특정 사용자가 쿠폰 코드에 해당하는 쿠폰을 발급하면") {
            val actual = issueCoupon.execute(userId, couponCode)

            Then("쿠폰 내역을 생성한다") {
                actual shouldBe IssueCouponResponse(couponId, couponCode, issuedAt)
            }
        }
    }

    Given("쿠폰 코드에 해당하는 쿠폰이 존재하지 않는 경우") {
        val userId = 1L
        val couponCode = "C0001"

        every { queryCouponPort.getCoupon(any()) } returns null

        When("해당 쿠폰을 조회하면") {
            Then("CouponNotFoundException 예외를 던진다") {
                shouldThrow<CouponExceptions.NotFound> {
                    issueCoupon.execute(userId, couponCode)
                }
            }
        }
    }

    Given("사용자 ID에 해당하는 사용자가 쿠폰 코드에 해당하는 쿠폰을 이미 발급받은 경우") {
        val userId = 1L
        val couponCode = "C0001"

        every { queryCouponPort.getCoupon(any()) } returns createCoupon(code = couponCode)
        every { queryCouponPort.isAlreadyGotCoupon(any(), any()) } returns true

        When("해당 쿠폰 내역을 조회하면") {
            Then("CouponAlreadyIssuedException 예외를 던진다") {
                shouldThrow<CouponExceptions.AlreadyIssued> {
                    issueCoupon.execute(userId, couponCode)
                }
            }
        }
    }

    Given("쿠폰 코드에 해당하는 쿠폰의 잔여 수량이 없는 경우") {
        val userId = 1L
        val couponCode = "C0001"
        val couponId = 1L
        val coupon = createCoupon(id = couponId, code = couponCode, totalQuantity = 1000, issuedQuantity = 1000)

        every { queryCouponPort.getCoupon(any()) } returns coupon
        every { queryCouponPort.isAlreadyGotCoupon(any(), any()) } returns false

        When("해당 쿠폰 내역을 조회하면") {
            Then("CouponNoLeftException 예외를 던진다") {
                shouldThrow<CouponExceptions.NoLeft> {
                    issueCoupon.execute(userId, couponCode)
                }
            }
        }
    }

    afterContainer()
})