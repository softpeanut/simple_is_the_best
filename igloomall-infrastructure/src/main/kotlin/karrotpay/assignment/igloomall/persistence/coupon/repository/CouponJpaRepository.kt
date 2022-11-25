package karrotpay.assignment.igloomall.persistence.coupon.repository

import karrotpay.assignment.igloomall.persistence.coupon.model.CouponJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponJpaRepository : CrudRepository<CouponJpaEntity, Long> {

    fun findByCode(code: String): CouponJpaEntity?

}