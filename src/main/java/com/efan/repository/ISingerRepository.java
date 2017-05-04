package com.efan.repository;

import com.efan.core.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 45425 on 2017/5/4.
 */
@Repository
public interface ISingerRepository extends JpaRepository<Singer,Long> {
}
