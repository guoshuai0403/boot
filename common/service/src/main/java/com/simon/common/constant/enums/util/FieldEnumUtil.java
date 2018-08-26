package com.simon.common.constant.enums.util;

import com.simon.common.constant.enums.IBaseEnum;

/**
 * Created by guoshuai on 2018/6/3 0003.
 */
public class FieldEnumUtil {

    /**
     * 根据code值获取枚举常量
     * @param enumClass
     * @param code
     * @param <E>
     * @return
     */
    public static <E extends Enum<?> & IBaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
