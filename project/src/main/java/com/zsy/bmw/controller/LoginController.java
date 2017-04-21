package com.zsy.bmw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MAC on 21/04/2017.
 */

@RestController
public class LoginController {

    @RequestMapping(value = "/")
    public String Login() {
        return "hahhaaa";
    }
}
