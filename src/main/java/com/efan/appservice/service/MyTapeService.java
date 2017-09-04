package com.efan.appservice.service;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.controller.dtos.MyTapeDto;
import com.efan.controller.inputs.DeleteInput;
import com.efan.controller.inputs.MyTapeMachine;
import com.efan.controller.inputs.RemarkInput;
import com.efan.core.primary.MyTape;
import com.efan.core.page.FilterModel;
import com.efan.core.page.ResultModel;
import com.efan.repository.primary.IMySongsRepository;
import com.efan.repository.primary.IMyTapeRepository;
import com.efan.utils.CodeUtil;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 我的录音 歌曲接口
 */
@Service
public class MyTapeService implements IMyTapeService {
    private IMyTapeRepository _myTapeRepository;
    private IMySongsRepository _mySongsRepository;

    @Autowired
    public MyTapeService(IMyTapeRepository myTapeRepository,IMySongsRepository mySongsRepository){
        this._myTapeRepository= myTapeRepository;
        this._mySongsRepository=mySongsRepository;
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
            if(!input.qiniuKey.isEmpty()){
                String url=""+input.qiniuKey;
                model.setQiniuKey(url);
            }
            model.setSongKey(input.songKey);
              model.setSongCode(input.songCode);

            model.setRemark(input.remark);
            model.setSongTime(input.songTime);
            model.setCreationTime(df.format(new Date()));
            model.setState(false);
          model=   _myTapeRepository.save(model);
          if (model.getId()>0){
              //清除歌单操作
              if(!input.songSubId.isEmpty()){
                  long l = Long.parseLong(input.songSubId);
                  _mySongsRepository.delete(l);
              }
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
        if(!input.qiniuUrl.isEmpty()){
            String url=""+input.qiniuUrl;
            mod.setQiniuKey(url);
        }
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
    public  MyTape Remark(RemarkInput input){
        MyTape model=_myTapeRepository.findOne(input.tapeId);
        if (!input.remark.isEmpty()){
            model.setRemark(input.remark);

        }
        if (!input.singer.isEmpty()){
            model.setSinger(input.singer);
        }
        if (!input.userImage.isEmpty()){
            model.setUserImage(input.userImage);

        }
        return  _myTapeRepository.save(model );
    }
    //获取我的录音详情
    public  MyTape GetMyTape(DeleteInput input) throws  Exception{
        MyTape model=_myTapeRepository.findOne(input.id);
       // model.setSinger(CodeUtil.emojiRecovery(model.getSinger()).toString());
        if(!model.getQiniuKey().isEmpty()){
            String domainOfBucket = "http://record.eqichang.efanyun.com";
           String encodedFileName =  URLEncoder.encode(model.getQiniuKey(), "utf-8");
            String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

            String accessKey = "qrHNg87X9WCbirrE_xouL35IUCJCQtQBUV3EfdD0";
            String secretKey = "6_nyn8qIOQrNG8oSqbVUCzVdEKUm6Qb5pf17Xjnr";
            Auth auth = Auth.create(accessKey, secretKey);
            long expireInSeconds = 36000;//1小时，可以自定义链接过期时间
            String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
    model.setQiniuKey(finalUrl);
        }
      return  model;
    }

}
