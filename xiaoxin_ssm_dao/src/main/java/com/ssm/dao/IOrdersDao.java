package com.ssm.dao;

import com.ssm.domain.Member;
import com.ssm.domain.Orders;
import com.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IOrdersDao
 * @projectName xiaoxin_ssm
 * @description: TODO
 * @date 2020/4/9 10:06
 */
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.ssm.dao.IProductDao.findById")),

    })
    public List<Orders> findAll() throws Exception;

    //多表的操作

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column ="id",javaType = java.util.List.class,many = @Many(select = "com.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;

    @Delete("delete from orders where id=#{orderId}")
    void deleteByOrders_Id(String orderId);

    @Delete("delete from order_traveller where orderId=#{orderId}")
    void deleteByOrder_traveller(String orderId);
}
