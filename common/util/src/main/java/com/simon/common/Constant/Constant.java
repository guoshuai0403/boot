package com.simon.common.Constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.io.Serializable;

/**
 * description: 常量池
 *
 * @auth guoshuai
 * @since 2018/8/27
 */
public interface Constant extends Serializable{

    Log log = LogFactory.getLog(Thread.currentThread().getClass());

    /** 字符串utf-8编码 */
    String ENCODING_UTF8 = "UTF-8";
}
