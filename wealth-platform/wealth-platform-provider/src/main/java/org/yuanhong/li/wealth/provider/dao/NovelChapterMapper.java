package org.yuanhong.li.wealth.provider.dao;

import org.yuanhong.li.wealth.api.meta.NovelChapter;

public interface NovelChapterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NovelChapter record);

    int insertSelective(NovelChapter record);

    NovelChapter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NovelChapter record);

    int updateByPrimaryKey(NovelChapter record);
}