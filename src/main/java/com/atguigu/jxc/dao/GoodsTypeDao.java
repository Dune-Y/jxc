package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsTypeDao {
    List<GoodsType> getAllGoodsTypeByParentId(Integer parentId);
}
