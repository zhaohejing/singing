package com.efan.appservice.service;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.OrderTime;
import com.efan.controller.inputs.*;
import com.efan.core.page.ChangeResponse;
import com.efan.core.page.ListResponse;
import com.efan.core.page.ObjectResponse;
import com.efan.core.page.ResultModel;
import com.efan.core.primary.Order;
import com.efan.repository.primary.IOrderRepository;
import com.efan.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 购买订单相关接口
 */
@Service
public class OrderService implements IOrderService {
    @Value("${efanurl}")
    private String efanurl;
    @Value("${returnurl}")
    private String returnurl;
    private IOrderRepository _orderRepository;
    private Logger logger = LogManager.getLogger(getClass());
    @Autowired
     public OrderService(IOrderRepository orderRepository){
         this._orderRepository=orderRepository;
     }
     /**
      * 获取门店列表
     * */
    public ObjectResponse GetRemoteList(RemoteInput input) {
        String url=efanurl+"api/getSpotsByCoordinate";
    /*    Map<String,String> parm=new HashMap<>();
        parm.put("longitude",input.y);
        parm.put("latitude",input.x );
        parm.put("page",input.page);*/
        String parm="longitude="+input.y+"&latitude"+input.x+"&page="+input.page;

      String result=  HttpUtils.sendPost(url,parm);
        logger.warn("logitude:"+input.y+",latitude:"+input.x+"result:"+result);
        ObjectResponse res;
        try{
            res =   new Gson().fromJson(result,ObjectResponse.class);
        }catch (Exception e){
            res=new ObjectResponse();
            res.code=1000;
            res.message=result;
        }
        return  res;
    }
    /**
     * 获取包厢列表
     * */
    public ListResponse GetCoupeList(String remoteId)  {
        String url=efanurl+"api/getMachineListBySpot";
        String parms="spot_id="+remoteId;
        String result=  HttpUtils.sendPost(url,parms);
        ListResponse res;
        try{
            res =   new Gson().fromJson(result,ListResponse.class);
        }catch (Exception e){
           res=new ListResponse();
           res.code=1000;
           res.message=e.getMessage();
        }
        if(res.code==200){
               List<Map<String,Object> > temp= res.respBody;
            for (Map<String, Object> aTemp : temp) {
                Object key = aTemp.get("machine_id");
                String time = GetLessTime((String) key);
                aTemp.put("time", time);
            }
            res.respBody=temp;
        }
        return  res;
    }
    private  String GetLessTime(String boxId){
        Date start =GetCurrentDate(true);
        Date end=GetCurrentDate(false);
         List<Order> orders=_orderRepository.findOrders(boxId,start,end);
         Long total=0L;
        for (Order order : orders) {
           // Long min = (order.getToTime().getTime() - order.getFromTime().getTime()) / 1000;
            total += order.getPurchaseTime();
        }
       Long less= (end.getTime()-start.getTime())/1000-total*60;
        if(less>0){
         return  new DecimalFormat("#.00").format(less/3600);
        }
          return  "0";
    }


