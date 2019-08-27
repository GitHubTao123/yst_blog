package com.yue.service_consumer.controller;

import com.yue.service_consumer.entity.Users;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

//@Log4j2
//@Api(value = "repostControllerBefore",tags = {"repostBefore"})
@Controller
public class RepostController {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/repost")
    public void repost(@PathParam("commId") int commId, @PathParam("repCont") String repCont, HttpServletRequest request) {
        Users loginUser = (Users)request.getSession().getAttribute("login_user");
        int loginUserId = loginUser.getUser_id();
        restTemplate.getForObject("http://repost-provider/repost?commId="+commId+"&repCont="+repCont+"&loginUserId="+loginUserId,String.class);
    }
}
