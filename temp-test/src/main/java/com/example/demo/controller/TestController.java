package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.validation.constraints.MyRegexp;
import com.example.demo.constant.RegexpEnum;
import com.example.demo.model.MenuModel;
import com.example.demo.model.UserModel;
import com.example.demo.util.ftp.FtpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Fidelity;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
@RestController
public class TestController {

    private static final Log logger = LogFactory.getLog(TestController.class);

    /**
     * 参数验证测试方法
     * @param userModel
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/test")
    public String test(@Validated UserModel userModel, BindingResult bindingResult){

        logger.info(userModel);

        logger.info(bindingResult);

        return "test";
    }

    @RequestMapping(value = "/test1")
    public String test1(@Validated({Default.class, UserModel.Woman.class}) UserModel userModel, BindingResult bindingResult,
                        @Validated MenuModel menuModel, BindingResult result){

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        for (ObjectError error : allErrors) {
            if (error instanceof  FieldError) {

                System.out.println(bindingResult.getObjectName());

                System.out.println(((FieldError) error).getField());

                System.out.println(error.getDefaultMessage());

                System.out.println(((FieldError) error).getRejectedValue());

                System.out.println(error.getCode());

                System.out.println("---------------------------------------------------");
            }
        }

        List<ObjectError> menuAllErrors = result.getAllErrors();

        for (ObjectError error : menuAllErrors) {
            if (error instanceof  FieldError) {

                System.out.println(result.getObjectName());

                System.out.println(((FieldError) error).getField());

                System.out.println(error.getDefaultMessage());

                System.out.println(((FieldError) error).getRejectedValue());

                System.out.println(error.getCode());

                System.out.println("---------------------------------------------------");
            }
        }



        return "test";
    }

    @RequestMapping(value = "/test2")
    public String test2(@Validated MenuModel menuModel, BindingResult result){
        List<ObjectError> allErrors = result.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                System.out.println(error);
                System.out.println(error.getDefaultMessage());
                System.out.println(((FieldError) error).getField());
            }
        }
        return "result";
    }

    @RequestMapping("/test3")
    public String test3(String email){

        System.out.println("the param is === "+email);
//        this.checkParam(result);
        return "test3";
    }

    private String checkParam(BindingResult result){
        List<ObjectError> allErrors = result.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                System.out.println(error);
                System.out.println(error.getDefaultMessage());
                System.out.println(((FieldError) error).getField());
            }
        }
        return "";
    }

    @RequestMapping(value = "/upload")
    private String upload(){
        try {
            File file = new File("C:/Users/40805/Desktop/TIM图片20180727175133.png");
            InputStream inputStream = new FileInputStream(file);
            for (int i = 0; i < 3; i++) {
                String filepath = "/" + i;
                FtpUtil.uploadFile("/media/file", filepath, "asdf.png", inputStream);
            }
            return "asdfsdf";
        } catch (Exception e) {
            e.printStackTrace();
            return "123123";
        }
    }
}
