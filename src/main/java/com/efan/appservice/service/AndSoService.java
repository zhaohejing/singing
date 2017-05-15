package com.efan.appservice.service;

import com.efan.appservice.iservice.IAndSoService;
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
    public  List<Map<String,Object>> GetDiscoStyle(){
        List<Map<String,Object>> list = _jdbc.queryForList("SELECT * from DiscoStyleInfo");
        return  list;
    }
    public List<Map<String,Object>> GetSingerList(){
        List<Map<String,Object>> list = _jdbc.queryForList("SELECT * from DiscoStyleInfo");
        return  list;
    }

}
