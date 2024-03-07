package com.example.kfd_hw.database.entity

import jakarta.persistence.*

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
    var role: Role = Role.USER,
) : AbstractEntity() {
    @OneToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "refreshTokenId")
    var refresh: RefreshTokenEntity? = null
}


enum class Role {
    USER, ADMIN,
}