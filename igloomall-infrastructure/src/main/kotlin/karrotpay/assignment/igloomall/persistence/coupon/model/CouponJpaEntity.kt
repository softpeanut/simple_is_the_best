package karrotpay.assignment.igloomall.persistence.coupon.model

import karrotpay.assignment.igloomall.persistence.ColumnType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "tbl_coupon")
@Entity
class CouponJpaEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = ColumnType.BIGINT)
    val id: Long = 0,

    @Column(
        name = "code",
        columnDefinition = ColumnType.CHAR + "(5)",
        nullable = false,
        unique = true
    )
    val code: String,

    @Column(
        name = "name",
        columnDefinition = ColumnType.VARCHAR + "(10)",
        nullable = false
    )
    val name: String,

    @Column(
        name = "amount",
        columnDefinition = ColumnType.BIGINT,
        nullable = false
    )
    val amount: Long,

    @Column(
        name = "total_quantity",
        columnDefinition = ColumnType.BIGINT,
        nullable = false
    )
    val totalQuantity: Long,

    @Column(
        name = "issued_quantity",
        columnDefinition = ColumnType.BIGINT,
        nullable = false
    )
    val issuedQuantity: Long


)