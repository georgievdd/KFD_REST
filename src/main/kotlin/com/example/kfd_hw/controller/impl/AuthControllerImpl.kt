package com.example.kfd_hw.controller.impl

import com.example.kfd_hw.controller.AuthController
import com.example.kfd_hw.dto.authentication.AuthenticationRequest
import com.example.kfd_hw.dto.authentication.AuthenticationResponse
import com.example.kfd_hw.dto.authentication.RegistrationRequest
import com.example.kfd_hw.dto.token.RefreshTokenRequest
import com.example.kfd_hw.dto.token.TokenResponse
import com.example.kfd_hw.service.impl.AuthenticationServiceImpl
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthControllerImpl(
    private val authenticationService: AuthenticationServiceImpl
) : AuthController {

    @PostMapping("/login")
    override fun login(@Valid @RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.login(authRequest)

    @PostMapping("/registration")
    override fun registration(@Valid @RequestBody registrationRequest: RegistrationRequest): AuthenticationResponse =
        authenticationService.registration(registrationRequest)

    @PostMapping("/refresh")
    override fun refresh(@RequestBody request: RefreshTokenRequest): AuthenticationResponse =
        authenticationService.refreshAccessToken(request.token)
}
