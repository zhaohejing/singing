package com.efan.appservice.service;

import com.efan.appservice.iservice.IAndSoService;
import com.efan.controller.inputs.BaseInput;
import com.efan.controller.inputs.GetSingerInput;
import com.efan.controller.inputs.GetSongsInput;
import com.efan.controller.inputs.VendorInput;
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
            sql.append(" and  arrSingers like '%").append(input.singer).append("%' ");
            count.append(" and  arrSingers like '%").append(input.singer).append("%' ");
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
    //获取热点分类
    public List<Map<String,Object>> GetHotCateList(){
        return  _jdbc.queryForList(" SELECT * FROM singerinfo WHERE wSingerType=40 ORDER BY unRanking DESC");
    }

    //获取热门歌星
    public ResultModel<Map<String,Object>> GetSingerByHot(BaseInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();
        sql.append("SELECT  unSingerCode unSingerCo ,pszName,pszSpell from singerinfo  where 1=1  and wSingerType<>40 ");
        count.append("SELECT count(*) from singerinfo where 1=1  and wSingerType<>40 ");
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

    //获取歌曲分类
     public ResultModel<Map<String,Object>> GetSingerCate(){
        List<Map<String,Object>> result=new ArrayList<>();
             Map<String,Object> a= new HashMap<>();
             a.put("id",11);
             a.put("name","内地男歌手");

         result.add(a);
         a= new HashMap<>();
         a.put("id",21);
         a.put("name","内地女歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",12);
         a.put("name","港台男歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",22);
         a.put("name","港台女歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",13);
         a.put("name","日韩男歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",23);
         a.put("name","日韩女歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",14);
         a.put("name","欧美男歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",24);
         a.put("name","欧美女歌手");
         result.add(a);

         a= new HashMap<>();
         a.put("id",30);
         a.put("name","乐队组合");
         result.add(a);

         a= new HashMap<>();
         a.put("id",40);
         a.put("name","娱乐节目");
         result.add(a);
         return new ResultModel<>(result);
     }
///获取排行榜下的歌曲
     public  ResultModel<Map<String,Object>> GetSongsByVendor(VendorInput input){
         StringBuilder sql=new StringBuilder();
         StringBuilder count=new StringBuilder();
         sql.append("SELECT b.*  FROM rankingsongs a INNER JOIN songinfo b ON a.unSongCode = b.unSongCode AND a.unSongCodex = b.unSongCodex where 1=1");
         count.append("SELECT count(1) FROM rankingsongs a INNER JOIN songinfo b ON a.unSongCode = b.unSongCode AND a.unSongCodex = b.unSongCodex where 1=1");

         if (input.getFilter()!=null&& !input.getFilter().isEmpty()){
             sql.append(" and a.unVenderId= ").append(input.getFilter());
             count.append(" and a.unVenderId= ").append(input.getFilter());
         }
         sql.append(" limit  ").append(input.getPage()).append(" , ").append(input.getSize());
         Long total=_jdbc.queryForObject(count.toString(),Long.class);
         List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
         List<Map<String,Object>> res=GenderIsTick(list,input.userKey);
         return new ResultModel<>(res, total);
     }

    //获取歌曲排行榜
    public List<Map<String,Object>> GetSongsVerList(){
        return  _jdbc.queryForList("SELECT ID,pszName from vendor where 1=1");
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

    public List<Map<String ,Object>> GetHotSongsList(String userKey,String cate){
        List<Map<String,Object>> list = _jdbc.queryForList("select ID,unSongCode ullSongCode,unSongCodex unSongCode,pszName,pszSpell,pszSingers singerName  FROM songinfo" +
                "where  arrSingers like '%"+cate+"%' ORDER BY unRanking DESC LIMIT 0, 20");
        List<Map<String,Object>> res=GenderIsTick(list,userKey);
        return  res;
    }
}
