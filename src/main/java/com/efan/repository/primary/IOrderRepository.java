package com.efan.repository.primary;

import com.efan.core.primary.Order;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Query("select u from Order u where u.boxId=:boxId and u.state=1 and u.fromTime>:a and u.toTime<=:b ")
    List<Order> findOrders(@Param("boxId")String boxId,@Param("a") Date start,@Param("b") Date end);

    @Query("select u from Order u where u.boxId=:boxId and u.userKey=:userKey  and u.fromTime<:a  and u.toTime>:a order by  u.creationTime ")
    List<Order> findOrdersbyFilter(@Param("boxId")String boxId,@Param("userKey")String userKey,   @Param("a") Date start);

    @Query("select u from Order u where u.boxId=:boxId  and u.fromTime>= :a  and u.toTime<= :b  and u.state=1"  )
    List<Order> findOrdersbyKey(@Param("boxId")String boxId,@Param("a") Date start,@Param("b") Date end);

    Page<Order> findAllByUserKey(String userKey, Pageable pageable   );

    Order findByOrderNumEquals(String order);
    Order findByEfanOrderEquals(String order);
    @Query("select u from Order u where u.boxId=:boxId   and u.state=1"  )
    List<Order> findboxandstate(@Param("boxId")String boxId);

    @Query("select u from Order u where u.boxId=:boxId and u.userKey<>:userKey and u.state=1  and u.fromTime<=:a  and u.toTime>=:a order by  u.creationTime ")
    List<Order> findbyFilter(@Param("boxId")String boxId,@Param("userKey")String use,  @Param("a") Date start);
}