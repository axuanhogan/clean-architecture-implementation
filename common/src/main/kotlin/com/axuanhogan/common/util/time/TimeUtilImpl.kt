package com.axuanhogan.common.util.time

import jakarta.enterprise.context.ApplicationScoped
import java.time.ZonedDateTime

@ApplicationScoped
class TimeUtilImpl: TimeUtil {

    override fun now(): ZonedDateTime {
        return ZonedDateTime.now()
    }
}
