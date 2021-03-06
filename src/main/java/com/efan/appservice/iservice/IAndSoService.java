package com.efan.appservice.iservice;

import com.efan.controller.inputs.BaseInput;
import com.efan.controller.inputs.GetSingerInput;
import com.efan.controller.inputs.GetSongsInput;
import com.efan.controller.inputs.VendorInput;
import com.efan.core.page.ResultModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 45425 on 2017/5/10.
 */
public interface IAndSoService {
    ResultModel<Map<String,Object>> GetSingerList(GetSingerInput input);
    ResultModel<Map<String,Object>> GetSongsList(GetSongsInput input);
    //获取歌曲分类
     List<Map<String,Object>> GetSongsCateList();
    //获取歌曲版本
     List<Map<String,Object>> GetSongsVerList();
    ResultModel<Map<String,Object>> GetSingerByHot(BaseInput input);
    //获取热点分类
     List<Map<String,Object>> GetHotCateList();
    ///获取排行榜下的歌曲
      ResultModel<Map<String,Object>> GetSongsByVendor(VendorInput input);
     ResultModel<Map<String,Object>> GetSingerCate();
    List<Map<String ,Object>> GetHotSongsList(String userKey,String cate);
}
