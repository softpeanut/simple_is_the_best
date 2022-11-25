package karrotpay.assignment.igloomall.persistence.user

import karrotpay.assignment.igloomall.domain.user.spi.UserPort
import karrotpay.assignment.igloomall.global.annotation.PersistenceAdapter
import karrotpay.assignment.igloomall.persistence.user.mapper.UserMapper
import karrotpay.assignment.igloomall.persistence.user.repository.UserJpaRepository

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userMapper: UserMapper,
    private val userRepository: UserJpaRepository
) : UserPort {

    override fun existsUserById(userId: Long) = userRepository.existsById(userId)
}