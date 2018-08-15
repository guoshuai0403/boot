package com.example.demo.enums;

import com.alibaba.fastjson.JSONObject;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
public enum Gender {

    MAN("男"),
    WOMAN("女");

    /** 描述 */
    private String comment;

    Gender(String comment){
        this.comment = comment;
    }

    /** 获取描述信息 */
    public String getComment(){
        return this.comment;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
