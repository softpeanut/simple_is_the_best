package karrotpay.assignment.igloomall.persistence.coupon.repository

import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntity
import karrotpay.assignment.igloomall.persistence.coupon.model.CouponHistoryJpaEntityId
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import javax.persistence.LockModeType

@Repository
interface CouponHistoryJpaRepository : CrudRepository<CouponHistoryJpaEntity, CouponHistoryJpaEntityId> {

    fun existsByCouponCodeAndUserId(couponCode: String, userId: Long): Boolean

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: CouponHistoryJpaEntityId): Optional<CouponHistoryJpaEntity>

}