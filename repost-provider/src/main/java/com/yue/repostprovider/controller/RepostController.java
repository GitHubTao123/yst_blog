package com.yue.repostprovider.controller;

import com.yue.repostprovider.entity.Users;
import com.yue.repostprovider.service.RepostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Log4j2
@Api(value = "RepostController",tags = {"repost"})
@RestController
public class RepostController {

    @Autowired
    private RepostService repostService;

    @ApiOperation(value = "转发并留言")
    @RequestMapping("/repost")
    public String repost(@PathParam("commId")int commId,@PathParam("repCont") String repCont,@PathParam("loginUserId") int loginUserId){
        repostService.repost(commId,repCont,loginUserId);
        return "";
    }
}
