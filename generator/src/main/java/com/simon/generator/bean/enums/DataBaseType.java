package com.simon.generator.bean.enums;

import com.alibaba.fastjson.JSONObject;
import com.simon.common.enums.IFieldEnum;

/**
 * 数据库类型字段枚举
 * Created by guoshuai on 2018/8/24 0024.
 */
public enum DataBaseType implements IFieldEnum {

    MYSQL(1, "mysql数据库"),
    ORACLE(2, "oracle数据库");

    private int code;

    private String des;

    DataBaseType(int code, String des){
        this.code = code;
        this.des = des;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDes() {
        return this.des;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
