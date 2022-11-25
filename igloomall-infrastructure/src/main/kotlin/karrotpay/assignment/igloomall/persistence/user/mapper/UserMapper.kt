package karrotpay.assignment.igloomall.persistence.user.mapper

import karrotpay.assignment.igloomall.domain.user.model.User
import karrotpay.assignment.igloomall.global.annotation.Mapper
import karrotpay.assignment.igloomall.persistence.GenericMapper
import karrotpay.assignment.igloomall.persistence.user.model.UserJpaEntity

@Mapper
class UserMapper : GenericMapper<UserJpaEntity, User> {

    override fun toDomain(entity: UserJpaEntity?): User? {
        return entity?.run {
            User(id = id)
        }
    }

    override fun toEntity(domain: User): UserJpaEntity {
        return domain.run {
            UserJpaEntity(id = id)
        }
    }
}