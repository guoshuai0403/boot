package com.simon.generator.common.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理 配置
 * Created by guoshuai on 2018/8/28 0028.
 */
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpServletRequest request, Exception e){
        Log log = LogFactory.getLog(GlobalDefultExceptionHandler.class);
        log.error(e.getMessage());
        return "error";
    }
}
