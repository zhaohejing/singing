package com.efan.appservice.iservice;

import com.efan.controller.dtos.OrderTime;
import com.efan.controller.dtos.OrderType;
import com.efan.controller.dtos.RemoteDto;
import com.efan.controller.inputs.OrderDetailInput;
import com.efan.controller.inputs.OrderInput;
import com.efan.controller.inputs.RemoteInput;
import com.efan.core.entity.Order;
import com.efan.core.page.PageModel;
import com.efan.core.page.Response;
import com.efan.core.page.ResultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *接口列表
 */
public interface IOrderService {
    Response GetRemoteList(RemoteInput input) ;
    /**
     * 获取包厢列表
     * */
    Response GetCoupeList(String remoteId) ;
     //获取预定列表
    List<OrderTime> GetOrderList(String boxId, Date date);
    Response GetOrderTypeList(Boolean isRemote, String boxId);
    Order CreateOrder(OrderInput input);
    Order GetOrderDetail(String orderId);
    String Payfor(String boxId,String orderId);
}
