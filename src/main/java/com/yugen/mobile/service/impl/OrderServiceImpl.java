package com.yugen.mobile.service.impl;

import com.google.gson.JsonObject;
import com.yugen.mobile.hsqldb.DBManger;
import com.yugen.mobile.hsqldb.SqlBuilder;
import com.yugen.mobile.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
@Service
public class OrderServiceImpl extends AbstractService implements OrderService {
    private static final String ORDER_TABLE = "cloud_order";
    private static final String CREATE_ORDER = "CREATE TABLE " + ORDER_TABLE + " (" +
            "        applyno VARCHAR(64)," +
            "        ecordercode VARCHAR(32) PRIMARY KEY," +
            "        opttype INTEGER," +
            "        trial BOOLEAN," +
            "        bossorderid VARCHAR(64)," +
            "        custcode VARCHAR(32)," +
            "        productcode VARCHAR(32)," +
            "        productparas VARCHAR(8192)," +
            "        services VARCHAR(8192)," +
            "        begintime BIGINT," +
            "        endtime BIGINT" +
            ")";

    private static final String INSERT_ORDER = "INSERT INTO " + ORDER_TABLE + " (applyno, ecordercode, opttype, " +
            " trial, bossorderid, custcode, productcode, productparas, services, begintime, endtime)";

    private static final String UPDATE_ORDER = "UPDATE " + ORDER_TABLE + " SET ";
    private static final String QUERY_BY_KEY = "SELECT * FROM " + ORDER_TABLE + " WHERE ecordercode = ";
    private static final String DUMP_ORDER = "SELECT * FROM " + ORDER_TABLE + " ";

    @Override
    public void save(JsonObject order) {
        boolean exist = DBManger.isExsit(QUERY_BY_KEY + order.get("custcode").getAsString());
        internalSave(exist, order);
    }

    @Override
    public ResultSet get(String key) {
        return DBManger.executeQuery(QUERY_BY_KEY + key);
    }

    @Override
    public String dumpTable() {
        return DBManger.dump(DUMP_ORDER);
    }

    protected void create(JsonObject order) {
        String sql = SqlBuilder.build(INSERT_ORDER)
                .valueBegin(order)
                .setString("applyno")
                .setString("ecordercode")
                .setInteger("opttype")
                .setBoolean("trial")
                .setString("bossorderid")
                .setString("custcode")
                .setString("productcode")
                .setString("productparas")
                .setString("services")
                .setLong("begintime")
                .setLong("endtime")
                .valuesEnd().toString();

        DBManger.executeUpdate(sql);
    }

    protected void update(JsonObject order) {
        String sql = SqlBuilder.build(UPDATE_ORDER)
                .updateBegin(order)
                .updateString("applyno")
                .updateInteger("opttype")
                .updateBoolean("trial")
                .updateString("bossorderid")
                .updateString("custcode")
                .updateString("productcode")
                .updateString("productparas")
                .updateString("services")
                .updateLong("begintime")
                .updateLong("endtime")
                .updateEnd()
                .where("ecordercode = ").setString("ecordercode")
                .toString();

        DBManger.executeUpdate(sql);
    }




}
