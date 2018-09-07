package org.yuanhong.li.wealth.provider.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yuanhong.li.wealth.api.meta.Novel;

public interface NovelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    Novel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);
    
    List<Novel> queryByModifyTime(@Param("modifyTime") Date modifyTime, @Param("limit") int limit);
}