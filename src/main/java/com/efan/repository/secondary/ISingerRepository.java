package com.efan.repository.secondary;

import com.efan.core.secondary.SingerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISingerRepository extends JpaRepository<SingerInfo,Integer> {

}