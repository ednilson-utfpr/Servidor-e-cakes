package com.efs.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;

@Controller
public class HomeController {

    @Autowired
    private Connection connection;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("conn", connection != null ? "Conexao ok" : "Erro na conexao");
        return "index";
    }

    @GetMapping("/teste")
    public String index2(Model model) {
        return "teste";
    }

    @GetMapping("/conn")
    public String myConn(Model model) {
        model.addAttribute("conn", connection != null ? "Conexao ok" : "Erro na conexao");
        return "index";

    }
}
