package com.example.kfd_hw.model

import com.example.kfd_hw.database.entity.Role
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class UserPrincipal(
    val email: String,
    val role: Role,
) : AbstractAuthenticationToken(mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_${role.name}"))) {

    init {
        isAuthenticated = true
    }
    override fun getCredentials() = null

    override fun getPrincipal() = email

}
