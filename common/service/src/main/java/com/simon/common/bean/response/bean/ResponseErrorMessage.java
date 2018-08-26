package com.simon.common.bean.response.bean;

import com.simon.common.util.format.date.DateUtil;

import java.sql.Timestamp;

/**
 * 当请求出错时返回的对象
 * Created by guoshuai on 2017/10/13.
 */
public class ResponseErrorMessage {

    // 返回码，0 - 正常，负数 - 错误
    private Integer code;

    // 返回给用户看的信息
    private String msg;

    // url 服务器维护时给移动端的跳转地址
    private String url;

    // 响应时间
    private Timestamp responsetime;

    // 有参构造器
    public ResponseErrorMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }



    // 无参构造器
    public ResponseErrorMessage() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 默认返回当前时间
    public Timestamp getResponsetime() {
        if (responsetime == null) {

            responsetime = DateUtil.currentTimestamp();
        }
        return responsetime;
    }

    public void setResponsetime(Timestamp responsetime) {
        this.responsetime = responsetime;
    }
}
