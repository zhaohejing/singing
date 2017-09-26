package com.efan.appservice.service;

import com.efan.appservice.iservice.IOrderService;
import com.efan.controller.dtos.OrderTime;
import com.efan.controller.inputs.*;
import com.efan.core.page.*;
import com.efan.core.primary.Order;
import com.efan.repository.primary.IOrderRepository;
import com.efan.utils.CodeUtil;
import com.efan.utils.HttpUtils;
import com.google.gson.Gson;
import com.sun.tools.corba.se.idl.constExpr.Times;
import io.swagger.models.auth.In;
import javafx.util.converter.TimeStringConverter;
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


import java.io.IOException;
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
    public BaseResponse GetRemoteList(RemoteInput input) {
        String url=efanurl+"api/getSpotsByCoordinate";
        String parm="longitude="+input.y+"&latitude="+input.x+"&page="+input.page;

      String result=  HttpUtils.sendPost(url,parm);
        logger.warn("logitude:"+input.y+",latitude:"+input.x+"result:"+result);
        BaseResponse res;
        try{
            res =   new Gson().fromJson(result,BaseResponse.class);
        }catch (Exception e){
            res=new BaseResponse();
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
         return  new DecimalFormat("#").format(less/3600);
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
        Integer nowDay=now.get(Calendar.DAY_OF_MONTH);
        Integer nowHour=now.get(Calendar.HOUR_OF_DAY);
        Integer minitu=now.get(Calendar.MINUTE);

        for (int i = 0; i <24 ; i++) {
            if(end.getYear()>now.getTime().getYear()|| end.getMonth()>now.getTime().getMonth()||end.getDate()>now.getTime().getDate()){
                if (list.size()<=0){
                    result.add(new OrderTime(i,i+1,0));
                    continue;
                }else{
                    Integer count=0;
                    Integer max=0;
                    for (Order te:list){
                        Date ll=GetTargetDate(true,i,date);
                        Date rr=GetTargetDate(false,i,date);
                        if (ll.getTime()<=te.getToTime().getTime()&&rr.getTime()>=te.getFromTime().getTime()){
                            Integer ttttt=  te.getToTime().getMinutes();
                            Long deff=te.getToTime().getTime()-te.getFromTime().getTime();
                            if (ttttt==0&&deff>=3540000){
                                ttttt=te.getPurchaseTime();
                            }
                            max= max>ttttt?max:ttttt;
                        }
                    }
                    count+=  max;
                    count=count==60?60:count+1;
                    result.add(new OrderTime(i,i+1,count));
                    continue;
                }



            }
            if(end.getYear()<now.getTime().getYear()|| end.getMonth()<now.getTime().getMonth()||end.getDate()<now.getTime().getDate()){
                result.add(new OrderTime(i,i+1,60));
                continue;
            }
            if (nowHour>i){
                result.add(new OrderTime(i,i+1,60));
                continue;
            }
            Integer count=0;

            if (list.size()<=0){
                if(i==nowHour){
                    count=minitu;
                }
            }
            Integer max=0;
            for (Order te:list){
                Date ll=GetTargetDate(true,i,date);
                Date rr=GetTargetDate(false,i,date);
                if(i==nowHour){
                    Integer mmmm=   te.getToTime().getMinutes();
                    count=   mmmm> minitu?mmmm:minitu;
                    continue;
                }
                if (ll.getTime()<=te.getToTime().getTime()&&rr.getTime()>=te.getFromTime().getTime()){
                    Integer ttttt=  te.getToTime().getMinutes();
                    if (ttttt==0&&(te.getToTime().getTime()-te.getFromTime().getTime())>=3540000){
                        ttttt=te.getPurchaseTime();
                    }
                          max= max>ttttt?max:ttttt;
                }

            }
            count+=  max;
            count=count==60?60:count+1;
            result.add(new OrderTime(i,i+1,count));
        }



        return  result;
    }

    /*获取预定订单列表*/
    public List<OrderTime> GetOrderListAsync(String boxId, Date date){
        Calendar now =Calendar.getInstance();
        Date start=GenderStart(now.getTime()) ;
        Date end=GenderTime(date,false);
        List<OrderTime> result=new ArrayList<>();
        List<Order> list= _orderRepository.findOrders(boxId,start,end);
        Integer nowDay=now.get(Calendar.DAY_OF_MONTH);
        Integer nowHour=now.get(Calendar.HOUR_OF_DAY);
        Integer minitu=now.get(Calendar.MINUTE);
        if(date.getDate()< now.getTime().getDate())
        {
            for (int i = 0; i <24 ; i++)
                result.add(new OrderTime(i,i+1,60));
        }else if(date.getDate() > now.getTime().getDate() )
        {
            for (int i = 0; i <24 ; i++)
            {
                Integer count=0;
                Integer max=0;
                for (Order te:list)
                {
                    Date ll=GetTargetDate(true,i,date);
                    Date rr=GetTargetDate(false,i,date);

                    if (ll.getTime()<=te.getToTime().getTime()&&rr.getTime()>=te.getFromTime().getTime())
                    {
                        if(te.getToTime().getHours()>i )
                        {
                            max=60;
                        }else
                        {
                            Integer ttttt=  te.getToTime().getMinutes();
                            max= max>ttttt?max:ttttt;
                        }

                    }
                }
                count=  max;
                count=count==60?60:count+1;
                result.add(new OrderTime(i,i+1,count));
            }
        }else
        {
            for (int i = 0; i <24 ; i++)
            {

                Integer count=0;
                Integer max=0;

                if (nowHour>i)
                {
                    result.add(new OrderTime(i,i+1,60));
                    continue;
                }else
                {
                    for (Order te:list)
                    {
                        Date ll=GetTargetDate(true,i,date);
                        Date rr=GetTargetDate(false,i,date);

                        if (ll.getTime()<=te.getToTime().getTime()&&rr.getTime()>=te.getFromTime().getTime())
                        {
                            if(te.getToTime().getHours()>i )
                            {
                                max=60;
                            }else
                            {
                                Integer ttttt=  te.getToTime().getMinutes();
                                max= max>ttttt?max:ttttt;
                            }
                        }
                    }
                    if(i==nowHour)
                    {
                        Integer mmmm=   now.getTime().getMinutes();
                        max= max>mmmm?max:mmmm;
                    }
                }
                count=  max;
                count=count==60?60:count+1;
                result.add(new OrderTime(i,i+1,count));
            }
        }
        return  result;
    }
    public  void  PayOffOrder(String order){
        Order o=_orderRepository.findByEfanOrderEquals(order);
        if (o!=null){
            o.setState(3);
            _orderRepository.saveAndFlush(o);
        }

    }

///根据lexington获取套餐详情
    public  BaseResponse GetOrderTypeList(Boolean isRemote,String boxId){
         String url=isRemote?efanurl+"api/getProductsByRoomRemote": efanurl+"api/getProductsByRoom";
         String parms="room_id="+boxId;
         String result=  HttpUtils.sendPost(url,parms);
         BaseResponse res;
         try{
             res =   new Gson().fromJson(result,BaseResponse.class);
         }catch (Exception e){
             res=new BaseResponse();
             res.code=1000;
             res.message=e.getMessage();
         }
         return  res;
     }
    public  BaseResponse GetMachineInfo(String boxId){
        String url=efanurl+"api/getMachineInfo";
        String parms="room_id="+boxId;
        String result=  HttpUtils.sendPost(url,parms);
        BaseResponse res;
        try{
            res =   new Gson().fromJson(result,BaseResponse.class);
        }catch (Exception e){
            res=new BaseResponse();
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
        Page<Order> res=  _orderRepository.findAllByUserKeyOrderByCreationTimeDesc(input.getFilter(), pageable);
        return new ResultModel<>(res.getContent(), res.getTotalElements());

    }
//创建订单并调用支付接口
 @Transactional
    public Order CreateOrder(OrderInput input)  throws Exception {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Calendar now=Calendar.getInstance();
      UUID num=   java.util.UUID.randomUUID();

      Date tempTime=input.fromTime.getTime()<date.getTime()?date:input.fromTime;


        Calendar c=Calendar.getInstance();
        c.setTime(tempTime);
     Timestamp   start=DateToTimestamp( c.getTime());
     now.setTime(date);


     if(input.purchaseTime<60){
        c.add(Calendar.MINUTE,input.purchaseTime);
         now.add(Calendar.MINUTE,input.purchaseTime);

     }
     else if(input.purchaseTime>=60&&input.purchaseTime<24*60){
          Integer hour=  input.purchaseTime/60;
          c.add(Calendar.HOUR_OF_DAY,hour );
          c.add(Calendar.MINUTE,input.purchaseTime-hour*60);

         now.add(Calendar.HOUR_OF_DAY,hour );
         now.add(Calendar.MINUTE,input.purchaseTime-hour*60);
        }
        Date end= c.getTime() ;
        Timestamp eee=DateToTimestamp(end);
        List<Order> temp=_orderRepository.findboxandstate(input.boxId);
     for (Order o:temp
          ) {
       if(o.getFromTime().getTime()<end.getTime()&&o.getToTime( ).getTime() >start.getTime()){
           throw new Exception("该时段已被预定");
       }
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
          if(or.getState()==0){
              or.setState(input.state);
          }
          or.setEfanOrder(input.efanOrder);
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
        List<Order> temp=_orderRepository.findboxandstate(input.machineCode);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        input.fromTime=input.fromTime.getTime()<now.getTime()?now:input.fromTime;
        Calendar c=Calendar.getInstance();
        c.setTime(input.fromTime);
        Date left=c.getTime();
        if(input.keepLive<60){
            c.add(Calendar.MINUTE,input.keepLive);
        }else if(input.keepLive>=60&&input.keepLive<24*60){
            Integer hour=  input.keepLive/60;
            c.add(Calendar.HOUR_OF_DAY,hour );
            c.add(Calendar.MINUTE,input.keepLive-hour*60);
        }
        Date end= c.getTime() ;

        for (Order o:temp
                ) {
            if (o.getFromTime().getTime()<=left.getTime()&&end.getTime()<=o.getToTime().getTime()) return  false;
            if (o.getFromTime().getTime()>=left.getTime()&&end.getTime()>=o.getFromTime().getTime())return  false;
            if (o.getToTime().getTime()>=left.getTime()&&end.getTime()>=o.getToTime().getTime())return  false;

        }
    return  true;

    }
    public ObjectResponse TalkSingIt(Order input) throws JSONException,IOException {
        logger.error("--------------------------------"+ new Gson().toJson(input));
        Timestamp now = new Timestamp(System.currentTimeMillis());
            now= input.getFromTime().getTime()<=now.getTime()?now:input.getFromTime();
        JSONObject map=new JSONObject();
        map.put("tag","roomControl");
        map.put("stbId",Integer.parseInt(input.getBoxId()) );
        map.put("identify","efanyun.com");
        map.put("openid",input.getUserKey());
        map.put("orderid",input.getOrderNum());
        map.put("serImage","");
        map.put("singer", CodeUtil.emojiConvert(input.getConsumerName()));
        map.put("method","open");
        map.put("mode","sale");
        map.put("duration",(input.getToTime().getTime()-now.getTime())/1000 );
        String result=   HttpUtils.postObj("https://cloud.xungevod.com:11443/kiosk/operation.html",map);
        logger.error("--------------------------------"+result);
        ObjectResponse res;
        try{
            res =   new Gson().fromJson(result,ObjectResponse.class);
        }catch (Exception e){
            res=new ObjectResponse();
           res.error=e.getMessage();
           res.operation="failed";

        }
        return  res;
    }
    //毁掉
    public List<Order> FindByFilter(String boxId,String openId){
        Date now=new Date();
        return  _orderRepository.findbyFilter(boxId,openId,now);
    }

    public BodyResponse OutProductInAsync(ObjectResponse response, Order input) throws JSONException {
        logger.error("--------------------------------"+ new Gson().toJson(input));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        JSONArray arr=new JSONArray();
        JSONObject obj=new JSONObject();
        obj.put("orderNumber",input.getEfanOrder());
        obj.put("machineCode",input.getBoxId());
        obj.put("productId",input.getOrderType().toString());
        obj.put("vendoutDate",df.format(new Date()));
        obj.put("payChannel","WX");
        obj.put("vendoutStatus",  response.operation.equals("ok")? "VENDOUT_SUCCESS":"VENDOUT_FAILEd");
          arr.put(obj);
        String result=   HttpUtils.postObj("http://openapi.efanyun.com/vendout/report/ktv",arr);
        logger.error("--------------------------------"+result);
        BodyResponse res;
        try{
            res =   new Gson().fromJson(result,BodyResponse.class);
        }catch (Exception e){
            res=new BodyResponse();
           res.code="failed";
           res.message=e.getMessage();
        }
        return  res;
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
    private  Date GenderStart(Date time){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time);
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    0, 0, 0);

        return  calendar1.getTime();
    }
    private  Date GenderNowNoMinitie(Date time){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time);
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                calendar1.get(Calendar.HOUR_OF_DAY), 0, 0);

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
    private  Date GetCurrentDateAsync(Boolean start,Integer hour){
        Calendar calendar1 = Calendar.getInstance();
        if (start){
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    hour, 0, 0);
        }else {
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    hour, 59, 59);
        }
        return  calendar1.getTime();
    }
    private  Date GetTargetDate(Boolean start,Integer hour,Date date){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        if (start){
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    hour, 0, 0);
        }else {
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                    hour+1, 0, 0);
        }
        return  calendar1.getTime();
    }
}
