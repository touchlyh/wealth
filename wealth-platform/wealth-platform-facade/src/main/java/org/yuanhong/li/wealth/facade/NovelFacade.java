package org.yuanhong.li.wealth.facade;

import org.yuanhong.li.wealth.api.dto.NovelChapterDTO;
import org.yuanhong.li.wealth.api.dto.NovelDTO;
import org.yuanhong.li.wealth.api.dto.Pageable;
import org.yuanhong.li.wealth.api.meta.Novel;

public interface NovelFacade {

	/**
	 * 分页查询小说列表
	 * @param lastId
	 * @param pageSize
	 * @return
	 */
	Pageable<Novel> queryNovelList(Long lastId, int pageSize);
	
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	NovelDTO getById(Long id);
	
	/**
	 * 查询具体章节
	 * @param chapterId
	 * @return
	 */
	NovelChapterDTO getChapterById(Long chapterId);
}
