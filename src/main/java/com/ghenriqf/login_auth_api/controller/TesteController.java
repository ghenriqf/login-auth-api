package com.ghenriqf.login_auth_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {
    @GetMapping
    public String teste() {
        return "Teste de segurança";
    }
}
