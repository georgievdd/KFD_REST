package com.example.kfd_hw.service

import com.example.kfd_hw.database.entity.UserEntity
import com.example.kfd_hw.model.message.user.UserResponse
import java.util.UUID

interface UserService {
    fun getAll(): Iterable<UserResponse>

    fun getById(id: UUID): UserResponse
}