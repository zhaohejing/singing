package com.efan.appservice.iservice;

import com.efan.controller.inputs.KeyInput;
import com.efan.controller.inputs.MySongsInput;
import com.efan.controller.inputs.SongSubInput;
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

    ResultModel<MySongs> GetMySongsByUserKey(KeyInput input);
     //更新歌单状态
     Map<String,Object> UpdateMySongsState(SongSubInput input);
    void DeleteMySongs(Long id);
    //efan毁掉 我的歌曲列表
    Map<String,Object> GetMySongsByUser(KeyInput input);
    Integer GetMySongsCount(String userKey,Long songsId);
    //排序我的歌单
    MySongs  SortMyTape(String userKey,Long tapeId);
}
