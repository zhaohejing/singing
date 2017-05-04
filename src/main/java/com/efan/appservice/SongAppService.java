package com.efan.appservice;

import com.efan.core.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 45425 on 2017/5/4.
 */
@Service
public class SongAppService implements ISongAppService {
    private JpaRepository<Song,Long> _songRepository;
    @Autowired
    public  SongAppService(JpaRepository<Song,Long> songRepository){
        this._songRepository=songRepository;
    }
    public List<Song> GetSongsList(){
      return   _songRepository.findAll();

    }

}
