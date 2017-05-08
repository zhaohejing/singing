package com.efan.appservice.iservice;

import com.efan.controller.dtos.RemoteDto;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;

/**
 * Created by 45425 on 2017/5/8.
 */
public interface IOrderService {
    ResultModel<RemoteDto> GetRemoteList(PageModel input);
}
