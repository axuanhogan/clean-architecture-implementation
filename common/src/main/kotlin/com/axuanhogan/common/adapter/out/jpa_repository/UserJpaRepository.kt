package com.axuanhogan.common.adapter.out.jpa_repository

import org.springframework.data.jpa.repository.JpaRepository
import com.axuanhogan.common.dao.UserDAO
import java.util.*

interface UserJpaRepository: JpaRepository<UserDAO, UUID>
