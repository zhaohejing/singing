package com.efan.repository;

import com.efan.core.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 45425 on 2017/5/4.
 */
@Repository
public interface IBoxRepository extends JpaRepository<Box,Long> {
    //利用原生的SQL进行修改操作
    @Query(value = "update efan_box set is_delete=1  where id=?1 ", nativeQuery = true)
    @Modifying
     void softDelete(int id);

    //分页
}
