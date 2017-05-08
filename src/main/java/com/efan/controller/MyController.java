package com.efan.controller;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.controller.dtos.MyTapeDto;
import com.efan.core.entity.MyTape;
import com.efan.core.page.ActionResult;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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


    /*创建我的原唱歌曲*/
    @ApiOperation(value="创建我的原唱歌曲", notes="我的模块接口")
    @RequestMapping(value  ="/insertTape" ,method = RequestMethod.POST)
    public ActionResult InsertTape(@RequestBody MyTapeDto input){
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
    /*修改我的歌单文件上传状态*/
    @ApiOperation(value="修改我的歌单文件上传状态", notes="我的模块接口")
    @RequestMapping(value  ="/updatemytapestate" ,method = RequestMethod.POST)
    public ActionResult UpdateMyTapeState(@RequestParam Long id){
        MyTape result=_mytapeService.UpdateMyTapeState(id);
        return  new ActionResult(result);
    }
    /*获取我的歌单列表*/
    @ApiOperation(value="获取我的歌单列表", notes="我的模块接口")
    @RequestMapping(value  ="/mytapes" ,method = RequestMethod.POST)
    public ActionResult MyTapes(@RequestBody PageModel input){
        ResultModel<MyTape> result=_mytapeService.GetMyTapeList(input);
        return  new ActionResult(result);
    }
}
