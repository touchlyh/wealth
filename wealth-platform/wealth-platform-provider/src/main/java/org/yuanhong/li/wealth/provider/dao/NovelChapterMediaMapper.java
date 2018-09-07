package org.yuanhong.li.wealth.provider.dao;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.NovelChapterMedia;

public interface NovelChapterMediaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NovelChapterMedia record);

    int insertSelective(NovelChapterMedia record);

    NovelChapterMedia selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NovelChapterMedia record);

    int updateByPrimaryKey(NovelChapterMedia record);
    
    List<NovelChapterMedia> selectByChapterId(Long chapterId);
}