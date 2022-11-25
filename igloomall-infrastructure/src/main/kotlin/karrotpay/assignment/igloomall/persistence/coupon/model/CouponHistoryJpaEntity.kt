package karrotpay.assignment.igloomall.persistence.coupon.model

import karrotpay.assignment.igloomall.persistence.ColumnType
import karrotpay.assignment.igloomall.persistence.user.model.UserJpaEntity
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Table(name = "tbl_coupon_history")
@Entity
class CouponHistoryJpaEntity(

    couponId: Long,

    userId: Long,

    @MapsId("couponId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    val coupon: CouponJpaEntity?,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserJpaEntity?,

    @Column(
        name = "issued_at",
        columnDefinition = ColumnType.DATETIME,
        nullable = false
    )
    val issuedAt: LocalDateTime,

    @Column(
        name = "used_at",
        columnDefinition = ColumnType.DATETIME
    )
    val usedAt: LocalDateTime?

) {
    @EmbeddedId
    val id: CouponHistoryJpaEntityId = CouponHistoryJpaEntityId(couponId, userId)
}

@Embeddable
data class CouponHistoryJpaEntityId(

    @Column(
        name = "coupon_id",
        columnDefinition = ColumnType.BIGINT,
        nullable = false
    )
    val couponId: Long,

    @Column(
        name = "user_id",
        columnDefinition = ColumnType.BIGINT,
        nullable = false
    )
    val userId: Long

) : Serializable