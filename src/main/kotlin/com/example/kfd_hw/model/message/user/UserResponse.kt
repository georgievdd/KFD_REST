package com.example.kfd_hw.model.message.user

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String,
    val username: String
)
