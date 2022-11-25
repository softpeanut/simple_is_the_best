package karrotpay.assignment.igloomall.persistence.coupon.repository

import karrotpay.assignment.igloomall.persistence.coupon.model.CouponJpaEntity
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType

@Repository
interface CouponJpaRepository : CrudRepository<CouponJpaEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByCode(code: String): CouponJpaEntity?

}