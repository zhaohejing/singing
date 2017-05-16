package com.efan.controller;

import com.efan.appservice.iservice.IAndSoService;
import com.efan.controller.dtos.SingerDto;
import com.efan.controller.dtos.SongCateDto;
import com.efan.controller.dtos.SongDto;
import com.efan.controller.inputs.GetSingerInput;
import com.efan.core.page.ActionResult;
import com.efan.core.page.FilterModel;
import com.efan.core.page.PageModel;
import com.efan.core.page.ResultModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *点歌接口列表
 */
@RestController
@RequestMapping("/api/songs")
public class AndSoController {
    private IAndSoService _songService;
    @Autowired
    public AndSoController(IAndSoService songService){
        this._songService=songService;

    }
    /*获取热门歌星列表*/
    @ApiOperation(value="热门歌星列表", notes="歌单接口")
    @ApiImplicitParam(name = "input", value = "分页dto", required = true, dataType = "PageModel")
    @RequestMapping(value  ="/hitlist" ,method = RequestMethod.POST)
    public ActionResult HitList(){
        GetSingerInput input=new GetSingerInput();
        input.setIndex(1);input.setSize(20);
        ResultModel<Map<String,Object>> result=_songService.GetSingerList(input);
        return  new ActionResult(result);
    }
    /*歌曲模糊搜索*/
    @ApiOperation(value="歌曲模糊搜索", notes="歌单接口")
    @ApiImplicitParam(name = "input", value = "{filter:关键词,index:页码,size:页容量 ,word:简写}", required = true, dataType = "GetSingerInput")
    @RequestMapping(value  ="/searchsongs" ,method = RequestMethod.POST)
    public ActionResult SearchSongs(@RequestBody GetSingerInput input){
        ResultModel<Map<String,Object>> result=_songService.GetSongsList(input);
        return  new ActionResult(result);
    }
    /*根据分类获取歌星列表*/
    @ApiOperation(value="根据分类获取歌星列表", notes="歌单接口")
    @ApiImplicitParam(name = "input", value = "{filter:过滤条件,index:页码,size:页容量 ,word:关键词}", required = true, dataType = "GetSingerInput")
    @RequestMapping(value  ="/getstarsbycate" ,method = RequestMethod.POST)
    public ActionResult GetStarByCate(@RequestBody GetSingerInput input){
        ResultModel<Map<String,Object>> result=_songService.GetSingerList(input);
        return  new ActionResult(result);
    }
    /*获取歌星下歌曲列表*/
    @ApiOperation(value="获取歌星下歌曲列表", notes="歌单接口")
    @ApiImplicitParam(name = "starId", value = "歌星id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/getsongsbystar" ,method = RequestMethod.POST)
    public ActionResult GetSongsByStar(Integer starId){
        List<SongDto> list=new ArrayList<SongDto>();
        list.add(new SongDto(1,"小苹果"));
        list.add(new SongDto(2,"情歌"));
        list.add(new SongDto(3,"绿光"));
        list.add(new SongDto(4,"一眼万年"));
        list.add(new SongDto(5,"爱如潮水"));
        list.add(new SongDto(6,"暖暖"));
        list.add(new SongDto(7,"贵妃醉酒"));
        list.add(new SongDto(8,"相别1997"));
        int total=list.size();
        return  new ActionResult(new ResultModel<SongDto>(list, (long) total));
    }
    /*获取歌曲分类列表*/
    @ApiOperation(value="获取歌曲分类列表", notes="歌单接口")
    @RequestMapping(value  ="/getsongscates" ,method = RequestMethod.POST)
    public ActionResult SongsCates(){
        List<Map<String,Object>> result=_songService.GetSongsCateList();
        return  new ActionResult(result);
    }
    /*获取分类下歌曲列表*/
    @ApiOperation(value="获取分类下歌曲列表", notes="歌单接口")
    @ApiImplicitParam(name = "input", value = "{filter:过滤条件,index:页码,size:页容量 ,word:关键词}", required = true, dataType = "GetSingerInput")
    @RequestMapping(value  ="/getsongsbycate" ,method = RequestMethod.POST)
    public ActionResult GetSongsByCates(@RequestBody GetSingerInput input){
        ResultModel<Map<String,Object>> result=_songService.GetSongsList(input);
        return  new ActionResult(result);
    }
    /*获取热点歌曲分类列表*/
    @ApiOperation(value="获取热点歌曲分类列表", notes="歌单接口")
    @RequestMapping(value  ="/gethotsongscate" ,method = RequestMethod.POST)
    public ActionResult GetSongsHotCates(){
        List<SongCateDto> list=new ArrayList<SongCateDto>();
        list.add(new SongCateDto(1,"情歌"));
        list.add(new SongCateDto(2,"粤语"));
        list.add(new SongCateDto(3,"国语"));
        list.add(new SongCateDto(4,"伤感"));
        list.add(new SongCateDto(7,"儿歌"));
        list.add(new SongCateDto(8,"18"));
        int total=list.size();
        return  new ActionResult(new ResultModel<SongCateDto>(list, (long) total));
    }
    /*获取热点歌曲分类下歌曲*/
    @ApiOperation(value="获取热点歌曲分类下歌曲", notes="歌单接口")
    @ApiImplicitParam(name = "hotCateId", value = "热门歌曲分类id", required = true, dataType = "Integer")
    @RequestMapping(value  ="/gethotsongsbycate" ,method = RequestMethod.POST)
    public ActionResult GetSongsByHotCates(Integer hotCateId){
        List<SongDto> list=new ArrayList<SongDto>();
        list.add(new SongDto(1,"小苹果"));
        list.add(new SongDto(3,"绿光"));
        list.add(new SongDto(5,"爱如潮水"));
        list.add(new SongDto(6,"暖暖"));
        list.add(new SongDto(7,"贵妃醉酒"));
        list.add(new SongDto(8,"相别1997"));
        int total=list.size();
        return  new ActionResult(new ResultModel<SongDto>(list, (long) total));
    }

