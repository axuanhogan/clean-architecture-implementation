package com.axuanhogan.common.dao

import com.axuanhogan.common.util.encryption.DAOAttributeConverter
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime
import java.util.UUID

@Entity(name = "users")
class UserDAO(
    @Id
    var id: UUID,

    @Convert(converter = DAOAttributeConverter::class)
    @Column(name = "email", nullable = false, length = 64)
    var email: String,

    @Column(name = "name", nullable = false, length = 16)
    var name: String,
) {
    @field:CreationTimestamp
    @Column(name = "create_date_time", updatable = false)
    lateinit var createDateTime: ZonedDateTime

    @field:UpdateTimestamp
    @Column(name = "update_date_time")
    lateinit var updateDateTime: ZonedDateTime
}
