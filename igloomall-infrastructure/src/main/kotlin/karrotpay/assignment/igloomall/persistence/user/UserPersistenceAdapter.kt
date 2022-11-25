package karrotpay.assignment.igloomall.persistence.user

import karrotpay.assignment.igloomall.domain.coupon.spi.CouponQueryUserPort
import karrotpay.assignment.igloomall.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserJpaRepository
) : CouponQueryUserPort {

    override fun existsUserById(userId: Long) = userRepository.existsById(userId)
}