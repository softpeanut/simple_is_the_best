package karrotpay.assignment.igloomall.global.config

import org.springframework.context.annotation.Configuration
import java.util.TimeZone
import javax.annotation.PostConstruct

@Configuration
class TimeZoneConfiguration {

    @PostConstruct
    protected fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }
}