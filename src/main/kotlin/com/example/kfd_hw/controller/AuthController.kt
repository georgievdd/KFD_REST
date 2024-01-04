package com.example.kfd_hw.controller

import com.example.kfd_hw.dto.authentication.AuthenticationRequest
import com.example.kfd_hw.dto.authentication.AuthenticationResponse
import com.example.kfd_hw.dto.authentication.RegistrationRequest
import com.example.kfd_hw.dto.token.RefreshTokenRequest
import com.example.kfd_hw.dto.token.TokenResponse

interface AuthController {
    fun login(authRequest: AuthenticationRequest): AuthenticationResponse
    fun registration(registrationRequest: RegistrationRequest): AuthenticationResponse
    fun refresh(request: RefreshTokenRequest): AuthenticationResponse
}