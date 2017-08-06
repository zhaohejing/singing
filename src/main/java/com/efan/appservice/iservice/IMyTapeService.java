package com.efan.appservice.iservice;

import com.efan.controller.dtos.MyTapeDto;
import com.efan.controller.inputs.DeleteInput;
import com.efan.controller.inputs.MyTapeMachine;
import com.efan.core.primary.MySongs;
import com.efan.core.primary.MyTape;
import com.efan.core.page.FilterModel;
import com.efan.core.page.ResultModel;

import java.util.Map;

/**
 * Created by 45425 on 2017/5/8.
 */
public interface IMyTapeService {
    Map<String,Object> InsertMyTape(MyTapeMachine input);
    //添加或编辑我的录音
     MyTape ModifyMyTape(MyTapeDto input);
    ResultModel<MyTape> GetMyTapeList(FilterModel model);
    MyTape UpdateMyTapeState(Long tapeId);
    MyTape GetMyTape(DeleteInput input);
    ResultModel<MyTape> GetAllMyTapeList(FilterModel model);
    //排序我的歌单
      void  SortMyTape(String userKey,Long tapeId);
}
