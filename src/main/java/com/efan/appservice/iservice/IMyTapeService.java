package com.efan.appservice.iservice;

import com.efan.controller.dtos.MyTapeDto;
import com.efan.core.entity.MyTape;
import com.efan.core.page.FilterModel;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;

/**
 * Created by 45425 on 2017/5/8.
 */
public interface IMyTapeService {
    MyTape ModifyMyTape(MyTapeDto input);
    ResultModel<MyTape> GetMyTapeList(FilterModel model);
    MyTape UpdateMyTapeState(Long tapeId);
}
