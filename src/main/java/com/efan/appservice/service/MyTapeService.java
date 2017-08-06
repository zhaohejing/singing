package com.efan.appservice.service;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.controller.dtos.MyTapeDto;
import com.efan.controller.inputs.DeleteInput;
import com.efan.controller.inputs.MyTapeMachine;
import com.efan.core.primary.MyTape;
import com.efan.core.page.FilterModel;
import com.efan.core.page.ResultModel;
import com.efan.repository.primary.IMyTapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的录音 歌曲接口
 */
@Service
public class MyTapeService implements IMyTapeService {
    private IMyTapeRepository _myTapeRepository;
    @Autowired
    public MyTapeService(IMyTapeRepository myTapeRepository){
        this._myTapeRepository= myTapeRepository;
    }
//添加或编辑我的录音
    public Map<String,Object> InsertMyTape(MyTapeMachine input){
        Map<String,Object> map=new HashMap();
        map.put("tag",input.tag);
        map.put("stbId",input.stbId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            MyTape model=new MyTape();
            model.setId(0L);
            model.setSongName(input.songName);
            model.setOriginalSinger(input.originalSinger);
            model.setSinger(input.singer);
            model.setUserKey(input.userKey);
            model.setOrderId(input.orderId);
            model.setUserImage(input.userImage);
            model.setQiniuUrl(input.qiniuUrl);
            model.setRemark(input.remark);
            model.setSort(1);
            model.setSongImage(input.songImage);
            model.setSongTime(input.songTime);
            model.setCreationTime(df.format(new Date()));
            model.setState(false);
          model=   _myTapeRepository.save(model);
          if (model.getId()>0){
              map.put("operation","ok");
          }else{
              map.put("operation","failed");
          }
        return map;
    }
    //添加或编辑我的录音
    public MyTape ModifyMyTape(MyTapeDto input){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            MyTape mod=_myTapeRepository.findOne(input.id );
            mod.setQiniuUrl(input.qiniuUrl);
            mod.setModifyTime(df.format(new Date()));
            return   _myTapeRepository.save(mod);

    }
//获取我的录音列表
    public ResultModel<MyTape> GetAllMyTapeList(FilterModel model){
        Pageable pageable = new PageRequest(model.index-1, model.size,null);
      Page<MyTape> res=  _myTapeRepository.findMyTapeByUserKey(model.filter, pageable);
      return  new ResultModel<MyTape>( res.getContent(),res.getTotalElements());
    }
    public ResultModel<MyTape> GetMyTapeList(FilterModel model){
        Pageable pageable = new PageRequest(model.index-1, model.size,null);
        Page<MyTape> res=  _myTapeRepository.findMyTapeByUserKeyAndState(model.filter,true, pageable);
        return  new ResultModel<MyTape>( res.getContent(),res.getTotalElements());
    }

//更新我的录音上传状态
    public  MyTape UpdateMyTapeState(Long tapeId){
     MyTape model=_myTapeRepository.findOne(tapeId);
     model.setState(true);
    return  _myTapeRepository.save(model );
    }

    //获取我的录音详情
    public  MyTape GetMyTape(DeleteInput input){
        MyTape model=_myTapeRepository.findOne(input.id);
      return  model;
    }
    //排序我的歌单
    public  void  SortMyTape(String userKey,Long tapeId){
        List<MyTape> list=_myTapeRepository.findMyTapeByUserKeyOrderBySortDesc(userKey);
        MyTape first=list.get(0);
        Integer sort=1;
         if (first!=null){
             sort=first.getSort()==null?0:(first.getSort()+1);
         }
         MyTape cur=_myTapeRepository.findOne(tapeId);
         cur.setSort(sort);
         _myTapeRepository.saveAndFlush(cur );
    }

}
