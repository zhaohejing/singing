package com.efan.repository;

import com.efan.core.entity.Hot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 45425 on 2017/5/4.
 */
@Repository
public interface IHotRepository extends JpaRepository<Hot,Long> {

}
