package com.efan.appservice.iservice;

import com.efan.controller.inputs.DeleteInput;
import com.efan.controller.inputs.KeyInput;
import com.efan.controller.inputs.MySongsInput;
import com.efan.core.page.FilterModel;
import com.efan.core.page.ResultModel;
import com.efan.core.primary.MySongs;

import java.util.List;

/**
 * 录音service
 */
public interface ITapeService {
    ResultModel<MySongs> GetMySongsList(FilterModel model);

    MySongs CreateMySongs(MySongsInput input);

     List<MySongs> GetMySongsByUserKey(KeyInput input);
     //更新歌单状态
     void UpdateMySongsState(DeleteInput input);
    void DeleteMySongs(Long id);
}
