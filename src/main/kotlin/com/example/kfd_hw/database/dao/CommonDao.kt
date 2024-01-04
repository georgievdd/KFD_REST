package com.example.kfd_hw.database.dao

import com.example.kfd_hw.database.entity.AbstractEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.UUID

@NoRepositoryBean
interface CommonDao<T : AbstractEntity> : CrudRepository<T, UUID> {
    fun findEntityById(id: UUID): T?
}