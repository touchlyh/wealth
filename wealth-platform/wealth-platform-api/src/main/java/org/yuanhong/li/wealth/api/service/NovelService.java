package org.yuanhong.li.wealth.api.service;

import org.yuanhong.li.wealth.api.dto.NovelChapterDTO;
import org.yuanhong.li.wealth.api.dto.NovelDTO;
import org.yuanhong.li.wealth.api.dto.Pageable;
import org.yuanhong.li.wealth.api.meta.Novel;

public interface NovelService {

	/**
	 * 分页查询小说列表
	 * @param lastId
	 * @param pageSize
	 * @return
	 */
	public Pageable<Novel> queryNovelList(Long lastId, int pageSize);
	
	/**
	 * 查询单个小说及其章节
	 * @param novelId
	 * @return
	 */
	public NovelDTO getNovelDtoById(Long novelId);
	
	/**
	 * 查询单个章节内容
	 * @param chapterId
	 * @return
	 */
	public NovelChapterDTO getNovelChapterDto(Long chapterId);
}
