package com.efan.appservice.iservice;

import com.efan.controller.dtos.OrderTime;
import com.efan.controller.inputs.*;
import com.efan.core.page.*;
import com.efan.core.primary.Order;
import org.json.JSONException;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *接口列表
 */
public interface IOrderService {
    BaseResponse GetRemoteList(RemoteInput input) ;
    /**
     * 获取包厢列表
     * */
    ListResponse GetCoupeList(String remoteId) ;
     //获取预定列表
    List<OrderTime> GetOrderList(String boxId, Date date);
    BaseResponse GetOrderTypeList(Boolean isRemote, String boxId);
    Order CreateOrder(OrderInput input) throws Exception;
    Order GetOrderDetail(String openId,String machineId);
    ResultModel<Order> GetMyOrders(BaseInput input);
      boolean vilidatePay(ValidatePayInput input);
    boolean VilidateOrder(String machineCode,Date from ,Date to );
    /** 更新订单状态
     */
     Order UpdateOrderState(OrderStateInput input);
    ObjectResponse TalkSingIt(Order input) throws JSONException,IOException;
    //毁掉
    BodyResponse OutProductInAsync(ObjectResponse response,Order input) throws JSONException;
    String ChangeToRoomId(String deviceCode);
    String ChangeToDevice_code(String room_id);
    List<Order> FindByFilter(String boxId,String openId);
    BaseResponse GetMachineInfo(String boxId);
    /*获取预定订单列表*/
     List<OrderTime> GetOrderListAsync(String boxId, Date date);
    void  PayOffOrder(String order);
}
