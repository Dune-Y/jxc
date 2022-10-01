package com.atguigu.jxc.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaleListGoodsDao {

    Integer getSaleTotalByGoodsId(Integer goodsId);
}
