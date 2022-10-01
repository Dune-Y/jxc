package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.service.LogService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Autowired
    private LogService logService;

    @Override

    public String loadGoodsType() {
        logService.save(new Log(Log.SELECT_ACTION, "查询商品类别信息"));
        return this.getAllGoodsType(-1).toString();
    }


    /*
     * 递归查询所有商品类别
     * */
    private JsonArray getAllGoodsType(Integer parentId) {
        JsonArray array = this.getGoodsTypeByParentId(parentId);
        for (int i = 0; i < array.size(); i++) {
            JsonObject obj = (JsonObject) array.get(i);
            if (obj.get("state").getAsString().equals("open")) {
                // 如果是叶子节点
                continue;
            } else {
                // 如果是根节点,继续递归查询
                obj.add("children", this.getAllGoodsType(obj.get("id").getAsInt()));
            }
        }
        return array;
    }

    private JsonArray getGoodsTypeByParentId(Integer parentId) {
        JsonArray array = new JsonArray();
        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByParentId(parentId);
        //遍历商品类别
        for (GoodsType goodsType : goodsTypeList) {
            JsonObject obj = new JsonObject();
            obj.addProperty("id",goodsType.getGoodsTypeId());  //id
            obj.addProperty("text",goodsType.getGoodsTypeName());  //类别名称
            if (goodsType.getGoodsTypeState()==1) {
                obj.addProperty("state","closed"); // 根节点
            }else {
                obj.addProperty("state","open"); // 子节点
            }
            obj.addProperty("iconCls","goods-type"); // 图标默认为自定义类型的商品图标
            JsonObject attributes = new JsonObject(); //扩展属性
            attributes.addProperty("state",goodsType.getGoodsTypeState()); //加入当前节点的类型
            obj.add("attributes",attributes);
            array.add(obj);
        }
        return array;
    }


}
