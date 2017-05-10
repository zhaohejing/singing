package com.efan.appservice.service;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.OrderTime;
import com.efan.controller.dtos.OrderType;
import com.efan.controller.dtos.RemoteDto;
import com.efan.core.entity.Order;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;
import com.efan.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.*;

/**
 * 购买订单相关接口
 */
@Service
public class OrderService implements IOrderService {
    private IOrderRepository _orderRepository;
    @Autowired
     public OrderService(IOrderRepository orderRepository){
         this._orderRepository=orderRepository;
     }
     /**
      * 获取门店列表
     * */
    public ResultModel<RemoteDto> GetRemoteList(String pointName, PageModel input) {
         List<RemoteDto> result = new ArrayList<RemoteDto>();
        result.add(new RemoteDto(1,"大望路1","大望路36号1",11,0));
        result.add(new RemoteDto(2,"大望路2","大望路36号2",22,0));
        result.add(new RemoteDto(3,"大望路3","大望路36号3",33,0));
        result.add(new RemoteDto(4,"大望路4","大望路36号4",44,0));
        result.add(new RemoteDto(5,"大望路5","大望路36号5",55,0));
        result.add(new RemoteDto(6,"大望路6","大望路36号6",66,0));
        result.add(new RemoteDto(7,"大望路7","大望路36号7",77,0));
        result.add(new RemoteDto(8,"大望路8","大望路36号8",88,0));
        return  new ResultModel<RemoteDto>(result,(long)result.size());
    }
    /**
     * 获取包厢列表
     * */
    public ResultModel<RemoteDto> GetCoupeList(Integer remoteId, PageModel input) {
        List<RemoteDto> result = new ArrayList<RemoteDto>();
        result.add(new RemoteDto(1,"box1","大望路36号1",11,1));
        result.add(new RemoteDto(2,"box2","大望路36号2",22,1));
        result.add(new RemoteDto(3,"box3","大望路36号3",33,1));
        result.add(new RemoteDto(4,"box4","大望路36号4",44,1));
        result.add(new RemoteDto(5,"box5","大望路36号5",55,1));
        result.add(new RemoteDto(6,"box6","大望路36号6",66,1));
        result.add(new RemoteDto(7,"box7","大望路36号7",77,1));
        result.add(new RemoteDto(8,"box8","大望路36号8",88,1));
        return  new ResultModel<RemoteDto>(result,(long)result.size());
    }

    private  Date GenderTime(Date time,Boolean isstart){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        if (isstart){
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
        }else   {
            calendar.set(Calendar.HOUR,23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);
            calendar.set(Calendar.MILLISECOND,999);
        }
        return  calendar.getTime();
    }
    /*获取预定订单列表*/
    public List<OrderTime> GetOrderList(Integer boxId, Date date){
        Date start=GenderTime(date,true);
        Date end=GenderTime(date,false);

        List<OrderTime> result=new ArrayList<OrderTime>();
          List<Order> list= _orderRepository.findOrders(boxId,start,end);
        for (int i = 0; i <24 ; i++) {
            Integer count=0;
            for (int j = 0; j < list.size(); j++) {
                Order temp=list.get(j);
               Timestamp from = temp.getFromTime();
                Integer hour=  from.getHours();
                Integer min=  from.getMinutes();
                Timestamp to = temp.getToTime();
                Integer tohour=  to.getHours();
                Integer tomin=  to.getMinutes();
                if (i==hour){
                    if (i==tohour){
                        count+=(tomin-min);
                    }else {
                        count+=(60-min);
                    }
                    }
                    if (i==tohour){
                        if (hour<tohour){
                            count+=tomin;
                        }
                    }
            }
            result.add(new OrderTime(i,i+1,count));
        }
        return  result;
    }
///根据lexington获取套餐详情
     public  List<OrderType> GetOrderTypeList(Boolean isRemote,Integer boxId){
            List<OrderType> result=new ArrayList<OrderType>();
         result.add(new OrderType("28分钟",60.0D,36.0D));
         result.add(new OrderType("38分钟",80.0D,42.0D));
         result.add(new OrderType("48分钟",100.0D,48.0D));
         result.add(new OrderType("58分钟",120.0D,55.0D));
return  result;
     }


}