    /*获取预定订单列表*/
    public List<OrderTime> GetOrderList(String boxId, Date date){
        Calendar now =Calendar.getInstance();
        Date start=GenderTime(date,true);
        Date end=GenderTime(date,false);
        List<OrderTime> result=new ArrayList<>();
          List<Order> list= _orderRepository.findOrders(boxId,start,end);
          Integer nowHour=now.get(Calendar.HOUR_OF_DAY);
        for (int i = 0; i <24 ; i++) {
            if (nowHour>i){
                result.add(new OrderTime(i,i+1,60));
                continue;
            }
            Integer count=0;
            for (Order temp : list) {
                Timestamp from = temp.getFromTime();
                Calendar left = Calendar.getInstance();
                left.setTime(from);
                Integer hour = left.get(Calendar.HOUR_OF_DAY);
                Integer min = left.get(Calendar.MINUTE);

                Timestamp to = temp.getToTime();
                Calendar right = Calendar.getInstance();
                right.setTime(to);

                Integer thor = right.get(Calendar.HOUR_OF_DAY);
                Integer tobin = right.get(Calendar.MINUTE);
                hour+=12;
                thor+=12;
                if (i == hour) {
                    if (i == thor) {
                        count += (tobin - min);
                    } else {
                        count += (60 - min);
                    }
                }
                if (i == thor) {
                    if (hour < thor) {
                        count += tobin;
                    }
                }
            }
            result.add(new OrderTime(i,i+1,count));
        }
        return  result;
    }
///根据lexington获取套餐详情
     public  ObjectResponse GetOrderTypeList(Boolean isRemote,String boxId){
         String url=isRemote?efanurl+"api/getProductsByRoomRemote": efanurl+"api/getProductsByRoom";
         String parms="room_id="+boxId;
         String result=  HttpUtils.sendPost(url,parms);
         ObjectResponse res;
         try{
             res =   new Gson().fromJson(result,ObjectResponse.class);
         }catch (Exception e){
             res=new ObjectResponse();
             res.code=1000;
             res.message=e.getMessage();
         }
         return  res;
     }
     ///获取订单详情
     public  Order GetOrderDetail(String openId,String machineId){

     List<Order> list=   _orderRepository.findOrdersbyFilter(machineId,openId,new Date());
     if(list.size()==0)return null;
         return list.get(0);
     }
     /*获取我的订单*/
    public ResultModel<Order> GetMyOrders(BaseInput input){
        Pageable pageable = new PageRequest(input.getIndex()-1, input.getSize(),null);
        Page<Order> res=  _orderRepository.findAllByUserKey(input.getFilter(), pageable);
        return new ResultModel<>(res.getContent(), res.getTotalElements());

    }
//创建订单并调用支付接口
 @Transactional
    public Order CreateOrder(OrderInput input)  throws Exception {
        Timestamp date = new Timestamp(System.currentTimeMillis());
      UUID num=   java.util.UUID.randomUUID();

      Timestamp start= DateToTimestamp( input.fromTime.getTime()<date.getTime()?date:input.fromTime);


        Calendar c=Calendar.getInstance();
        c.setTime(start);

        if(input.purchaseTime<60){
            c.add(Calendar.MINUTE,input.purchaseTime);
        }else if(input.purchaseTime>=60&&input.purchaseTime<24*60){
          Integer hour=  input.purchaseTime/60;
          c.add(Calendar.HOUR_OF_DAY,hour );
          c.add(Calendar.MINUTE,input.purchaseTime-hour*60);
        }
        Date end= c.getTime() ;
        Timestamp eee=DateToTimestamp(end);
        List<Order> temp=_orderRepository.findAllByBoxIdEquals(input.boxId);

     for (Order o:temp
          ) {
         if (o.getFromTime().getTime()<=start.getTime()&&eee.getTime()<=o.getToTime().getTime()) throw new Exception("该时段已被预定");

         if (o.getFromTime().getTime()>=start.getTime()&&eee.getTime()>=o.getFromTime().getTime())throw new Exception("该时段已被预定");
         if (o.getToTime().getTime()>=start.getTime()&&eee.getTime()>=o.getToTime().getTime())throw new Exception("该时段已被预定");
     }

        Order model=new Order();
        model.setAmount(input.amount);
        model.setBoxId(input.boxId);
        model.setBoxName(input.boxName);
        model.setToTime(eee);
        model.setFromTime(start);
        model.setId(0L);
        model.setOrderNum(num.toString());
        model.setCommon(false);
        model.setPointName(input.pointName);
        model.setUserKey(input.userKey);
        model.setState(0);
        model.setPurchaseTime(input.purchaseTime);
        model.setModifyUserId(1L);
        model.setConsumerName(input.consumerName);
        model.setCreationTime(date);
        model.setCreationUserId(1L);
        model.setDelete(false);
        model.setModifyTime(date);
        model.setMobile(input.consumerName);
        model.setOrderType(input.orderType);
      return  _orderRepository.save(model);
    }
    /** 更新订单状态
      */
    public Order UpdateOrderState(OrderStateInput input){
      Order or=_orderRepository.findByOrderNumEquals(input.order);
      if (or!=null){
           or.setState(input.state);
       or=   _orderRepository.saveAndFlush(or);
      }
      return  or;
    }
    public String ChangeToRoomId(String deviceCode){
        String url=efanurl+"api/getRoomIdByDeviceCode";
        String parms="device_code="+deviceCode;
        String result=  HttpUtils.sendPost(url,parms);
        ChangeResponse res;
        try{
            res =   new Gson().fromJson(result,ChangeResponse.class);
        }catch (Exception e){
         return    e.getMessage();
        }
        if(res.code==200){
          return  res.room_id;
        }
        return  "";
    }
    public String ChangeToDevice_code(String room_id){
        String url=efanurl+"api/getDeviceCodeByRoomId";
        String parms="room_id="+room_id;
        String result=  HttpUtils.sendPost(url,parms);
        ChangeResponse res;
        try{
            res =   new Gson().fromJson(result,ChangeResponse.class);
        }catch (Exception e){
            return    e.getMessage();
        }
        if(res.code==200){
            return  res.device_code;
        }
        return  "";
    }
    /** 验证支付
     */
    public  boolean vilidatePay(ValidatePayInput input) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
       input.fromTime=input.fromTime.getTime()<now.getTime()?now:input.fromTime;
       Calendar c=Calendar.getInstance();
       c.setTime(input.fromTime);
        c.add(Calendar.MINUTE,input.keepLive);
       Date end= c.getTime() ;
        List<Order> res=_orderRepository.findOrdersbyKey(input.machineCode,input.fromTime,end);
      return  res.size()>0;
    }
    public boolean TalkSingIt(Order input) throws JSONException {
        JSONObject map=new JSONObject();
        map.put("tag","roomControl");
        map.put("stbId",Integer.parseInt(input.getBoxId()) );
        map.put("identify","efanyun.com");
        map.put("openid",input.getUserKey());
        map.put("orderid",input.getOrderNum());
        map.put("serImage",input.getConsumerName());
        map.put("singer",input.getConsumerName());
        map.put("method","open");
        map.put("mode","sale");
        map.put("duration",input.getPurchaseTime());
        String result=   HttpUtils.postObj("https://cloud.xungevod.com:11443/kiosk/operation.html",map);
        ObjectResponse res;
        try{
            res =   new Gson().fromJson(result,ObjectResponse.class);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }
    //毁掉

    public boolean OutProductInAsync(Order input) throws JSONException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        JSONArray arr=new JSONArray();
        JSONObject obj=new JSONObject();
        obj.put("orderNumber",input.getOrderNum());
        obj.put("machineCode",input.getBoxId());
        obj.put("productId",input.getOrderType().toString());
        obj.put("vendoutDate",df.format(new Date()));
        obj.put("payChannel","WX");
        obj.put("vendoutStatus","VENDOUT_SUCCESS");
      arr.put(obj);
        String result=   HttpUtils.postObj("http://openapi.efanyun.com/vendout/report/ktv",arr);
        ObjectResponse res;
        try{
            res =   new Gson().fromJson(result,ObjectResponse.class);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }
    /** 验证订单
      */
    public  boolean VilidateOrder(String machineCode,Date from ,Date to ){
        List<Order> res=_orderRepository.findOrdersbyKey(machineCode,from,to);
        return res.size()<=0;
    }

    private  Date GenderTime(Date time,Boolean isstart){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time);
        if (isstart){
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), calendar1.get(Calendar.SECOND));
        }else {
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
        }
        return  calendar1.getTime();

    }
    private Timestamp DateToTimestamp(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String   time = sdf.format(date);
       return Timestamp.valueOf(time);
    }
    private  Date GetCurrentDate(Boolean start){
        Calendar calendar1 = Calendar.getInstance();
        if (start){
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), calendar1.get(Calendar.SECOND));
        }else {
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
        }
        return  calendar1.getTime();
    }

}
