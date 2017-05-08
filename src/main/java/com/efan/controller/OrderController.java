package com.efan.controller;

import com.efan.core.page.ActionResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 远程购买接口表
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    public OrderController(){

    }
/*获取门店列表*/
    @ApiOperation(value="获取门店列表", notes="远程购买接口")
    @ApiImplicitParam(name = "point", value = "点位坐标", required = true, dataType = "String")
    @RequestMapping(value  ="/stores" ,method = RequestMethod.POST)
    public ActionResult RemoteBuy(String point){
        return  new ActionResult();
    }
    /*获取包房信息*/
    @ApiOperation(value="获取包房信息", notes="远程购买接口")
    @ApiImplicitParam(name = "id", value = "点位id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/coupe" ,method = RequestMethod.POST)
    public ActionResult CoupeList(Integer id){
        return  new ActionResult();
    }
    /*获取包房预定列表*/
    @ApiOperation(value="获取包房预定清单", notes="远程购买接口")
    @ApiImplicitParam(name = "id", value = "包房id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/booking" ,method = RequestMethod.POST)
    public ActionResult Booking(Integer id){
        return  new ActionResult();
    }

    /*支付接口*/
    @ApiOperation(value="调用支付", notes="远程购买接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "包房id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, dataType = "String"),
            })
    @RequestMapping(value  ="/payfor" ,method = RequestMethod.POST)
    public ActionResult PayFor(String orderId,String openId){
        return  new ActionResult();
    }



}
