package karrotpay.assignment.igloomall.persistence.user

import karrotpay.assignment.igloomall.domain.user.spi.QueryUserPort
import karrotpay.assignment.igloomall.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserJpaRepository
) : QueryUserPort {

    override fun existsUserById(userId: Long): Boolean {
        return userRepository.existsById(userId)
    }
}