package com.yugen.mobile.service;


import com.google.gson.JsonObject;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
public interface RequestService {
    void create(JsonObject custom);
    String dumpTable();
}
