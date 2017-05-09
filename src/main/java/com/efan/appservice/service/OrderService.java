package com.efan.appservice.service;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.RemoteDto;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;
import com.efan.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
