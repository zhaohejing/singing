package com.efan.appservice;

import com.efan.core.entity.Song;

import java.util.List;

/**
 * Created by 45425 on 2017/5/4.
 */
public interface ISongAppService {
    List<Song> GetSongsList();
}
