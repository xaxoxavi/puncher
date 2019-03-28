package com.esliceu.puncher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XMLController {

    @GetMapping("/")
    public String getXMLForm() {
        return "form";
    }


}
