package karrotpay.assignment.igloomall.domain.coupon.usecase

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.every
import io.mockk.mockk
import karrotpay.assignment.igloomall.common.afterContainer
import karrotpay.assignment.igloomall.domain.coupon.spi.QueryCouponPort
import karrotpay.assignment.igloomall.domain.createCoupon

class FindAllCouponsBehaviorSpec : BehaviorSpec({
    val queryCouponPort = mockk<QueryCouponPort>()

    val findAllCoupons = FindAllCoupons(queryCouponPort)

    Given("쿠폰 내역이 존재하는 경우") {
        every { queryCouponPort.getAllCoupons() } returns listOf(
            createCoupon(totalQuantity = 10, issuedQuantity = 1),
            createCoupon(totalQuantity = 10, issuedQuantity = 10)
        )

        When("특정 쿠폰에 해당하는 쿠폰 내역을 조회하면") {
            val actual = findAllCoupons.execute()

            Then("쿠폰 코드, 쿠폰명, 쿠폰 금액, 전체 쿠폰 수량, 남아있는 쿠폰 수량을 확인할 수 있다") {
                actual.coupons shouldHaveSize 2
            }
        }
    }

    afterContainer()
})