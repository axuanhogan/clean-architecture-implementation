package personal.my.adapter.mapper

import personal.my.use_case.port.`in`.pdo.UserPDO
import personal.my.dao.UserDAO

fun UserDAO.toPDO(): UserPDO {
    return UserPDO(
        id = this.id,
        name = this.name,
    )
}
