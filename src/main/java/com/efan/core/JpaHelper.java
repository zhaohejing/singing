package com.efan.core;


import com.efan.core.entity.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试JPA原生SQL查询
 * @author Luxh
 */
public class JpaHelper {
    EntityManagerFactory emf = null;
    public void testNativeQuery1() {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT * FROM t_user";
        //创建原生SQL查询QUERY实例
        Query query =  em.createNativeQuery(sql);
                //执行查询，返回的是对象数组(Object[])列表,
                //每一个对象数组存的是相应的实体属性
                List objecArraytList = query.getResultList();
        for(int i=0;i<objecArraytList.size();i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            //使用obj[0],obj[1],obj[2]...取出属性　　　　
        }
        em.close();
    }


    /**
     * 查询的结果是实体的集合
     */

    public List<Order> GetUserList() {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT * FROM t_user";
        //创建原生SQL查询QUERY实例,指定了返回的实体类型
       Query query =  em.createNativeQuery(sql,Order.class);
       //执行查询，返回的是实体列表,
                List<Order> userList = query.getResultList();
        em.close();
        return  userList;
    }


    /**
     * 查询单个属性
     * 返回的是这个属性值的集合
     */
    public void testNativeQuery3() {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT t.name FROM t_user t";
        //创建原生SQL查询QUERY实例
     Query query =  em.createNativeQuery(sql);
                //执行查询，返回的是String类型的集合，因为name这个属性是String类型
        List<String>  resultList = query.getResultList();
        em.close();
    }


    /**
     　  　　  * 查询多个属性
     　  　　  * 返回的是这些属性值的数组的集合
     　　　 */

    public void testNativeQuery4() {
        EntityManager em = emf.createEntityManager();
        //定义SQL
        String sql = "SELECT t.name,t.age,t.email FROM t_user t";
        //创建原生SQL查询QUERY实例
      Query query =  em.createNativeQuery(sql);
          //执行查询，返回的是查询属性值数组的集合
         List objecArraytList = query.getResultList();
        for(int i=0;i<objecArraytList.size();i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            //使用obj[0],obj[1],obj[2]取出属性
        }
        em.close();

    }

}