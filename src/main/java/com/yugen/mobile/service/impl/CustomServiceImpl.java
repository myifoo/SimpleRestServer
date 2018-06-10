package com.yugen.mobile.service.impl;

import com.google.gson.JsonObject;
import com.yugen.mobile.hsqldb.DBManger;
import com.yugen.mobile.hsqldb.SqlBuilder;
import com.yugen.mobile.service.CustomService;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
@Service
public class CustomServiceImpl extends AbstractService implements CustomService {
    private static final String CUSTOMER_TABLE = "cloud_customer";
    private static final String CREATE_CUSTOMER = "CREATE TABLE " + CUSTOMER_TABLE + " (" +
            "    CUSTID INTEGER, " +
            "    CUSTCODE VARCHAR(32) PRIMARY KEY, " +
            "    CUSTTYPE INTEGER, " +
            "    REGISTERSOURCE INTEGER, " +
            "    CUSTNAME VARCHAR(255), " +
            "    USERID VARCHAR(32)," +
            "    USERNAME VARCHAR(32), " +
            "    USERALIAS VARCHAR(32), " +
            "    MOBILE VARCHAR(15), " +
            "    EMAIL VARCHAR(100)" +
            ")";

    private static final String INSERT_CUSTOMER = "INSERT INTO " + CUSTOMER_TABLE + " (custid, custcode, custtype, " +
            " registersource, custname, userid, username, useralias, mobile, email)";
    private static final String UPDATE_CUSTOMER = "UPDATE " + CUSTOMER_TABLE + " SET ";
    private static String QUERY_BY_KEY = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE custcode = ";
    private static final String DUMP_CUSTOMER = "SELECT * FROM " + CUSTOMER_TABLE + " ";

    @Override
    public void save(JsonObject custom) {
        boolean exist = DBManger.isExsit(QUERY_BY_KEY + custom.get("custcode").getAsString());
        internalSave(exist, custom);
    }

    @Override
    public ResultSet get(String key) {
        return DBManger.executeQuery(QUERY_BY_KEY + key);
    }

    @Override
    public String dumpTable() {
        return DBManger.dump(DUMP_CUSTOMER);
    }

    protected void create(JsonObject custom) {
        String sql = SqlBuilder.build(INSERT_CUSTOMER)
                .valueBegin(custom)
                .setInteger("custid")
                .setString("custcode")
                .setInteger("custtype")
                .setInteger("registersource")
                .setString("custname")
                .setString("userid")
                .setString("username")
                .setString("useralias")
                .setString("mobile")
                .setString("email")
                .valuesEnd().toString();

        DBManger.executeUpdate(sql);
    }

    protected void update(JsonObject custom) {
        String sql = SqlBuilder.build(UPDATE_CUSTOMER)
                .updateBegin(custom)
                .updateInteger("custid")
                .updateInteger("custtype")
                .updateInteger("registersource")
                .updateString("custname")
                .updateString("userid")
                .updateString("username")
                .updateString("useralias")
                .updateString("mobile")
                .updateString("email")
                .updateEnd()
                .where("custcode = ").setString("custcode")
                .toString();

        DBManger.executeUpdate(sql);
    }


}
