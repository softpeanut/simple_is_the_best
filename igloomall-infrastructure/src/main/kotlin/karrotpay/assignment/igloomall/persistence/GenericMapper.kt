package karrotpay.assignment.igloomall.persistence

interface GenericMapper<E, D> {

    fun toDomain(entity: E?): D?

    fun toEntity(domain: D): E

}