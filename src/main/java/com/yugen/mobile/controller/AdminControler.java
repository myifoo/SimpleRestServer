package com.yugen.mobile.controller;

import com.yugen.mobile.service.CustomService;
import com.yugen.mobile.service.OrderService;
import com.yugen.mobile.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
@RestController
@RequestMapping("/admin")
public class AdminControler {
    @Autowired
    private CustomService customService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/customers", produces = "text/plain; charset=utf-8")
    public String customers() {
        return customService.dumpTable();
    }

    @GetMapping(value = "/orders", produces = "text/plain; charset=utf-8")
    public String orders() {
        return orderService.dumpTable();
    }

    @GetMapping(value = "/requests", produces = "text/plain; charset=utf-8")
    public String requests() {
        return requestService.dumpTable();
    }
}
