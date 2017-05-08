package com.efan.appservice.iservice;

import com.efan.controller.dtos.RemoteDto;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 45425 on 2017/5/8.
 */
public interface IOrderService {
     ResultModel<RemoteDto> GetRemoteList(String pointName, PageModel input) ;
    /**
     * 获取包厢列表
     * */
     ResultModel<RemoteDto> GetCoupeList(int remoteId, PageModel input) ;
}
