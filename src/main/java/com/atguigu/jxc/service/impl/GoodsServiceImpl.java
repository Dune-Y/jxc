package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private LogService logService;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SaleListGoodsService saleListGoodsService;
    @Autowired
    private CustomerReturnListGoodsService customerReturnListGoodsService;

    @Override
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        Map<String, Object> map = new HashMap<>();
        // 如果页码等于0将其更改为1
        page = page == 0 ? 1 : page;
        // 页码偏移
        int offSet = (page - 1) * rows;
        // 查询当前库存量
        List<Goods> goodsList = goodsDao.getGoodInventryList(offSet, rows, codeOrName, goodsTypeId);

        for (Goods goods : goodsList) {
            // 销售总量等于销售单据的销售数据减去推过单据的退货数据 V
            goods.setSaleTotal(saleListGoodsService.getSaleTotalByGoodsId(goods.getGoodsId())
                    - customerReturnListGoodsService.getCustomerReturnTotalByGoodsId(goods.getGoodsId()));
        }

        map.put("rows", goodsList);
        // 根据商品编码或者名称获取库存总数
        map.put("total", goodsDao.getGoodsInventoryCount(codeOrName, goodsTypeId));

        logService.save(new Log(Log.SELECT_ACTION, "分页查询商品库存信息"));
        return map;
    }
}
