package karrotpay.assignment.igloomall.persistence.user.model

import karrotpay.assignment.igloomall.persistence.ColumnType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "tbl_user")
@Entity
class UserJpaEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = ColumnType.BIGINT)
    val id: Long = 0

)