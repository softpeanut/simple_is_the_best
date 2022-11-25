package karrotpay.assignment.igloomall.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeWebAdapter {

    @GetMapping
    fun welcome() = "Welcome to Igloo Mall"
}