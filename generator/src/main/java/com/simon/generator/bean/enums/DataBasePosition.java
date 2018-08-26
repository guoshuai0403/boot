package com.simon.generator.bean.enums;

import com.alibaba.fastjson.JSONObject;
import com.simon.common.enums.IFieldEnum;

/**
 * 数据库位置枚举类
 * Created by guoshuai on 2018/8/24 0024.
 */
public enum DataBasePosition implements IFieldEnum {

    LCOAL(1, "本地"),
    REMOTE(2, "远程");

    private int code;

    private String des;

    DataBasePosition(int code, String des){
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
