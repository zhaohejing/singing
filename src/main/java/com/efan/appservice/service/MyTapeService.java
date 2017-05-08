package com.efan.appservice.service;

import com.efan.appservice.iservice.IMyTapeService;
import com.efan.repository.IMyTapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 我的歌单 歌曲接口
 */
@Service
public class MyTapeService implements IMyTapeService {
    private IMyTapeRepository _myTapeRepository;
    @Autowired
    public MyTapeService(IMyTapeRepository myTapeRepository){
        this._myTapeRepository= myTapeRepository;
    }
}
