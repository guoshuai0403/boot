package com.example.demo.constant;

import java.io.Serializable;

/**
 * description: 需要正则表达式验证的参数
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
public enum RegexpEnum implements Serializable {

    PHONE(Regrexp.PHONE, "手机号码格式不正确~"),
    EMAIL(Regrexp.EMAIL, "电子邮箱格式不正确"),
    ;

    /** 正则表达式 */
    private String reg;

    /** 不匹配正则表达式时的描述 */
    private String comment;

    RegexpEnum(String reg, String comment){
        this.reg = reg;
        this.comment = comment;
    }

    /** 获取正则表达式 */
    public String getReg() {
        return reg;
    }

    /** 不匹配正则表达式时的描述 */
    public String getComment() {
        return comment;
    }

    /** 正则表达式定义 */
    private interface Regrexp{

        /** 手机号正则表达式 */
        String PHONE = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

        /** 邮箱正则表达式 */
        String EMAIL = "^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
    }






}
