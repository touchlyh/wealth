package org.yuanhong.li.wealth.provider.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yuanhong.li.wealth.api.dto.NovelChapterDTO;
import org.yuanhong.li.wealth.api.dto.NovelDTO;
import org.yuanhong.li.wealth.api.dto.Pageable;
import org.yuanhong.li.wealth.api.meta.Novel;
import org.yuanhong.li.wealth.api.meta.NovelChapter;
import org.yuanhong.li.wealth.api.meta.NovelChapterMedia;
import org.yuanhong.li.wealth.api.service.NovelService;
import org.yuanhong.li.wealth.provider.dao.NovelChapterMapper;
import org.yuanhong.li.wealth.provider.dao.NovelChapterMediaMapper;
import org.yuanhong.li.wealth.provider.dao.NovelMapper;

@Component("novelService")
public class NovelServiceImpl implements NovelService {
	
	@Resource
	private NovelMapper novelMapper;
	
	@Resource
	private NovelChapterMapper novelChapterMapper;
	
	@Resource
	private NovelChapterMediaMapper novelChapterMediaMapper;

	@Override
	public Pageable<Novel> queryNovelList(Long lastId, int pageSize) {
		Date modifyTime = null;
		if(lastId == null) {
			modifyTime = new Date();
		}else {
			Novel novel = novelMapper.selectByPrimaryKey(lastId);
			modifyTime = novel== null ? new Date() : novel.getModifyTime();
		}
		
		List<Novel> novelList = novelMapper.queryByModifyTime(modifyTime, pageSize);
		Pageable<Novel> page = new Pageable<Novel>();
		page.setHasNext(!CollectionUtils.isEmpty(novelList));
		if(!CollectionUtils.isEmpty(novelList)) {
			Novel novel = novelList.get(novelList.size()-1);
			page.setLastId(novel.getId());
			page.setData(novelList);
		}
		return page;
	}

	@Override
	public NovelDTO getNovelDtoById(Long novelId) {
		Novel novel = novelMapper.selectByPrimaryKey(novelId);
		if(novel != null) {
			List<NovelChapter> chapterList = novelChapterMapper.selectByNovelId(novelId);
			return this.assemberNovelDto(novel, chapterList);
		}
		return null;
	}

	private NovelDTO assemberNovelDto(Novel novel, List<NovelChapter> chapterList) {
		NovelDTO novelDto = new NovelDTO();
		BeanUtils.copyProperties(novel, novelDto);
		novelDto.setNovelChapterList(chapterList);
		return novelDto;
	}

	@Override
	public NovelChapterDTO getNovelChapterDto(Long chapterId) {
		NovelChapter chapter = novelChapterMapper.selectByPrimaryKey(chapterId);
		if(chapter != null) {
			List<NovelChapterMedia> mediaList = novelChapterMediaMapper.selectByChapterId(chapterId);
			return this.assemberChapterDto(chapter, mediaList);
		}
		return null;
	}

	private NovelChapterDTO assemberChapterDto(NovelChapter chapter, List<NovelChapterMedia> mediaList) {
		NovelChapterDTO chapterDto = new NovelChapterDTO();
		BeanUtils.copyProperties(chapter, chapterDto);
		chapterDto.setMediaList(mediaList);
		return chapterDto;
	}

}
