package com.efan.controller;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.core.primary.MySongs;
import com.efan.core.page.ActionResult;
import com.efan.core.page.FilterModel;
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
    /*获取我的歌单列表*/
    @ApiOperation(value="获取我的歌单列表", notes="我的模块接口")
    @ApiImplicitParam(name = "input", value = "{filter:过滤条件,index:页码,size:页容量 }", required = true, dataType = "FilterModel")
    @RequestMapping(value  ="/mysongs" ,method = RequestMethod.POST)
    public ActionResult MySongs(@RequestBody FilterModel input){
        ResultModel<MySongs> result=_mytapeService.GetMySongsList(input);
        return  new ActionResult(result);
    }
}
