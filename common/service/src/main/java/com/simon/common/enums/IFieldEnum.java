package com.simon.common.enums;

import java.io.Serializable;

/**
 * 字段枚举类需要实现的接口
 * Created by guoshuai on 2018/8/24 0024.
 */
public interface IFieldEnum extends Serializable {

    int getCode();

    String getDes();
}
