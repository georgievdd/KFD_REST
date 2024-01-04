package com.example.kfd_hw.database.dao

import com.example.kfd_hw.database.dao.CommonDao
import com.example.kfd_hw.database.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID


interface RefreshTokenDao: CommonDao<RefreshTokenEntity> {
    fun findByEmail(email: String): RefreshTokenEntity?

    fun findByRefreshToken(token: String): RefreshTokenEntity?
}