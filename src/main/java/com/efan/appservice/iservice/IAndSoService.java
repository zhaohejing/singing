package com.efan.appservice.iservice;

import com.efan.controller.inputs.GetSingerInput;
import com.efan.core.page.ResultModel;

import java.util.List;
import java.util.Map;

/**
 * Created by 45425 on 2017/5/10.
 */
public interface IAndSoService {
    ResultModel<Map<String,Object>> GetDiscoStyle();
    ResultModel<Map<String,Object>> GetSingerList(GetSingerInput input);
    ResultModel<Map<String,Object>> GetSongsList(GetSingerInput input);
}
