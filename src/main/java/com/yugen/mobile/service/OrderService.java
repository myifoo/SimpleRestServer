package com.yugen.mobile.service;


import com.google.gson.JsonObject;

import java.sql.ResultSet;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
public interface OrderService {
    void save(JsonObject custom);
    ResultSet get(String custcode);
    String dumpTable();
}
