package com.axuanhogan.common.mapper

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.common.dao.UserDAO

fun UserDAO.toPDO(): UserPDO {
    return UserPDO(
        id = this.id,
        email = this.email,
        name = this.name,
    )
}

fun UserPDO.toDAO(): UserDAO {
    return UserDAO(
        id = this.id,
        email = this.email,
        name = this.name,
    )
}
