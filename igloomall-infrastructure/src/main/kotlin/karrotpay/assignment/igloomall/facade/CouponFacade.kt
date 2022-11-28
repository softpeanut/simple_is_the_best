package karrotpay.assignment.igloomall.facade

import karrotpay.assignment.igloomall.domain.coupon.dto.FindAllCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.FindCouponHistoriesResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.FindUserRetainedCouponsResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.IssueCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.dto.UseCouponResponse
import karrotpay.assignment.igloomall.domain.coupon.usecase.FindAllCoupons
import karrotpay.assignment.igloomall.domain.coupon.usecase.FindCouponHistories
import karrotpay.assignment.igloomall.domain.coupon.usecase.FindUserRetainedCoupons
import karrotpay.assignment.igloomall.domain.coupon.usecase.IssueCoupon
import karrotpay.assignment.igloomall.domain.coupon.usecase.UseCoupon
import karrotpay.assignment.igloomall.domain.user.usecase.CheckUserExists
import karrotpay.assignment.igloomall.global.config.CacheValue
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Component
class CouponFacade(
    private val issueCoupon: IssueCoupon,
    private val checkUserExists: CheckUserExists,
    private val useCoupon: UseCoupon,
    private val findUserRetainedCoupons: FindUserRetainedCoupons,
    private val findCouponHistories: FindCouponHistories,
    private val findAllCoupons: FindAllCoupons
) {

    @Transactional(
        readOnly = true,
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun findAllCoupons(): FindAllCouponsResponse {
        return findAllCoupons.execute()
    }

    @Cacheable(value = [CacheValue.FIND_COUPON_HISTORIES], key = "#couponId")
    @Transactional(
        readOnly = true,
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun findCouponHistories(couponId: Long): FindCouponHistoriesResponse {
        return findCouponHistories.execute(couponId)
    }

    @Cacheable(value = [CacheValue.FIND_USER_RETAINED_COUPON], key = "#userId")
    @Transactional(
        readOnly = true,
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun findUserRetainedCoupons(userId: Long): FindUserRetainedCouponsResponse {
        checkUserExists.execute(userId)

        return findUserRetainedCoupons.execute(userId)
    }

    @Caching(
        evict = [
            CacheEvict(value = [CacheValue.FIND_COUPON_HISTORIES], allEntries = true),
            CacheEvict(value = [CacheValue.FIND_USER_RETAINED_COUPON], key = "#userId")
        ]
    )
    @Transactional(rollbackFor = [Exception::class])
    fun issueCoupon(userId: Long, couponCode: String): IssueCouponResponse {
        checkUserExists.execute(userId)

        return issueCoupon.execute(userId, couponCode)
    }

    @Caching(
        evict = [
            CacheEvict(value = [CacheValue.FIND_COUPON_HISTORIES], key = "#couponId"),
            CacheEvict(value = [CacheValue.FIND_USER_RETAINED_COUPON], key = "#userId")
        ]
    )
    @Transactional(rollbackFor = [Exception::class])
    fun useCoupon(userId: Long, couponId: Long): UseCouponResponse {
        checkUserExists.execute(userId)

        return useCoupon.execute(userId, couponId)
    }
}