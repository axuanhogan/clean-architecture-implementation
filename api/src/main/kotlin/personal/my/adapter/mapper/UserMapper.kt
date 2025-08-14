package personal.my.adapter.mapper

import personal.my.core.port.`in`.pdo.UserPDO
import personal.my.jpa.dao.UserDAO

fun UserDAO.toPDO(): UserPDO {
    return UserPDO(
        id = this.id,
        name = this.name,
    )
}
