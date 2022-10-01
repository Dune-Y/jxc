package com.atguigu.jxc.controller;

import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.GoodsTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;



    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param codeOrName   商品名称或编码
     * @param goodsTypeId 商品类别ID
     * @return
     */

    @RequestMapping("/listInventory")
    @RequiresPermissions(value = "当前库存查询")
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        return goodsService.listInventory(page, rows, codeOrName, goodsTypeId);
    }




}
