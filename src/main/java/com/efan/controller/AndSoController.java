package com.efan.controller;

import com.efan.core.page.ActionResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *点歌接口列表
 */
@RestController
@RequestMapping("/api/songs")
public class AndSoController {
    @Autowired
    public AndSoController(){

    }
    /*获取热门歌星列表*/
    @ApiOperation(value="热门歌星列表", notes="歌单接口")
    @RequestMapping(value  ="/hitlist" ,method = RequestMethod.POST)
    public ActionResult HitList(){
        return  new ActionResult();
    }

    /*歌曲模糊搜索*/
    @ApiOperation(value="歌曲模糊搜索", notes="歌单接口")
    @ApiImplicitParam(name = "keyWord", value = "关键词", required = true, dataType = "String")
    @RequestMapping(value  ="/searchsongs" ,method = RequestMethod.POST)
    public ActionResult SearchSongs(String keyWord){
        return  new ActionResult();
    }
    /*根据分类获取歌星列表*/
    @ApiOperation(value="根据分类获取歌星列表", notes="歌单接口")
    @ApiImplicitParam(name = "cateId", value = "分类id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/getstarsbycate" ,method = RequestMethod.POST)
    public ActionResult GetStarByCate(Integer cateId){
        return  new ActionResult();
    }
    /*获取歌星下歌曲列表*/
    @ApiOperation(value="获取歌星下歌曲列表", notes="歌单接口")
    @ApiImplicitParam(name = "starId", value = "歌星id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/getsongsbystar" ,method = RequestMethod.POST)
    public ActionResult GetSongsByStar(Integer starId){
        return  new ActionResult();
    }
    /*获取歌曲分类列表*/
    @ApiOperation(value="获取歌曲分类列表", notes="歌单接口")
    @RequestMapping(value  ="/getsongscates" ,method = RequestMethod.POST)
    public ActionResult SongsCates(){
        return  new ActionResult();
    }
    /*获取分类下歌曲列表*/
    @ApiOperation(value="获取分类下歌曲列表", notes="歌单接口")
    @ApiImplicitParam(name = "cateId", value = "歌曲分类Id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/getsongsbycate" ,method = RequestMethod.POST)
    public ActionResult GetSongsByCates(Integer cateId){
        return  new ActionResult();
    }
    /*获取热点歌曲分类列表*/
    @ApiOperation(value="获取热点歌曲分类列表", notes="歌单接口")
    @RequestMapping(value  ="/gethotsongscate" ,method = RequestMethod.POST)
    public ActionResult GetSongsHotCates(){
        return  new ActionResult();
    }
    /*获取热点歌曲分类下歌曲*/
    @ApiOperation(value="获取热点歌曲分类下歌曲", notes="歌单接口")
    @ApiImplicitParam(name = "hotCateId", value = "热门歌曲分类id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/gethotsongsbycate" ,method = RequestMethod.POST)
    public ActionResult GetSongsByHotCates(Integer hotCateId){
        return  new ActionResult();
    }

    /*获取排行榜列表*/
    @ApiOperation(value="获取排行榜列表", notes="歌单接口")
    @RequestMapping(value  ="/getrankinglist" ,method = RequestMethod.POST)
    public ActionResult GetRankingList(){
        return  new ActionResult();
    }
    /*获取排行榜歌曲列表*/
    @ApiOperation(value="获取排行榜歌曲列表", notes="歌单接口")
    @RequestMapping(value  ="/getrankingsongs" ,method = RequestMethod.POST)
    public ActionResult GetRankingSongs(){
        return  new ActionResult();
    }

    /*获取分类下歌星列表*/
    @ApiOperation(value="获取歌星分类列表", notes="歌单接口")
    @RequestMapping(value  ="/getstarcates" ,method = RequestMethod.POST)
    public ActionResult GetStarCates(){
        return  new ActionResult();
    }

    @RequestMapping(value  ="/payfor" ,method = RequestMethod.POST)
    public ActionResult PayFor(String orderId,String openId){
        return  new ActionResult();
    }


}
