package com.atguigu.jxc.service;

import java.util.Map;

public interface GoodsService {


    /**
     * @description
     */
    Map<String,Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

}
