package com.efan.controller;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.OrderTime;
import com.efan.controller.inputs.*;
import com.efan.core.page.ListResponse;
import com.efan.core.page.ObjectResponse;
import com.efan.core.primary.Order;
import com.efan.core.page.ActionResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "RemoteInput")
    @RequestMapping(value  ="/stores" ,method = RequestMethod.POST)
    public ActionResult RemoteBuy(@RequestBody RemoteInput input){
        ObjectResponse result= _orderService.GetRemoteList(input);
        return  new ActionResult(result);
    }
    /*获取包房信息*/
    @ApiOperation(value="获取包房信息", notes="远程购买接口")
    @ApiImplicitParam(name = "spot", value = "点位对象id", required = true, dataType = "GetSpotInput")
    @RequestMapping(value  ="/coupe" ,method = RequestMethod.POST)
    public ActionResult CoupeList(@RequestBody GetSpotInput spot){
        ListResponse result= _orderService.GetCoupeList(spot.spotId);
        return  new ActionResult(result);
    }
    /*获取包房预定列表*/
    @ApiOperation(value="获取到店包房预定清单", notes="到店购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "GetOrderInput")
    @RequestMapping(value  ="/booking" ,method = RequestMethod.POST)
    public ActionResult Booking(@RequestBody GetOrderInput input){
        List<OrderTime> res =_orderService.GetOrderList(input.boxId, input.date);
        return  new ActionResult(res);
    }

    /*创建支付订单*/
    @ApiOperation(value="创建订单", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "OrderInput")
    @RequestMapping(value  ="/createorder" ,method = RequestMethod.POST)
    public ActionResult CreateOrder(@RequestBody OrderInput input){
        boolean canCreate=_orderService.VilidateOrder(input.boxId,input.fromTime,input.toTime);
        if(!canCreate){
            return  new ActionResult(false,"当前时段已有订单");
        }
        Order res=_orderService.CreateOrder(input);
        return  new ActionResult(res);
    }

    /*支付接口*/
    @ApiOperation(value="验证是否可以支付", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "ValidatePayInput")
    @RequestMapping(value  ="/canpay" ,method = RequestMethod.POST)
    public ActionResult PayFor(@RequestBody ValidatePayInput input){
        boolean res= _orderService.vilidatePay(input);
        return  new ActionResult(res);
    }
/**
 * 获取套餐详情*/
    @ApiOperation(value="获取套餐详情列表", notes="到店购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "GetPayType")
    @RequestMapping(value  ="/getpaylist" ,method = RequestMethod.POST)
     public  ActionResult GetPayList(@RequestBody GetPayType input){
        ObjectResponse list=_orderService.GetOrderTypeList(input.isRemote,input.boxId);
             return  new ActionResult(list);
     }

    /**
     * 通知机器是否可以开唱*/
    @ApiOperation(value="通知机器是否可以开唱", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "CanSingInput")
    @RequestMapping(value  ="/cansingit" ,method = RequestMethod.POST)
    public  ActionResult CanSingIt(@RequestBody CanSingInput input) throws JSONException{
        String device=_orderService.ChangeRoomId(input.machineId);
        if (device.isEmpty()){
            return  new ActionResult(false,null ,"设备id转换失败！" );
        }

      Order model   =_orderService.GetOrderDetail(input.openId,device);
      if (model==null){
          return  new ActionResult(false,input.url ,"没有你的订单"   );
      }
      if ( model.getState()!=1){
          return  new ActionResult(false,input.url ,"订单还未支付"   );
      }else if(!model.getUserKey().equals(input.openId)){
          return  new ActionResult(false,null ,"该时段已被其他用户预定！" );
      }

      //通知开平
      _orderService.TalkSingIt(model);
    _orderService.OutProductIn(model);
    _orderService.OutProductInAsync(model   );
        return  new ActionResult(true,model.getOrderNum(),"获取成功,可以开唱");
    }
    /**
     * 支付成功修改订单状态*/
    @ApiOperation(value="支付成功修改订单状态", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "{order:订单号,state:支付状态}", required = true, dataType = "OrderStateInput")
    @RequestMapping(value  ="/paystate" ,method = RequestMethod.POST)
    public  ActionResult UpdatePayState(@RequestBody OrderStateInput input){
        Order model   =_orderService.UpdateOrderState(input);
        return  new ActionResult(model);
    }
}
