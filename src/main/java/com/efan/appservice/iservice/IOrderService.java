package com.efan.appservice.iservice;

import com.efan.controller.dtos.OrderTime;
import com.efan.controller.dtos.OrderType;
import com.efan.controller.dtos.RemoteDto;
import com.efan.controller.inputs.OrderDetailInput;
import com.efan.controller.inputs.OrderInput;
import com.efan.core.entity.Order;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *接口列表
 */
public interface IOrderService {
     ResultModel<RemoteDto> GetRemoteList(String pointName, PageModel input) ;
    /**
     * 获取包厢列表
     * */
     ResultModel<RemoteDto> GetCoupeList(Integer remoteId, PageModel input) ;
     //获取预定列表
    List<OrderTime> GetOrderList(Integer boxId, Date date);
    List<OrderType> GetOrderTypeList(Boolean isRemote, Integer boxId);
    Order CreateOrder(OrderInput input);
    Order GetOrderDetail(OrderDetailInput input);
}
