package karrotpay.assignment.igloomall.persistence.user.repository

import karrotpay.assignment.igloomall.persistence.user.model.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : CrudRepository<UserJpaEntity, Long>