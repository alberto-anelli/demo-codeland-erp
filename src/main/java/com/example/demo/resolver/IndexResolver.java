package com.example.demo.resolver;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexResolver {

    @GetMapping
    @Secured("ROLE_ERP_ADMIN")
    public String index(Model model, Authentication user) {
        model.addAttribute("user", user);
        return "index";
    }
}

