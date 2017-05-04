package com.efan.controller;

import com.efan.appservice.ISongAppService;
import com.efan.core.entity.Song;
import com.efan.core.page.ActionResult;
import com.efan.repository.IBoxRepository;
import com.efan.repository.ISongRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 45425 on 2017/5/4.
 */
@RestController
@RequestMapping("/api/song")
public class SongController {
    private ISongAppService _songService;
    private IBoxRepository _boxRepository;
    @Autowired
    public SongController(ISongAppService songService,IBoxRepository boxRepository){
        this._songService=songService;
        this._boxRepository=boxRepository;
    }

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
  //  @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value  ="/list" ,method = RequestMethod.POST)
    public ActionResult UserList() {
        List<Song> res= _songService.GetSongsList();
        return new ActionResult(res);
    }

}
