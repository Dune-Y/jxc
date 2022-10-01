package com.atguigu.jxc.controller;


import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {


    @Autowired
    private GoodsTypeService goodsTypeService;
    //http://localhost:8080/goodsType/loadGoodsType
    @RequestMapping("/loadGoodsType")
    public String loadGoodsType(){
        return goodsTypeService.loadGoodsType();
    }
}
