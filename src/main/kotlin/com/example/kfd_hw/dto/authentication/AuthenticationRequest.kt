package com.example.kfd_hw.dto.authentication

import org.jetbrains.annotations.NotNull

data class AuthenticationRequest(
    @field:NotNull
    val email: String,
    @field:NotNull
    val password: String
)
