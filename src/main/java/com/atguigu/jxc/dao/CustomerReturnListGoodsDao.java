package com.atguigu.jxc.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerReturnListGoodsDao {
    Integer getCustomerReturnTotalByGoodsId(Integer goodsId);
}
