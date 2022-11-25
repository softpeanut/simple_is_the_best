package karrotpay.assignment.igloomall.persistence.user

import karrotpay.assignment.igloomall.domain.coupon.spi.CouponQueryUserPort
import karrotpay.assignment.igloomall.global.annotation.PersistenceAdapter
import karrotpay.assignment.igloomall.persistence.user.repository.UserJpaRepository

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserJpaRepository
) : CouponQueryUserPort {

    override fun existsUserById(userId: Long) = userRepository.existsById(userId)
}