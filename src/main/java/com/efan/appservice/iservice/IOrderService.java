package com.efan.appservice.iservice;

import com.efan.controller.dtos.OrderTime;
import com.efan.controller.inputs.*;
import com.efan.core.page.ListResponse;
import com.efan.core.page.ObjectResponse;
import com.efan.core.page.ResultModel;
import com.efan.core.primary.Order;
import org.json.JSONException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *接口列表
 */
public interface IOrderService {
    ObjectResponse GetRemoteList(RemoteInput input) ;
    /**
     * 获取包厢列表
     * */
    ListResponse GetCoupeList(String remoteId) ;
     //获取预定列表
    List<OrderTime> GetOrderList(String boxId, Date date);
    ObjectResponse GetOrderTypeList(Boolean isRemote, String boxId);
    Order CreateOrder(OrderInput input) throws Exception;
    Order GetOrderDetail(String openId,String machineId);
    ResultModel<Order> GetMyOrders(BaseInput input);
      boolean vilidatePay(ValidatePayInput input);
    boolean VilidateOrder(String machineCode,Date from ,Date to );
    /** 更新订单状态
     */
     Order UpdateOrderState(OrderStateInput input);
    ObjectResponse TalkSingIt(Order input) throws JSONException;
    //毁掉
    ObjectResponse OutProductInAsync(Order input) throws JSONException;
    String ChangeToRoomId(String deviceCode);
    String ChangeToDevice_code(String room_id);
    List<Order> FindByFilter(String boxId,String openId);
}
