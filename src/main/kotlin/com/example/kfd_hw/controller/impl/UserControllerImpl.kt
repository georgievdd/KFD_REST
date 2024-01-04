package com.example.kfd_hw.controller.impl

import com.example.kfd_hw.controller.UserController
import com.example.kfd_hw.database.entity.UserEntity
import com.example.kfd_hw.model.message.user.UserResponse
import com.example.kfd_hw.service.UserService
import com.example.kfd_hw.service.impl.UserServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/user")
class UserControllerImpl(
    private val userService: UserServiceImpl
) : UserController {

    @GetMapping
    override fun getAll(): Iterable<UserResponse> =
        userService.getAll()

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: UUID): UserResponse =
        userService.getById(id)

}