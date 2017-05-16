package com.efan.appservice.service;

import com.efan.appservice.iservice.IAndSoService;
import com.efan.controller.inputs.GetSingerInput;
import com.efan.core.page.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

    //获取diskette分科
    public ResultModel<Map<String,Object>> GetDiscoStyle(){
        List<Map<String,Object>> list = _jdbc.queryForList("SELECT * from DiscoStyleInfo");
        return  new ResultModel<Map<String,Object>>(list,(long)list.size());
    }
    //获取歌手列表
    public ResultModel<Map<String,Object>> GetSingerList(GetSingerInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();

        sql.append("SELECT unSingerCode,pszName,pszSpell from SingerInfo where 1=1");
        count.append("SELECT count(*) from SingerInfo where 1=1");

        if (input.filter!=null&& !input.filter.isEmpty()){
           sql.append(" and  pszName like %"+input.filter+"% ");
            count.append(" and  pszName like %"+input.filter+"% ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like %"+input.word+"% ");
            count.append(" and  pszSpell like %"+input.word+"% ");
        }
        sql.append(" limit  "+input.getPage()+" , "+input.getSize() );
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return  new ResultModel<Map<String,Object>>(list,total);
    }
    //获取歌曲列表
    public ResultModel<Map<String,Object>> GetSongsList(GetSingerInput input){
        StringBuilder sql=new StringBuilder();
        StringBuilder count=new StringBuilder();

        sql.append("SELECT ullSongCode,unSongCode,pszName,pszSpell from SongInfo where 1=1");
        count.append("SELECT count(*) from SongInfo where 1=1");

        if (input.filter!=null&& !input.filter.isEmpty()){
            sql.append(" and  pszName like %"+input.filter+"% ");
            count.append(" and  pszName like %"+input.filter+"% ");
        }
        if (input.word!=null&& !input.word.isEmpty()){
            sql.append(" and  pszSpell like %"+input.word+"% ");
            count.append(" and  pszSpell like %"+input.word+"% ");
        }
        sql.append(" limit  "+input.getPage()+" , "+input.getSize() );
        Long total=_jdbc.queryForObject(count.toString(),Long.class);
        List<Map<String,Object>> list = _jdbc.queryForList(sql.toString());
        return  new ResultModel<Map<String,Object>>(list,total);
    }
}
