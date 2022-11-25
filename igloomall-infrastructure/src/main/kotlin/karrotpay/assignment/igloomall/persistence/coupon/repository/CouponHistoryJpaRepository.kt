package karrotpay.assignment.igloomall.persistence.coupon.repository

import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntityId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponHistoryJpaRepository : CrudRepository<CouponHistoryJpaEntity, CouponHistoryJpaEntityId> {

    fun existsByCouponCodeAndUserId(couponCode: String, userId: Long): Boolean

}