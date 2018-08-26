package com.simon.generator.bean;

import com.simon.generator.bean.enums.DataBasePosition;
import com.simon.generator.bean.enums.DataBaseType;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * 数据库连接相关信息
 * Created by guoshuai on 2018/8/24 0024.
 */
@Controller
public class ConnectBean implements Serializable {

    /**
     * 数据库类型
     */
    private DataBaseType type;

    /**
     * 数据库位置
     */
    private DataBasePosition position;

    /**
     * 数据库ip
     */
    private String ip;

    /**
     * 数据库端口号
     */
    private Integer port;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    public DataBaseType getType() {
        return type;
    }

    public void setType(DataBaseType type) {
        this.type = type;
    }

    public DataBasePosition getPosition() {
        return position;
    }

    public void setPosition(DataBasePosition position) {
        this.position = position;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
