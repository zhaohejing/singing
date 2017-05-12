package com.efan.repository;

import com.efan.core.entity.MySongs;
import com.efan.core.entity.MyTape;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 45425 on 2017/5/12.
 */
@Repository
public interface IMySongsRepository extends JpaRepository<MySongs,Long> {
    Page<MySongs> findMySongsBySongNameLikeAndUserKeyEquals(String songName,String userKey, Pageable pageable);
}
