package karrotpay.assignment.igloomall.global.annotation

import org.springframework.web.bind.annotation.RestController

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@RestController
annotation class WebAdapter
