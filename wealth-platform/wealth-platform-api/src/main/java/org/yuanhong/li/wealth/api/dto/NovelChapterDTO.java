package org.yuanhong.li.wealth.api.dto;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.NovelChapter;
import org.yuanhong.li.wealth.api.meta.NovelChapterMedia;

public class NovelChapterDTO extends NovelChapter{

	private static final long serialVersionUID = -4011466393675863063L;

	private List<NovelChapterMedia> mediaList;

	public List<NovelChapterMedia> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<NovelChapterMedia> mediaList) {
		this.mediaList = mediaList;
	}
}
