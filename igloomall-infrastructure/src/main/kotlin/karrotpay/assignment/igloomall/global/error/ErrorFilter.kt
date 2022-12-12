package karrotpay.assignment.igloomall.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import karrotpay.assignment.igloomall.common.error.BaseException
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ErrorFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BaseException) {
            errorToJson(e, response)
        } catch (e: Exception) {

            /**
             * DispatcherServlet에서 발생하는 BaseException은
             * Spring 관련 Exception으로 wrapping되어서 위의 catch 문에서 잡히지 않으므로
             * e.cause의 타입을 검사
             */
            when (e.cause) {
                is BaseException -> errorToJson((e.cause as BaseException), response)
                else -> {
                    errorToJson(GlobalExceptions.InternalServerError(), response)
                    e.printStackTrace()
                }
            }
        }
    }

    private fun errorToJson(exception: BaseException, response: HttpServletResponse) {
        response.status = exception.status
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse.of(exception)))
    }
}