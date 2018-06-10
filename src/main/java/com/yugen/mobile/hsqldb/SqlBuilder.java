package com.yugen.mobile.hsqldb;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Description:
 * <p>
 * Created by A.T on 2018/06/09
 */
public class SqlBuilder {
    private StringBuilder sql;
    private JsonObject values;

    public SqlBuilder(String sql) {
        this.sql = new StringBuilder(sql);
    }

    public SqlBuilder(String sql, JsonObject values) {
        this.sql = new StringBuilder(sql);
        this.values = values;
    }

    static public SqlBuilder build(String sql) {
        return new SqlBuilder(sql);
    }

    static public SqlBuilder build(String sql, JsonObject values) {
        return new SqlBuilder(sql, values);
    }

    public SqlBuilder valueBegin(JsonObject values) {
        this.values = values;
        sql.append(" values (");
        return this;
    }

    public SqlBuilder valuesEnd() {
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");

        return this;
    }

    public SqlBuilder updateBegin(JsonObject values) {
        this.values = values;
        return this;
    }

    public SqlBuilder updateEnd() {
        sql.deleteCharAt(sql.length()-1);
        return this;
    }

    public SqlBuilder setInteger(String key) {
        sql.append(values.get(key).getAsInt()+",");
        return this;
    }

    public SqlBuilder setLong(String key) {
        sql.append(values.get(key).getAsLong()+",");
        return this;
    }

    public SqlBuilder setString(String key) {

        String value;
        if(values.get(key).isJsonPrimitive())
            value = values.get(key).getAsString();
        else
            value = values.get(key).toString();

        sql.append("\'").append(value).append("\',");
        return this;
    }

    public SqlBuilder setBoolean(String key) {
        sql.append(values.get(key).getAsBoolean()+",");
        return this;
    }

    public SqlBuilder updateInteger(String key) {
        sql.append(key)
                .append(" = ")
                .append(values.get(key).getAsInt()+",");

        return this;
    }

    public SqlBuilder updateLong(String key) {
        sql.append(key)
                .append(" = ")
                .append(values.get(key).getAsLong()+",");

        return this;
    }

    public SqlBuilder updateString(String key) {
        String value;
        if(values.get(key).isJsonPrimitive())
            value = values.get(key).getAsString();
        else
            value = values.get(key).toString();

        sql.append(key)
                .append(" = ")
                .append("\'")
                .append(value)
                .append("\',");

        return this;
    }

    public SqlBuilder updateBoolean(String key) {
        sql.append(key)
                .append(" = ")
                .append(values.get(key).getAsBoolean()+",");
        return this;
    }

    public SqlBuilder where(String condition) {
        sql.append(" where " + condition);
        return this;
    }

    @Override
    public String toString() {
        return sql.toString();
    }



}
