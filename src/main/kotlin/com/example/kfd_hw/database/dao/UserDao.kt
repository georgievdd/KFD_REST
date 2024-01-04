package com.example.kfd_hw.database.dao

import com.example.kfd_hw.database.entity.UserEntity
import java.util.UUID

interface UserDao : CommonDao<UserEntity> {
    fun findByEmail(email: String): UserEntity?
}