package org.yuanhong.li.wealth.provider.dao;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
    
    List<Item> queryAllItemsByPrice();
    
}