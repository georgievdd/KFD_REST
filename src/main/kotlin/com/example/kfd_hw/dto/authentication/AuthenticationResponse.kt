package com.example.kfd_hw.dto.authentication

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)
