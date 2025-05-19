package personal.my.adapter.out.jpa_repository

import org.springframework.data.jpa.repository.JpaRepository
import personal.my.dao.UserDAO
import java.util.*

interface UserJpaRepository: JpaRepository<UserDAO, String> {
    fun findById(id: UUID): List<UserDAO>
}
