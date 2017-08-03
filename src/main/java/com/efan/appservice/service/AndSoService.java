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
 * Created by 45425 on 2017/5/10.
 */
@Service
public class AndSoService implements IAndSoService {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    public   JdbcTemplate _jdbc;
    private IMySongsRepository _mySongsRepository;
    @Autowired
    public  AndSoService(IMySongsRepository mySongsRepository){
        _mySongsRepository=mySongsRepository;
    }
    //获取歌手列表
    public ResultModel<Map<String,Object>> GetSingerList(GetSingerInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT unSingerCode,pszName,pszSpell,wNameWords,wSingerType,unRanking from singerinfo where 1=1");
        count.append("SELECT count(*) from singerinfo where 1=1");

        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
           sql.append(" and  pszName like '%"+input.getFilter()+"%' ");
            count.append(" and  pszName like '%"+input.getFilter()+"%' ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like '%"+input.word+"%' ");
            count.append(" and  pszSpell like '%"+input.word+"%' ");
        }
        if (input.cate>0){
            sql.append(" and  wSingerType = '"+input.cate+"' ");
            count.append(" and  wSingerType = '"+input.cate+"' ");
        }
     /*   if (input.area>0){
            sql.append(" and  wSingerAre = '"+input.area+"' ");
            count.append(" and  wSingerAre = '"+input.area+"' ");
        }*/

        sql.append(" limit  "+input.getPage()+" , "+input.getSize() );
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return  new ResultModel<Map<String,Object>>(list,total);
    }
    //获取歌曲列表
    public ResultModel<Map<String,Object>> GetSongsList(GetSongsInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT ID,unSongCode,unSongCodex,pszName,pszSpell,pszSingers from songinfo  where 1=1");
        count.append("SELECT count(*) from songinfo where 1=1");
        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
            sql.append(" and  pszName like '%"+input.getFilter()+"%' ");
            count.append(" and  pszName like '%"+input.getFilter()+"%' ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like '%"+input.word+"%' ");
            count.append(" and  pszSpell like '%"+input.word+"%' ");
        }
        if (input.cateId!=null&& !input.cateId.isEmpty()){
            sql.append(" and  a.arrStyles = '"+input.cateId+"' ");
            count.append(" and  arrStyles = '"+input.cateId+"' ");
        }
        if (input.singer!=null&&! input.singer.isEmpty()){
            sql.append(" and  pszSingers like '%"+input.singer+"%' ");
            count.append(" and  pszSingers like '%"+input.singer+"%' ");
        }
       /* if (input.version!=null&&! input.version.isEmpty()){
            sql.append(" and  a.arrVersions = '"+input.version+"' ");
            count.append(" and  arrVersions = '"+input.version+"' ");
        }*/
        sql.append(" limit  "+input.getPage()+" , "+input.getSize() );
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        List<Map<String,Object>> res=GenderIsTick(list,input.userKey);
        return  new ResultModel<Map<String,Object>>(res,total);
    }
    //获取歌曲分类
    public List<Map<String,Object>> GetSongsCateList(){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT ID,pszName from songstyleinfo where 1=1");
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return  list;
    }
    //获取热门歌星
    public ResultModel<Map<String,Object>> GetSingerByHot(BaseInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT unSingerCode,pszName,pszSpell,wSingerType,unRanking from singerinfo a where 1=1   ");
        count.append("SELECT count(*) from singerinfo where 1=1");
        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
            sql.append(" and  pszName like '%"+input.getFilter()+"%' ");
            count.append(" and  pszName like '%"+input.getFilter()+"%' ");
        }
        sql.append("order by unRanking desc");
        sql.append(" limit  "+input.getPage()+" , "+input.getSize() );
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return  new ResultModel<Map<String,Object>>(list,total);

    }
    //获取热门歌曲
    public ResultModel<Map<String,Object>> GetSongsByHot(GetSongsInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append(" SELECT ID,unSongCode,unSongCodex,pszName,pszSpell,pszSingers from songinfo  where 1=1");
        count.append("select count(*) from songinfo  where 1=1");
        if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
            sql.append(" and  pszName like '%"+input.getFilter()+"%' ");
            count.append(" and  pszName like '%"+input.getFilter()+"%' ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like '%"+input.word+"%'");
            count.append(" and  pszSpell like '%"+input.word+"%' ");
        }
        sql.append(" order by unRanking desc ");
        sql.append(" limit  "+input.getPage()+" , "+input.getSize() );
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        List<Map<String,Object>> res=GenderIsTick(list,input.userKey);
        return  new ResultModel<Map<String,Object>>(res,total);
    }

     public ResultModel<Map<String,Object>> GetSingerCate(){
        List<Map<String,Object>> result=new ArrayList<>();
             Map<String,Object> a=new HashMap<String,Object>();
                a.put("id",1);
                  a.put("name","男");

         result.add(a);
         a=new HashMap<String,Object>();
         a.put("id",2);
         a.put("name","女");

         result.add(a);
         a=new HashMap<String,Object>();
         a.put("id",3);
         a.put("name","乐队");
         result.add(a);
         a=new HashMap<String,Object>();
         a.put("id",4);
         a.put("name","其他");
         result.add(a);
         a=new HashMap<String,Object>();
         a.put("id",5);
         a.put("name","娱乐节目");
         result.add(a);
         return  new ResultModel<Map<String, Object>>(result);
     }
    public ResultModel<Map<String,Object>> GetSingerArea(){
        List<Map<String,Object>> result=new ArrayList<>();
        Map<String,Object> a=new HashMap<String,Object>();
        a.put("id",1);
        a.put("name","大陆");
        result.add(a);
        a=new HashMap<String,Object>();
        a.put("id",2);
        a.put("name","港台");
        result.add(a);
        a=new HashMap<String,Object>();
        a.put("id",3);
        a.put("name","日韩");
        result.add(a);
        a=new HashMap<String,Object>();
        a.put("id",4);
        a.put("name","欧美");
        result.add(a);
        a=new HashMap<String,Object>();
        a.put("id",5);
        a.put("name","其他");
        result.add(a);
        return  new ResultModel<Map<String, Object>>(result);
    }
    //获取歌曲排行榜
    public List<Map<String,Object>> GetSongsVerList(){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT ID,pszName from songverinfo where 1=1");
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return  list;
    }
    //获取用户是否已点歌状态
    private List<Map<String,Object>> GenderIsTick(List<Map<String,Object>> list,String userKey ){
            List<MySongs> result=_mySongsRepository.findAllByUserKeyEqualsAndStateEquals(userKey,true);
        for (int i = 0; i < list.size(); i++) {
            Boolean ispick=false;
            for (int j = 0; j < result.size(); j++) {
                Object left=list.get(i).get("ID");
                Integer right=result.get(j).getSongKey();
                if (left.toString().equals(right.toString())){
            ispick=true;
                }
            }
            list.get(i).put("pick",ispick);
        }
        return  list;
    }

    public List<Map<String ,Object>> GetHotSongsList(String userKey){
        StringBuilder sb=new StringBuilder();
        sb.append("select a.ID,a.unSongCode,a.unSongCodex,a.pszName,a.pszSpell,a.pszSingers,a.unRanking,a.arrStyles FROM songinfo AS a ORDER BY a.unRanking DESC LIMIT 0, 20");
        List<Map<String,Object>> list = _jdbc.queryForList(sb.toString());
        List<Map<String,Object>> res=GenderIsTick(list,userKey);
        return  res;
    }
}
