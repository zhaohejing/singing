package com.efan.controller;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.OrderTime;
import com.efan.controller.dtos.OrderType;
import com.efan.controller.dtos.RemoteDto;
import com.efan.controller.inputs.GetOrderInput;
import com.efan.controller.inputs.GetPayType;
import com.efan.controller.inputs.OrderInput;
import com.efan.core.entity.Order;
import com.efan.core.page.ActionResult;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 远程购买接口表
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private IOrderService _orderService;
    @Autowired
    public OrderController(IOrderService orderService){
        _orderService=orderService;
    }
/*获取门店列表*/
    @ApiOperation(value="获取门店列表", notes="远程购买接口")
    @ApiImplicitParam(name = "point", value = "点位坐标", required = true, dataType = "String")
    @RequestMapping(value  ="/stores" ,method = RequestMethod.POST)
    public ActionResult RemoteBuy(String point){
        ResultModel<RemoteDto> result= _orderService.GetRemoteList(point,new PageModel(1,10));
        return  new ActionResult(result);
    }
    /*获取包房信息*/
    @ApiOperation(value="获取包房信息", notes="远程购买接口")
    @ApiImplicitParam(name = "id", value = "点位id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/coupe" ,method = RequestMethod.POST)
    public ActionResult CoupeList(Integer id){
        ResultModel<RemoteDto> result= _orderService.GetCoupeList(id,new PageModel(1,10));
        return  new ActionResult(result);
    }
    /*获取包房预定列表*/
    @ApiOperation(value="获取包房预定清单", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "GetOrderInput")
    @RequestMapping(value  ="/booking" ,method = RequestMethod.POST)
    public ActionResult Booking(@RequestBody GetOrderInput input){
        List<OrderTime> res =_orderService.GetOrderList(input.boxId, input.date);
        return  new ActionResult(res);
    }
    /*确认预定创建支付订单*/
    @ApiOperation(value="创建订单", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "OrderInput")
    @RequestMapping(value  ="/createorder" ,method = RequestMethod.POST)
    public ActionResult CreateOrder(@RequestBody OrderInput input){
        Order res=_orderService.CreateOrder(input);
        return  new ActionResult(res);
    }

    /*支付接口*/
    @ApiOperation(value="调用支付", notes="远程购买接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "包房id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, dataType = "String"),
            })
    @RequestMapping(value  ="/payfor" ,method = RequestMethod.POST)
    public ActionResult PayFor(String orderId,String openId){
        return  new ActionResult(1);
    }

/**
 * 获取套餐详情*/
    @ApiOperation(value="获取套餐详情列表", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "GetPayType")
    @RequestMapping(value  ="/getpaylist" ,method = RequestMethod.POST)
     public  ActionResult GetPayList(@RequestBody GetPayType input){
        List<OrderType> list=_orderService.GetOrderTypeList(input.isRemote,input.boxId);
             return  new ActionResult(list);
     }

}
