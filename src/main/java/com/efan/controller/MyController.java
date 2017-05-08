package com.efan.controller;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.controller.dtos.MyTapeDto;
import com.efan.core.entity.MyTape;
import com.efan.core.page.ActionResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 我的接口列表
 */
@RestController
@RequestMapping("/api/my")
public class MyController {
    private IMyTapeService _mytapeService;
    @Autowired
    public MyController(IMyTapeService mytapeService){
        this._mytapeService=mytapeService;

    }
    /*获取我的歌单列表*/
    @ApiOperation(value="获取当前用户的歌单列表", notes="我的模块接口")
    @ApiImplicitParam(name = "openId", value = "用户openId", required = true, dataType = "String")
    @RequestMapping(value  ="/songs" ,method = RequestMethod.POST)
    public ActionResult MySongs(String openId){
        return  new ActionResult();
    }


    /*创建我的原唱歌曲*/
    @ApiOperation(value="创建我的原唱歌曲", notes="我的模块接口")
    @RequestMapping(value  ="/modifytape" ,method = RequestMethod.POST)
    public ActionResult ModifyTape(@RequestBody MyTapeDto input){
         MyTape result=_mytapeService.ModifyMyTape(input);
         return  new ActionResult(result);
    }
    /*通知设备上传文件并获取到唯一id*/
    @ApiOperation(value="通知设备上传文件并获取到唯一id", notes="我的模块接口")
    @RequestMapping(value  ="/notifyupload" ,method = RequestMethod.POST)
    public ActionResult NotifyUploadSongs(@RequestBody MyTapeDto input){
        MyTape result=_mytapeService.ModifyMyTape(input);
        return  new ActionResult(result);
    }
}
