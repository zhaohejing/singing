package com.efan.appservice.service;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.controller.dtos.MyTapeDto;
import com.efan.core.entity.MyTape;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;
import com.efan.repository.IMyTapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 我的歌单 歌曲接口
 */
@Service
public class MyTapeService implements IMyTapeService {
    private IMyTapeRepository _myTapeRepository;
    @Autowired
    public MyTapeService(IMyTapeRepository myTapeRepository){
        this._myTapeRepository= myTapeRepository;
    }
//添加或编辑我的歌单
    public MyTape ModifyMyTape(MyTapeDto input){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if (input.id<=0){
            MyTape model=new MyTape();
            model.setId(0L);
            model.setSongName(input.songName);
            model.setOriginalSinger(input.originalSinger);
            model.setSinger(input.singer);
            model.setSongKey(input.songKey);
            model.setSongtime(input.songtime);
            model.setCreationTime(df.format(new Date()));
            model.setState(false);
          return   _myTapeRepository.save(model);
        }else   {
            MyTape mod=_myTapeRepository.findOne(input.id );
            mod.setSongName(input.songName);
            mod.setOriginalSinger(input.originalSinger);
            mod.setSinger(input.singer);
            mod.setSongtime(input.songtime);
            mod.setSongKey(input.songKey);
            mod.setModifyTime(df.format(new Date()));
            mod.setState(false);
            return   _myTapeRepository.save(mod);
        }
    }
//获取我的歌单列表
    public ResultModel<MyTape> GetMyTapeList(PageModel model){
        Pageable pageable = new PageRequest(model.index-1, model.size,null);
      Page<MyTape> res=  _myTapeRepository.findAll(pageable);
      List<MyTape> te=res.getContent();
      return  new ResultModel<MyTape>( res.getContent(),res.getTotalElements());
    }
//更新我的歌单上传状态
    public  MyTape UpdateMyTapeState(Long tapeId){
     MyTape model=_myTapeRepository.findOne(tapeId);
     model.setState(true);
    return  _myTapeRepository.save(model );
    }
}
