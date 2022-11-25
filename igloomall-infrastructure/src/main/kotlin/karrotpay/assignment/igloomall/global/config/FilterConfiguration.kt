package karrotpay.assignment.igloomall.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import karrotpay.assignment.igloomall.global.error.ErrorFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfiguration(
    private val objectMapper: ObjectMapper
) {

    /**
     * 모든 endpoint에서 해당 filter를 거치도록 등록
     */
    @Bean
    protected fun registerFilter() = FilterRegistrationBean(
        ErrorFilter(objectMapper)
    ).apply {
        addUrlPatterns("*")
    }
}