package com.lsf.mapper;


import com.lsf.my.mapper.MyMapper;
import com.lsf.pojo.Category;
import com.lsf.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends MyMapper<Category> {
    public List getSubCatList(Integer id);
    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}