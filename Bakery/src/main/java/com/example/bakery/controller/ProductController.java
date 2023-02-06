package com.example.bakery.controller;

import com.example.bakery.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/products/list")
public class ProductController {

    @Autowired
    private AppUtils appUtil;

    @GetMapping
    public String showListPage(Model model) {
        String username = appUtil.getPrincipalUsername();

        if (Objects.equals(username, "anonymousUser")) {
            return "login";
        }

        model.addAttribute("username", username);
        return "products/list";
    }
}
