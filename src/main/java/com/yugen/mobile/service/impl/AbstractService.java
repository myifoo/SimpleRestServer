package com.yugen.mobile.service.impl;

import com.google.gson.JsonObject;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/10
 */
public class AbstractService {
    protected void internalSave(boolean exist, JsonObject model){
        if (exist) {
            update(model);
        } else {
            create(model);
        }
    }

    protected void update(JsonObject model) {

    }

    protected void create(JsonObject model) {

    }
}
