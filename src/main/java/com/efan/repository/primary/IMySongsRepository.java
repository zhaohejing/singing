package com.efan.repository.primary;

import com.efan.core.primary.MySongs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 45425 on 2017/5/12.
 */
@Repository
public interface IMySongsRepository extends JpaRepository<MySongs,Long> {
    Page<MySongs> findMySongsBySongNameLikeAndUserKeyEquals(String songName,String userKey, Pageable pageable);
    Page<MySongs> findAll (Pageable pageable);
    List<MySongs> findAllByUserKeyEqualsAndStateEquals(String userKey,Boolean state);
    Page<MySongs> findAllByUserKeyEqualsAndStateEquals(String userKey,Boolean state,Pageable pageable);
}