    /*获取排行榜列表*/
    @ApiOperation(value="获取排行榜列表", notes="歌单接口")
    @RequestMapping(value  ="/getrankinglist" ,method = RequestMethod.POST)
    public ActionResult GetRankingList(){
        List<SongDto> list=new ArrayList<SongDto>();
        list.add(new SongDto(1,"小苹果"));
        list.add(new SongDto(6,"暖暖"));
        list.add(new SongDto(7,"贵妃醉酒"));
        list.add(new SongDto(8,"相别1997"));
        int total=list.size();
        return  new ActionResult(new ResultModel<SongDto>(list, (long) total));
    }
    /*获取排行榜歌曲列表*/
    @ApiOperation(value="获取排行榜歌曲列表", notes="歌单接口")
    @RequestMapping(value  ="/getrankingsongs" ,method = RequestMethod.POST)
    public ActionResult GetRankingSongs(){
        List<SongDto> list=new ArrayList<SongDto>();
        list.add(new SongDto(1,"小苹果"));
        list.add(new SongDto(5,"爱如潮水"));
        list.add(new SongDto(6,"暖暖"));
        list.add(new SongDto(7,"贵妃醉酒"));
        list.add(new SongDto(8,"相别1997"));
        int total=list.size();
        return  new ActionResult(new ResultModel<SongDto>(list, (long) total));
    }

    /*获取分类下歌星列表*/
    @ApiOperation(value="获取歌星分类列表", notes="歌单接口")
    @RequestMapping(value  ="/getstarcates" ,method = RequestMethod.POST)
    public ActionResult GetStarCates(){
        List<SingerDto> list=new ArrayList<SingerDto>();
        list.add(new SingerDto(2,"莫文蔚"));
        list.add(new SingerDto(3,"周杰伦"));
        list.add(new SingerDto(4,"张惠妹"));
        list.add(new SingerDto(5,"张杰"));
        list.add(new SingerDto(6,"梁静茹"));
        list.add(new SingerDto(7,"屠洪刚"));
        list.add(new SingerDto(8,"那英"));
        int total=list.size();
        return  new ActionResult(new ResultModel<SingerDto>(list, (long) total));
    }


}
