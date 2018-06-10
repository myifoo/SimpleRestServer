package com.yugen.mobile.controller.utils;

import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/10
 */
public class ResultCodeUtils {
    public static ResponseEntity<String> buildOkEntity() {
        JsonObject response = new JsonObject();
        response.addProperty("result", true);
        response.addProperty("errmsg", "");
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    public static ResponseEntity<String> build500Entity() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "服务方未定义异常");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(500));
    }

    public static ResponseEntity<String> build501Entity() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "sign的值错误");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(501));
    }

    public static ResponseEntity<String> build502Entity() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "productcode不存在");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(502));
    }

    public static ResponseEntity<String> build503EntityOfSaaS0002() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "企业应用订购编码不存在");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(503));
    }

    public static ResponseEntity<String> build504EntityOfSaaS0002() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "客户不存在");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(504));
    }

    public static ResponseEntity<String> build503EntityOfSaaS0001() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "业务编码不存在");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(503));
    }

    public static ResponseEntity<String> build504EntityOfSaaS0001() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "产品参数错误");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(504));
    }

    public static ResponseEntity<String> build505EntityOfSaaS0001() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "业务参数错误");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(505));
    }

    public static ResponseEntity<String> build506EntityOfSaaS0001() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "企业应用订购编码不存在（变更时）");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(506));
    }

    public static ResponseEntity<String> build507EntityOfSaaS0001() {
        JsonObject response = new JsonObject();
        response.addProperty("result", false);
        response.addProperty("errmsg", "客户不存在（变更时）");
        return new ResponseEntity<>(response.toString(), HttpStatus.valueOf(507));
    }


}
