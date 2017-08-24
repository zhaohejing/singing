package com.efan.controller;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.OrderTime;
import com.efan.controller.inputs.*;
import com.efan.core.page.*;
import com.efan.core.primary.Order;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
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
        BaseResponse result= _orderService.GetRemoteList(input);
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
    public ActionResult CreateOrder(@RequestBody OrderInput input) throws  Exception{
      /*  boolean canCreate=_orderService.VilidateOrder(input.boxId,input.fromTime,input.toTime);
        if(!canCreate){
            return  new ActionResult(false,"当前时段已有订单");
        }*/
        try {
            Order res=_orderService.CreateOrder(input);
            return  new ActionResult(res);
        }catch (Exception e){
            return  new ActionResult(false,e.getMessage());
        }
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
        BaseResponse list=_orderService.GetOrderTypeList(input.isRemote,input.boxId);
             return  new ActionResult(list);
     }

    /**
     * 通知机器是否可以开唱*/
    @ApiOperation(value="通知机器是否可以开唱", notes="远程购买接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "CanSingInput")
    @RequestMapping(value  ="/cansingit" ,method = RequestMethod.POST)
    public  ActionResult CanSingIt(@RequestBody CanSingInput input) throws JSONException{
       String device=_orderService.ChangeToRoomId(input.machineId);
       ActionResult result=null;
       if (device.isEmpty()){
           result=  new ActionResult(false,null ,"设备id转换失败！" );
           result.setCode(-1);
           return  result;
       }
        List<Order> temp =_orderService.FindByFilter(device,input.openId);
        if (temp!=null&&temp.size()>0){
            result=  new ActionResult(false,null ,"当前时间段已被别人预定！" );
            result.setCode(-3);
            return  result;
        }
      Order model   =_orderService.GetOrderDetail(input.openId,device);
     BaseResponse r=   _orderService.GetMachineInfo(device);
      if (model==null){
          result=  new ActionResult(false,input.url ,"请来预定吧"   );
          result.setCode(-2);
          result.setMachine(r);
          result.setRoomId(device);
          return  result;
      }
      if ( model.getState()!=1){
          result=  new ActionResult(false,input.url ,"请来预定吧"   );
          result.setCode(-2);
          result.setMachine(r);
          result.setRoomId(device);
          return  result;
      }else if(!model.getUserKey().equals(input.openId)){
          result=  new ActionResult(false,null ,"当前时间段已被别人预定！" );
          result.setCode(-3);
          return  result;
      }

      //通知开平
        model.setBoxId(input.machineId);
     ObjectResponse temp1=  _orderService.TalkSingIt(model);

   // _orderService.OutProductIn(model);
        BodyResponse temp2=  _orderService.OutProductInAsync(temp1,model);
        if(temp1.operation.equals("ok")){
            result=  new ActionResult(true,model.getOrderNum(),"请去演唱吧");
            result.setCode(1);
        }else{
            result=  new ActionResult(true,model.getOrderNum(),"开屏失败，联系管理员");
            result.setCode(0);
        }

        return  result;
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
