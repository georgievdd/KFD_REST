package com.example.kfd_hw.model.message.user

import java.util.UUID

data class UserResponse(
    val id: Long,
    val email: String,
    val username: String
)
