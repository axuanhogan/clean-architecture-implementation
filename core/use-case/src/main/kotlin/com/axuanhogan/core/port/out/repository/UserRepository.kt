package com.axuanhogan.core.port.out.repository

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import java.util.*

interface UserRepository: CommonRepository<UserPDO, UUID>
