package org.yuanhong.li.wealth.api.dto;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.Novel;
import org.yuanhong.li.wealth.api.meta.NovelChapter;

public class NovelDTO extends Novel{

	private static final long serialVersionUID = 7445644945992974182L;

	private List<NovelChapter> novelChapterList;

	public List<NovelChapter> getNovelChapterList() {
		return novelChapterList;
	}

	public void setNovelChapterList(List<NovelChapter> novelChapterList) {
		this.novelChapterList = novelChapterList;
	}
	
}
