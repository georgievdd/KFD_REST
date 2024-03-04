package com.example.kfd_hw.service.impl

import com.example.kfd_hw.config.JwtProperties
import com.example.kfd_hw.database.dao.RefreshTokenDao
import com.example.kfd_hw.database.dao.UserDao
import com.example.kfd_hw.database.entity.RefreshTokenEntity
import com.example.kfd_hw.database.entity.Role
import com.example.kfd_hw.database.entity.UserEntity
import com.example.kfd_hw.dto.authentication.AuthenticationRequest
import com.example.kfd_hw.dto.authentication.AuthenticationResponse
import com.example.kfd_hw.dto.authentication.RegistrationRequest
import com.example.kfd_hw.model.UserPrincipal
import com.example.kfd_hw.model.exception.AlreadyExistException
import com.example.kfd_hw.model.exception.BadRequestException
import com.example.kfd_hw.model.exception.NotFoundException
import com.example.kfd_hw.model.exception.UnauthorizeException
import com.example.kfd_hw.service.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDao: UserDao,
    private val tokenService: TokenServiceImpl,
    private val jwtProperties: JwtProperties,
    private val refreshTokenDao: RefreshTokenDao,
    private val encoder: PasswordEncoder,
) : AuthenticationService {


    override fun login(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        val authRefresh = refreshTokenDao.findByEmail(authRequest.email) ?: throw NotFoundException()
        // плохо, роль должна быть не здесь
        val role = userDao.findByEmail(authRequest.email)!!.role
        val userPrincipal = UserPrincipal(authRequest.email, role)
        println(authRequest)
        val accessToken = tokenService.generateAccessToken(userPrincipal)
        val refreshToken = tokenService.generateRefreshToken(userPrincipal)

        authRefresh.refreshToken = refreshToken
        refreshTokenDao.save(authRefresh)

        return AuthenticationResponse(
            refreshToken = refreshToken,
            accessToken = accessToken,
        )
    }

    override fun registration(registrationRequest: RegistrationRequest): AuthenticationResponse {
        val user = userDao.findByEmail(registrationRequest.email)

        if (user != null)
            throw AlreadyExistException()

        val userPrincipal = UserPrincipal(registrationRequest.email, Role.USER)
        val accessToken = tokenService.generateAccessToken(userPrincipal)
        val refreshToken = tokenService.generateRefreshToken(userPrincipal)

        userDao.save(createUser(registrationRequest))
        // добавить связь сущностям
        refreshTokenDao.save(createRefresh(refreshToken, registrationRequest.email))

        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    override fun refresh(refreshToken: String): AuthenticationResponse {
        val refresh = refreshTokenDao.findByRefreshToken(refreshToken) ?: throw NotFoundException()
        val email = tokenService.extractEmail(refreshToken) ?: throw UnauthorizeException()
        val userPrincipal = tokenService.createContext(email, tokenService.extractRole(refreshToken))
        val accessToken = tokenService.generateAccessToken(userPrincipal)
        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refresh.refreshToken
        )
    }

    private fun createUser(data: RegistrationRequest) = UserEntity(
        username = data.username,
        email = data.email,
        password = encoder.encode(data.password),
    )
    private fun createRefresh(refreshToken: String, email: String) = RefreshTokenEntity(
        refreshToken = refreshToken,
        email = email,
    )
}