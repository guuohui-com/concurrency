package com.ysu.concurrency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
