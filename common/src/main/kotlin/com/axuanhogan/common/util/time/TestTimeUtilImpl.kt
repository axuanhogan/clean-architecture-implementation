package com.axuanhogan.common.util.time

import io.quarkus.arc.profile.IfBuildProfile
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import jakarta.annotation.Priority
import java.time.ZoneId
import java.time.ZonedDateTime

@Alternative
@Priority(100)
@IfBuildProfile("test")
@ApplicationScoped
class TestTimeUtilImpl: TimeUtil {

    private var fixedTime: ZonedDateTime = ZonedDateTime.of(
        2025, 1, 1, 12, 23, 34, 555, ZoneId.of("Asia/Taipei")
    )

    fun setFixedTime(dateTime: ZonedDateTime) {
        this.fixedTime = dateTime
    }

    override fun now(): ZonedDateTime {
        return fixedTime
    }
}
