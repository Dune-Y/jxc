package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GoodsDao {
    List<Goods> getGoodInventryList(@Param("offSet") Integer offSet,@Param("rows") Integer rows, @Param("codeOrName")String codeOrName, @Param("goodsTypeId")Integer goodsTypeId) ;

    Integer getGoodsInventoryCount(String codeOrName, Integer goodsTypeId);
}
