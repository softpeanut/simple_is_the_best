package karrotpay.assignment.igloomall.domain.coupon.usecase

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import karrotpay.assignment.igloomall.common.afterContainer
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import karrotpay.assignment.igloomall.domain.createCouponVO
import java.time.LocalDateTime

class FindCouponHistoriesBehaviorSpec : BehaviorSpec({
    val queryCouponPort = mockk<QueryCouponPort>()

    val findCouponHistories = FindCouponHistories(queryCouponPort)

    Given("쿠폰 내역이 존재하는 경우") {
        val couponId = 1L

        every { queryCouponPort.getCouponHistoriesByCouponId(any()) } returns listOf(
            createCouponVO(id = couponId, usedAt = LocalDateTime.now()),
            createCouponVO(id = couponId)
        )

        When("특정 쿠폰에 해당하는 쿠폰 내역을 조회하면") {
            val actual = findCouponHistories.execute(couponId)

            Then("사용자 ID, 쿠폰 ID, 쿠폰 코드, 쿠폰 발급 일시, 쿠폰 사용 여부, 쿠폰 사용 일시를 확인할 수 있다") {
                actual.coupons shouldHaveSize 2
                actual.coupons[0].couponId shouldBe couponId
                actual.coupons[0].isUsedCoupon.shouldBeTrue()
                actual.coupons[1].couponId shouldBe couponId
                actual.coupons[1].isUsedCoupon.shouldBeFalse()
            }
        }
    }

    afterContainer()
})
