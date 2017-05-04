package com.efan.repository;

import com.efan.core.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 45425 on 2017/5/4.
 */
@Repository
public interface ISongRepository extends JpaRepository<Song,Long> {
}
