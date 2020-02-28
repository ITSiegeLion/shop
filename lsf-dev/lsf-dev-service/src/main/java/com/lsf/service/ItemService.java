package com.lsf.service;

import com.lsf.pojo.Items;
import com.lsf.pojo.ItemsImg;
import com.lsf.pojo.ItemsParam;
import com.lsf.pojo.ItemsSpec;

import java.util.List;

public interface ItemService {

    /**
     * 根据ID查询商品详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据ID查询商品图片
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据ID查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据ID查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);
}
