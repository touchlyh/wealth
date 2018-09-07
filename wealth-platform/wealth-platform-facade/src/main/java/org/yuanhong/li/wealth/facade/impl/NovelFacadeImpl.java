package org.yuanhong.li.wealth.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.yuanhong.li.wealth.api.dto.NovelChapterDTO;
import org.yuanhong.li.wealth.api.dto.NovelDTO;
import org.yuanhong.li.wealth.api.dto.Pageable;
import org.yuanhong.li.wealth.api.meta.Novel;
import org.yuanhong.li.wealth.api.service.NovelService;
import org.yuanhong.li.wealth.facade.NovelFacade;

@Component("novelFacade")
public class NovelFacadeImpl implements NovelFacade{
	
	@Resource
	private NovelService novelService;

	@Override
	public Pageable<Novel> queryNovelList(Long lastId, int pageSize) {
		return novelService.queryNovelList(lastId, pageSize);
	}

	@Override
	public NovelDTO getById(Long id) {
		return novelService.getNovelDtoById(id);
	}

	@Override
	public NovelChapterDTO getChapterById(Long chapterId) {
		return novelService.getNovelChapterDto(chapterId);
	}

}
