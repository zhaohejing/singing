package com.efan.controller;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.controller.dtos.MyTapeDto;
import com.efan.core.entity.MyTape;
import com.efan.core.page.ActionResult;
import com.efan.core.page.FilterModel;
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
    /*获取我的歌单列表*/
    @ApiOperation(value="获取我的歌单列表", notes="我的模块接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "FilterModel")
    @RequestMapping(value  ="/mytapes" ,method = RequestMethod.POST)
    public ActionResult MyTapes(@RequestBody FilterModel input){
        ResultModel<MyTape> result=_mytapeService.GetMyTapeList(input);
        return  new ActionResult(result);
    }
}
