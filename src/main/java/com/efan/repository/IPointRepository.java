package com.efan.repository;

import com.efan.core.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPointRepository extends JpaRepository<Point,Long> {

}