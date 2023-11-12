package com.springframework.springrecipeapp.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"","/","/index","/index.html"})
    public String getHome(Model model){
        System.out.println("Hello from app");
        return "index.html";
    }
}
