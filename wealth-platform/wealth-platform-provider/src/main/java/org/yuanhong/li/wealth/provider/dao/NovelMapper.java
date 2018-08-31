package org.yuanhong.li.wealth.provider.dao;

import org.yuanhong.li.wealth.api.meta.Novel;

public interface NovelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    Novel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);
}