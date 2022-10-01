package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerReturnListGoodsDao;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerReturnListGoodsServiceImpl implements CustomerReturnListGoodsService {
    @Autowired
    CustomerReturnListGoodsDao customerReturnListGoodsDao;


    @Override
    public Integer getCustomerReturnTotalByGoodsId(Integer goodsId) {
        // 1.获取退货数量
        Integer n = customerReturnListGoodsDao.getCustomerReturnTotalByGoodsId(goodsId);
        return n == null ? 0 : n;
    }
}
