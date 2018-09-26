package com.lr.tl_android.conrtoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "helllo";
    }
}
