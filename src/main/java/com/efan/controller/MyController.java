package com.efan.controller;

import com.efan.core.page.ActionResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 我的接口列表
 */
@RestController
@RequestMapping("/api/my")
public class MyController {
    @Autowired
    public MyController(){

    }
    /*获取我的歌单列表*/
    @ApiOperation(value="获取当前用户的歌单列表", notes="我的模块接口")
    @ApiImplicitParam(name = "openId", value = "用户openId", required = true, dataType = "String")
    @RequestMapping(value  ="/songs" ,method = RequestMethod.POST)
    public ActionResult MySongs(String openId){
        return  new ActionResult();
    }
}
