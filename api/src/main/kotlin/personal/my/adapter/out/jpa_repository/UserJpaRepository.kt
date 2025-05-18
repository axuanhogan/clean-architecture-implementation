package personal.my.adapter.out.jpa_repository

import org.springframework.data.jpa.repository.JpaRepository
import personal.my.dao.User
import java.util.*

interface UserJpaRepository: JpaRepository<User, String> {
    fun findById(id: UUID): List<User>
}
