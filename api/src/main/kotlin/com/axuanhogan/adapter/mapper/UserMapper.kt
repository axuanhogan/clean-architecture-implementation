package com.axuanhogan.adapter.mapper

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.jpa.dao.UserDAO

fun UserDAO.toPDO(): UserPDO {
    return UserPDO(
        id = this.id,
        name = this.name,
    )
}
