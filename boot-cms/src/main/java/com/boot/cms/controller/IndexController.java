package com.boot.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.cms.base.BaseController;
import com.boot.cms.entity.User.UserEntity;
import com.boot.cms.service.IUserService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/9
 */
@RestController
public class IndexController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * controller测试
     * @return
     */
    @RequestMapping(value = "/test")
    public String test(String test){
        return "the content you input is  ===== " + test;
    }

    @RequestMapping(value = "/getUser")
    public UserEntity getUser(UserEntity userEntity){

//        LOG.info(JSONObject.toJSONString(result));
        UserEntity user = userService.queryById(userEntity.getId());
//        LOG.info(user.toString());
        return user;
    }
}
