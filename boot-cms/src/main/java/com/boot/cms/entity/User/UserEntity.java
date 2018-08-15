package com.boot.cms.entity.User;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/9
 */
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

//    @NotNull(message = "名称不能为null")
    private String name;

    private String userPassword;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
