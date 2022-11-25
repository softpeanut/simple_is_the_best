package karrotpay.assignment.igloomall.domain

import karrotpay.assignment.igloomall.domain.coupon.model.Coupon
import karrotpay.assignment.igloomall.domain.coupon.model.CouponHistory
import karrotpay.assignment.igloomall.domain.coupon.spi.vo.CouponVO
import java.time.LocalDateTime

fun createCouponHistory(
    couponId: Long = 1L,
    userId: Long = 1L,
    issuedAt: LocalDateTime = LocalDateTime.now(),
    usedAt: LocalDateTime? = null
) = CouponHistory(
    couponId = couponId,
    userId = userId,
    issuedAt = issuedAt,
    usedAt = usedAt
)

fun createCoupon(
    id: Long = 1L,
    code: String = "C0001",
    name: String = "의류 할인 쿠폰",
    amount: Long = 50000L,
    totalQuantity: Long = 1000L,
    issuedQuantity: Long = 0L
) = Coupon(
    id = id,
    code = code,
    name = name,
    amount = amount,
    totalQuantity = totalQuantity,
    issuedQuantity = issuedQuantity
)

fun createCouponVO(
    id: Long = 1L,
    code: String = "C0001",
    name: String = "의류 할인 쿠폰",
    amount: Long = 50000L,
    totalQuantity: Long = 1000L,
    issuedQuantity: Long = 0L,
    userId: Long = 1L,
    issuedAt: LocalDateTime = LocalDateTime.now(),
    usedAt: LocalDateTime? = null
) = CouponVO(
    coupon = createCoupon(
        id = id,
        code = code,
        name = name,
        amount = amount,
        totalQuantity = totalQuantity,
        issuedQuantity = issuedQuantity
    ),
    couponHistory = createCouponHistory(
        couponId = id,
        userId = userId,
        issuedAt = issuedAt,
        usedAt = usedAt
    )
)