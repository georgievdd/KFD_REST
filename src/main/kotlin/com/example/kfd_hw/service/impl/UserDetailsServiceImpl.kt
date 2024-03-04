package com.example.kfd_hw.service.impl

import com.example.kfd_hw.database.dao.UserDao
import com.example.kfd_hw.model.exception.NotFoundException
import com.example.kfd_hw.database.entity.UserEntity as ApplicationUser
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
@Service
class UserDetailsServiceImpl(
    private val dao: UserDao
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = dao.findByEmail(username) ?: throw NotFoundException()
        return user.mapToUserDetails()
    }

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()

}