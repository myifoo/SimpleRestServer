package com.yugen.mobile.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yugen.mobile.controller.utils.ResultCodeUtils;
import com.yugen.mobile.service.CustomService;
import com.yugen.mobile.service.OrderService;
import com.yugen.mobile.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
@RestController
@RequestMapping("/service")
public class ChinaMobileSaaSController {
    @Autowired
    private CustomService customService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RequestService requestService;

    @PostMapping(value = "/apply", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> apply(@RequestParam(value = "timestamp") Long timestamp, @RequestParam(value = "sign") String sign,
                                @RequestBody String bodyJson) {
        Gson gson = new Gson();
        JsonObject request = gson.fromJson(bodyJson, JsonObject.class);
        request.addProperty("timestamp", timestamp);
        request.addProperty("sign", sign);
        request.addProperty("type", "0001");
        request.addProperty("content", bodyJson);

        ResponseEntity<String> errorEntity = checkSaaS0001(request);
        if(errorEntity != null)
            return errorEntity;

        try {
            requestService.create(request); // 每次请求都会创建新的记录
            customService.save(request);
            orderService.save(request);
            return ResultCodeUtils.buildOkEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultCodeUtils.build500Entity();
    }

    @PostMapping(value = "/update", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> update(@RequestParam(value = "timestamp") Long timestamp, @RequestParam(value = "sign") String sign,
                       @RequestBody String bodyJson) {
        Gson gson = new Gson();
        JsonObject request = gson.fromJson(bodyJson, JsonObject.class);
        request.addProperty("timestamp", timestamp);
        request.addProperty("sign", sign);
        request.addProperty("type", "0002");
        request.addProperty("content", bodyJson);

        ResponseEntity<String> errorEntity = checkSaaS0002(request);
        if(errorEntity != null)
            return errorEntity;

        try {
            requestService.create(request); // 每次请求都会创建新的记录
            return ResultCodeUtils.buildOkEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultCodeUtils.build500Entity();
    }

    private ResponseEntity<String> checkSaaS0001(JsonObject request) {
        if(checkSign())
            return ResultCodeUtils.build501Entity();

        if(checkProductcode())
            return ResultCodeUtils.build502Entity();

        if(checkServicecode())
            return ResultCodeUtils.build503EntityOfSaaS0001();

        if(checkProductparas())
            return ResultCodeUtils.build504EntityOfSaaS0001();

        if(checkServiceparas())
            return ResultCodeUtils.build505EntityOfSaaS0001();

        if(checkEcordercode())
            return ResultCodeUtils.build506EntityOfSaaS0001();

        if(checkCustcode())
            return ResultCodeUtils.build507EntityOfSaaS0001();


        return null;
    }

    private ResponseEntity<String> checkSaaS0002(JsonObject request) {
        if(checkSign())
            return ResultCodeUtils.build501Entity();

        if(checkProductcode())
            return ResultCodeUtils.build502Entity();

        if(checkEcordercode())
            return ResultCodeUtils.build503EntityOfSaaS0002();

        if(checkCustcode())
            return ResultCodeUtils.build504EntityOfSaaS0002();

        return null;
    }

    private boolean checkSign() {
        return false; //todo
    }

    private boolean checkProductcode() {
        return false; //todo
    }

    private boolean checkEcordercode() {
        return false; //todo
    }

    private boolean checkCustcode() {
        return false; //todo
    }

    private boolean checkServicecode() {
        return false; //todo
    }

    private boolean checkProductparas() {
        return false; //todo
    }

    private boolean checkServiceparas() {
        return false; //todo
    }



}
