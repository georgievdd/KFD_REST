package com.example.kfd_hw.dto.authentication

import org.jetbrains.annotations.NotNull

data class RegistrationRequest(
    @NotNull
    val email: String,
    @NotNull
    val password: String,
    @NotNull
    val username: String
)