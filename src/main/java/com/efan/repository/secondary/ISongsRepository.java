package com.efan.repository.secondary;

import com.efan.core.secondary.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISongsRepository extends JpaRepository<SongInfo,Integer> {

}