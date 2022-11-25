package karrotpay.assignment.igloomall.domain.coupon.usecase

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.every
import io.mockk.mockk
import karrotpay.assignment.igloomall.common.afterContainer
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import karrotpay.assignment.igloomall.domain.createCouponVO
import java.time.LocalDateTime

class FindUserRetainedCouponsBehaviorSpec : BehaviorSpec({
    val queryCouponPort = mockk<QueryCouponPort>()

    val findUserRetainedCoupons = FindUserRetainedCoupons(queryCouponPort)

    Given("쿠폰 내역이 존재하는 경우") {
        val userId = 1L

        every { queryCouponPort.getCouponHistoriesByUser(any()) } returns listOf(
            createCouponVO(userId = userId, usedAt = LocalDateTime.now()),
            createCouponVO(userId = userId)
        )

        When("특정 사용자가 발급받은 쿠폰 내역을 조회하면") {
            val actual = findUserRetainedCoupons.execute(userId)

            Then("쿠폰 ID, 쿠폰 코드, 쿠폰 발급 일시, 쿠폰 사용 여부, 쿠폰 사용 일시를 확인할 수 있다") {
                actual.coupons shouldHaveSize 2
                actual.coupons[0].isUsedCoupon.shouldBeTrue()
                actual.coupons[1].isUsedCoupon.shouldBeFalse()
            }
        }
    }

    afterContainer()
})
