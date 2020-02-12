package de.clines.documentation.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootPathController {

    @GetMapping("/")
    fun redirectOnRoot() = "redirect:/swagger-ui.html"

}