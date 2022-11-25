package karrotpay.assignment.igloomall.web

import karrotpay.assignment.igloomall.global.annotation.WebAdapter
import org.springframework.web.bind.annotation.GetMapping

@WebAdapter
class WelcomeWebAdapter {

    @GetMapping
    fun welcome() = "Welcome to Igloo Mall"
}