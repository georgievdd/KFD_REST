package com.example.kfd_hw.service.impl

import com.example.kfd_hw.database.dao.UserDao
import com.example.kfd_hw.database.entity.UserEntity
import com.example.kfd_hw.model.exception.NotFoundException
import com.example.kfd_hw.model.message.user.UserResponse
import com.example.kfd_hw.service.UserService
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val dao: UserDao
) : UserService {
    override fun getAll(): Iterable<UserResponse> =
        dao.findAll().map { user -> user.mapToUserResponse() }

    override fun getById(id: UUID): UserResponse =
        dao.findEntityById(id)?.mapToUserResponse() ?: throw NotFoundException()

    private fun UserEntity.mapToUserResponse(): UserResponse =
        UserResponse(
            id = this.id,
            username = this.username,
            email = this.email
        )
}