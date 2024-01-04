package com.example.kfd_hw.service.impl

import com.example.kfd_hw.database.dao.UserDao
import com.example.kfd_hw.database.entity.Role
import com.example.kfd_hw.dto.authentication.RegistrationRequest
import com.example.kfd_hw.model.exception.NotFoundException
import com.example.kfd_hw.database.entity.UserEntity as ApplicationUser
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
@Service
class CustomUserDetailsService(
    private val userDao: UserDao
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userDao.findByEmail(username)
        return user?.mapToUserDetails() ?: throw NotFoundException()
    }

    fun createUserDetails(user: RegistrationRequest): UserDetails =
        User.builder()
            .username(user.email)
            .password(user.password)
            .roles(Role.USER.name)
            .build()

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()

}