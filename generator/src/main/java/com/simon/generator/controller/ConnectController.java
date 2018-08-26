package com.simon.generator.controller;

import com.simon.common.bean.response.bean.ResponseMessage;
import com.simon.common.controller.BaseController;
import com.simon.generator.bean.ConnectBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by guoshuai on 2018/8/24 0024.
 */
@Controller
@RequestMapping(value = "/connect")
public class ConnectController extends BaseController {

    @RequestMapping(value = "/")
    public String connect(ConnectBean connectBean, HttpSession session){
        session.setAttribute("connectBean", connectBean);
        return "connect";
    }

    /**
     * 测试数据库连接
     * @return
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public ResponseMessage test(ConnectBean connectBean){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }
}
