package com.example.kfd_hw.database.entity

import com.example.kfd_hw.database.dao.CommonDao
import jakarta.persistence.*
import java.util.*

@Entity
class RefreshTokenEntity(
    @Column(nullable = false, length = 250)
    var email: String,
    @Column(nullable = false, length = 250)
    var refreshToken: String,
) : AbstractEntity()