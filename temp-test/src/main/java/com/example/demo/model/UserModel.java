package com.example.demo.model;

import com.example.demo.annotation.validation.constraints.MyRegexp;
import com.example.demo.constant.RegexpEnum;
import com.example.demo.enums.Gender;
import sun.misc.Regexp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
public class UserModel {


    private int id;

    private String name;

    private String password;

    @MyRegexp(RegexpEnum.EMAIL)
    private String email;

    private Gender gender;

    @MyRegexp(RegexpEnum.PHONE)
    private String phone;

    @Min(value = 18, groups = {Adult.class, Woman.class})
    private int age;

    public interface Adult {}

    public interface Woman {}



















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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
