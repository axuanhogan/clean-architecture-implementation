package com.axuanhogan.common.mapper

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.common.dao.UserDAO

fun UserDAO.toPDO(): UserPDO {
    return UserPDO(
        id = this.id,
        name = this.name,
    )
}
