package com.efan.repository;

import com.efan.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Query("select u from Order u where u.boxId=:boxId and u.creationTime>:a and u.creationTime<=:b ")
    List<Order> findOrders(@Param("boxId")Integer boxId,@Param("a") Date start,@Param("b") Date end);
    @Query("select u from Order u where u.orderNum=:orderId  ")
     Order findOrderByFilter(@Param("orderId") String orderId);
}