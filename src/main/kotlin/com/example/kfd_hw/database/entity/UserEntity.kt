package com.example.kfd_hw.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class UserEntity(
    @Column(nullable = false, updatable = false, length = 100)
    val email: String,
    @Column(nullable = false, length = 100)
    var username: String,
    @Column(nullable = false, length = 250)
    var password: String,
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER
) : AbstractEntity()


enum class Role {
    USER, ADMIN,
}