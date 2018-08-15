package com.example.demo.model;

import com.example.demo.annotation.validation.constraints.MyRegexp;
import com.example.demo.constant.RegexpEnum;
import sun.misc.Regexp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
public class MenuModel {

    private int id;

    private String name;

    private String manager;

    @MyRegexp(RegexpEnum.PHONE)
    private String mangerPhone;

    private Date foundingDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMangerPhone() {
        return mangerPhone;
    }

    public void setMangerPhone(String mangerPhone) {
        this.mangerPhone = mangerPhone;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }
}
