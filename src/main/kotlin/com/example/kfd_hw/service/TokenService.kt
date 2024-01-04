package com.example.kfd_hw.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface TokenService {
    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String
    fun extractEmail(token: String): String?

    fun isExpired(token: String): Boolean

    fun isValid(token: String, userDetails: UserDetails): Boolean
}