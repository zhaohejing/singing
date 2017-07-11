package com.efan.appservice.iservice;

import com.efan.controller.inputs.DeleteInput;
import com.efan.controller.inputs.KeyInput;
import com.efan.controller.inputs.MySongsInput;
import com.efan.controller.inputs.songSubInput;
import com.efan.core.page.FilterModel;
import com.efan.core.page.ResultModel;
import com.efan.core.primary.MySongs;

import java.util.List;
import java.util.Map;

/**
 * 录音service
 */
public interface ITapeService {
    ResultModel<MySongs> GetMySongsList(FilterModel model);

    MySongs CreateMySongs(MySongsInput input);

    Map<String,Object> GetMySongsByUserKey(KeyInput input);
     //更新歌单状态
     Map<String,Object>  UpdateMySongsState(songSubInput input);
    void DeleteMySongs(Long id);
}
