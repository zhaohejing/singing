package com.efan.appservice.service;

import com.efan.appservice.iservice.IAndSoService;
import com.efan.controller.inputs.BaseInput;
import com.efan.controller.inputs.GetSingerInput;
import com.efan.controller.inputs.GetSongsInput;
import com.efan.core.page.ResultModel;
import com.efan.core.primary.MySongs;
import com.efan.repository.primary.IMySongsRepository;
import com.efan.repository.primary.IMyTapeRepository;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 歌曲查询service
 */
@Service
public class AndSoService implements IAndSoService {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private    JdbcTemplate _jdbc;
    private IMySongsRepository _mySongsRepository;
    @Autowired
    public  AndSoService(IMySongsRepository mySongsRepository){
        _mySongsRepository=mySongsRepository;
    }
    //获取歌手列表
    public ResultModel<Map<String,Object>> GetSingerList(GetSingerInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT unSingerCode unSingerCo ,pszName,pszSpell from singerinfo where 1=1");
        count.append("SELECT count(*) from singerinfo where 1=1");

        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
           sql.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
            count.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like '%").append(input.word).append("%' ");
            count.append(" and  pszSpell like '%").append(input.word).append("%' ");
        }
        if (input.cate>0){
            sql.append(" and  wSingerType = '").append(input.cate).append("' ");
            count.append(" and  wSingerType = '").append(input.cate).append("' ");
        }
     /*   if (input.area>0){
            sql.append(" and  wSingerAre = '"+input.area+"' ");
            count.append(" and  wSingerAre = '"+input.area+"' ");
        }*/

        sql.append(" limit  ").append(input.getPage()).append(" , ").append(input.getSize());
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return new ResultModel<>(list, total);
    }
    //获取歌曲列表
    public ResultModel<Map<String,Object>> GetSongsList(GetSongsInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT ID,unSongCode ullSongCode,unSongCodex unSongCode,pszName,pszSpell,pszSingers singerName  from songinfo  where 1=1");
        count.append("SELECT count(*) from songinfo where 1=1");
        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
            sql.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
            count.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like '%").append(input.word).append("%' ");
            count.append(" and  pszSpell like '%").append(input.word).append("%' ");
        }
        if (input.cateId!=null&& !input.cateId.isEmpty()){
            sql.append(" and  arrStyles = '").append(input.cateId).append("' ");
            count.append(" and  arrStyles = '").append(input.cateId).append("' ");
        }
        if (input.singer!=null&&! input.singer.isEmpty()){
            sql.append(" and  pszSingers like '%").append(input.singer).append("%' ");
            count.append(" and  pszSingers like '%").append(input.singer).append("%' ");
        }
       /* if (input.version!=null&&! input.version.isEmpty()){
            sql.append(" and  a.arrVersions = '"+input.version+"' ");
            count.append(" and  arrVersions = '"+input.version+"' ");
        }*/
        sql.append(" limit  ").append(input.getPage()).append(" , ").append(input.getSize());
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        List<Map<String,Object>> res=GenderIsTick(list,input.userKey);
        return new ResultModel<>(res, total);
    }
    //获取歌曲分类
    public List<Map<String,Object>> GetSongsCateList(){
        return  _jdbc.queryForList("SELECT ID,pszName from songstyleinfo where 1=1");
    }
    //获取热门歌星
    public ResultModel<Map<String,Object>> GetSingerByHot(BaseInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT  unSingerCode unSingerCo ,pszName,pszSpell from singerinfo a where 1=1   ");
        count.append("SELECT count(*) from singerinfo where 1=1");
        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
            sql.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
            count.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
        }
        sql.append("order by unRanking desc");
        sql.append(" limit  ").append(input.getPage()).append(" , ").append(input.getSize());
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return new ResultModel<>(list, total);

    }
    //获取热门歌曲
    public ResultModel<Map<String,Object>> GetSongsByHot(GetSongsInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append(" SELECT ID,unSongCode ullSongCode,unSongCodex unSongCode,pszName,pszSpell,pszSingers singerName from songinfo  where 1=1");
        count.append("select count(*) from songinfo  where 1=1");
        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
            sql.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
            count.append(" and  pszName like '%").append(input.getFilter()).append("%' ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like '%").append(input.word).append("%'");
            count.append(" and  pszSpell like '%").append(input.word).append("%' ");
        }
        sql.append(" order by unRanking desc ");
        sql.append(" limit  ").append(input.getPage()).append(" , ").append(input.getSize());
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        List<Map<String,Object>> res=GenderIsTick(list,input.userKey);
        return new ResultModel<>(res, total);
    }

     public ResultModel<Map<String,Object>> GetSingerCate(){
        List<Map<String,Object>> result=new ArrayList<>();
             Map<String,Object> a= new HashMap<>();
                a.put("id",1);
                  a.put("name","男");

         result.add(a);
         a= new HashMap<>();
         a.put("id",2);
         a.put("name","女");

         result.add(a);
         a= new HashMap<>();
         a.put("id",3);
         a.put("name","乐队");
         result.add(a);
         a= new HashMap<>();
         a.put("id",4);
         a.put("name","其他");
         result.add(a);
         a= new HashMap<>();
         a.put("id",5);
         a.put("name","娱乐节目");
         result.add(a);
         return new ResultModel<>(result);
     }
    public ResultModel<Map<String,Object>> GetSingerArea(){
        List<Map<String,Object>> result=new ArrayList<>();
        Map<String,Object> a= new HashMap<>();
        a.put("id",1);
        a.put("name","大陆");
        result.add(a);
        a= new HashMap<>();
        a.put("id",2);
        a.put("name","港台");
        result.add(a);
        a= new HashMap<>();
        a.put("id",3);
        a.put("name","日韩");
        result.add(a);
        a= new HashMap<>();
        a.put("id",4);
        a.put("name","欧美");
        result.add(a);
        a= new HashMap<>();
        a.put("id",5);
        a.put("name","其他");
        result.add(a);
        return new ResultModel<>(result);
    }
    //获取歌曲排行榜
    public List<Map<String,Object>> GetSongsVerList(){
        return  _jdbc.queryForList("SELECT ID,pszName from songverinfo where 1=1");
    }
    //获取用户是否已点歌状态
    private List<Map<String,Object>> GenderIsTick(List<Map<String,Object>> list,String userKey ){
            List<MySongs> result=_mySongsRepository.findAllByUserKeyEqualsAndStateEquals(userKey,true);
        for (Map<String, Object> aList : list) {
            Boolean impact = false;
            for (MySongs aResult : result) {
                Object left = aList.get("ID");
                Integer right = aResult.getSongKey();
                if (left.toString().equals(right.toString())) {
                    impact = true;
                }
            }
            aList.put("pick", impact);
        }
        return  list;
    }

    public List<Map<String ,Object>> GetHotSongsList(String userKey){
        List<Map<String,Object>> list = _jdbc.queryForList("select ID,unSongCode ullSongCode,unSongCodex unSongCode,pszName,pszSpell,pszSingers singerName  FROM songinfo ORDER BY unRanking DESC LIMIT 0, 20");
        List<Map<String,Object>> res=GenderIsTick(list,userKey);
        return  res;
    }
}
