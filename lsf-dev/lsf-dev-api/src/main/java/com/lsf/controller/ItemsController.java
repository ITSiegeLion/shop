package com.lsf.controller;

import com.lsf.pojo.Items;
import com.lsf.pojo.ItemsImg;
import com.lsf.pojo.ItemsParam;
import com.lsf.pojo.ItemsSpec;
import com.lsf.pojo.vo.ItemInfoVO;
import com.lsf.service.ItemService;
import com.lsf.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品
 */
@Api(value = "商品", tags = "商品相关的接口")
@RestController
@RequestMapping("items")
public class ItemsController {
    final static Logger logger = LoggerFactory.getLogger(ItemsController.class);
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "商品详情", notes = "查询商品详细信息", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JsonResult itemInfo(
             @ApiParam(name = "itemId", value = "商品ID", required = true)
             @PathVariable String itemId){

        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList =  itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemSpecList(itemsSpecs);
        itemInfoVO.setItemParams(itemsParam);
        return JsonResult.getSuccessResult(itemInfoVO);
    }

}
