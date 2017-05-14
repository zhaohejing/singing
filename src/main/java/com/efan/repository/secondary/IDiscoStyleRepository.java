package com.efan.repository.secondary;

import com.efan.core.primary.MySongs;
import com.efan.core.secondary.DiscoStyleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by 45425 on 2017/5/12.
 */
@Repository
public interface IDiscoStyleRepository extends JpaRepository<DiscoStyleInfo,Integer> {

}