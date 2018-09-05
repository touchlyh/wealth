package org.yuanhong.li.wealth.controller.mall;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yuanhong.li.wealth.facade.ItemFacade;
import org.yuanhong.li.wealth.facade.vo.item.ItemVO;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;

@Controller
public class ItemController {
	
	@Resource
	private ItemFacade itemFacade;

	@RequestMapping(value = "/api/item", method = RequestMethod.GET)
	public String getItemById(Model model, HttpServletResponse httpResponse,
            @RequestParam(value = "id",required = true) Long id) {
		WealthResult<ItemVO> itemResult = itemFacade.getById(id);
		if(!itemResult.isSuccess()) {
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "/api/system/info";
		}
		ItemVO item = itemResult.getData();
		model.addAttribute("item", item);
		return "/mall/item";
	}
	
	@RequestMapping(value = "/api/item/list", method = RequestMethod.GET)
	public String getAllItems(Model model, HttpServletResponse httpResponse) {
		return null;
	}
}
