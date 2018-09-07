package org.yuanhong.li.wealth.controller.novel;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yuanhong.li.wealth.api.consts.ResultCode;
import org.yuanhong.li.wealth.api.consts.RoleEnum;
import org.yuanhong.li.wealth.api.dto.NovelChapterDTO;
import org.yuanhong.li.wealth.api.dto.NovelDTO;
import org.yuanhong.li.wealth.api.dto.Pageable;
import org.yuanhong.li.wealth.api.meta.Novel;
import org.yuanhong.li.wealth.api.meta.RoleResource;
import org.yuanhong.li.wealth.controller.utils.BaseUserContext;
import org.yuanhong.li.wealth.facade.NovelFacade;
import org.yuanhong.li.wealth.facade.UserRoleFacade;

@Controller
public class NovelController {
	
	@Resource
	private NovelFacade novelFacade;
	
	@Resource
	private UserRoleFacade userRoleFacade;

	/**
	 * 获取漫画列表
	 * @return
	 */
	@RequestMapping(value = "/api/novel/list", method = RequestMethod.GET)
	public String queryNovelList(Model model,HttpServletResponse httpResponse,
			@RequestParam(value = "lastId",required = false) Long lastId,
			@RequestParam(value = "size",required = false, defaultValue="20") int pageSize) {
		Pageable<Novel> novelPage = novelFacade.queryNovelList(lastId, pageSize);
		model.addAttribute("hasNext", novelPage.isHasNext());
		model.addAttribute("lastId",novelPage.getLastId());
		model.addAttribute("data", novelPage.getData());
		return "test/test";
	}
	
	/**
	 * 根据ID查询
	 * @return
	 */
	@RequestMapping(value = "/api/novel", method = RequestMethod.GET)
	public String getNovel(Model model,HttpServletResponse httpResponse,
			@RequestParam(value = "id",required = true) Long id) {
		NovelDTO novelDto = novelFacade.getById(id);
		if(novelDto == null) {
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "/api/system/info";
		}
		model.addAttribute("novel", novelDto);
		return "test/test";
	}
	
	/**
	 * 查询具体的章节
	 * @return
	 */
	@RequestMapping(value = "/api/novel/chapter", method = RequestMethod.GET)
	public String getNovelChapter(Model model,HttpServletResponse httpResponse,
			@RequestParam(value = "novelId",required = true) Long novelId,
			@RequestParam(value = "chapterId",required = true) Long chapterId) {
		NovelChapterDTO chapterDto = novelFacade.getChapterById(chapterId);
		if(chapterDto == null) {
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "/api/system/info";
		}
		Long userId = BaseUserContext.getUserId();
		if(userId == null) { //没有权限跳转登录
			List<RoleResource> resList = userRoleFacade.getRoleResourceList(RoleEnum.VISTOR.getCode(), novelId);
			if(CollectionUtils.isEmpty(resList)) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return "redirect:/api/user/login?next=/api/novel?id="+novelId;
			}
			List<Long> chapterList = resList.stream().map(rl->rl.getResId()).collect(Collectors.toList());
			if(!chapterList.contains(chapterId)) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return "redirect:/api/user/login?next=/api/novel?id="+novelId;
			}
		} else { //没有权限跳转购买
			List<RoleResource> resList = userRoleFacade.getUserResourceList(userId, novelId);
			if(CollectionUtils.isEmpty(resList)) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return "/api/system/info?code="+ResultCode.OP_FORBIDON.getCode();
			}
			List<Long> chapterList = resList.stream().map(rl->rl.getResId()).collect(Collectors.toList());
			if(!chapterList.contains(chapterId)) {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return "/api/system/info?code="+ResultCode.OP_FORBIDON.getCode();
			}
		}
		model.addAttribute("chapter", chapterDto);
		return "test/test";
	}
}
