package com.yugen.mobile.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String sayHello() {
        return "Hello from Spring5 and embedded Tomcat8!";
    }

    @GetMapping("/ping")
    public String ping() {
        return "ping";
    }

    @PostMapping("/read")
    public String read(@RequestBody String s) {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(s, JsonObject.class);
        return s;
    }
}
