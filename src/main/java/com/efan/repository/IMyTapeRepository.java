package com.efan.repository;

import com.efan.core.entity.MyTape;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by 45425 on 2017/5/8.
 */
@Repository
public interface IMyTapeRepository extends JpaRepository<MyTape,Long> {
    Page<MyTape> findMyTapeBySongKey(String songKey,Pageable pageable);
}
