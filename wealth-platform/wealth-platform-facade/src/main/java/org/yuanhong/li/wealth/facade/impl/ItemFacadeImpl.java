package org.yuanhong.li.wealth.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yuanhong.li.wealth.api.consts.ResultCode;
import org.yuanhong.li.wealth.api.meta.Item;
import org.yuanhong.li.wealth.api.service.ItemService;
import org.yuanhong.li.wealth.facade.ItemFacade;
import org.yuanhong.li.wealth.facade.vo.item.ItemVO;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;

@Component("itemFacade")
public class ItemFacadeImpl implements ItemFacade {
	
	@Resource
	private ItemService itemService;

	@Override
	public WealthResult<List<ItemVO>> queryAllItems() {
		List<Item> itemList = itemService.queryAllItemsByPrice();
		if(CollectionUtils.isEmpty(itemList)) {
			return new WealthResult<List<ItemVO>>(ResultCode.RES_NOT_EXSISTED.getCode(),"商品列表为空",null);
		}
		List<ItemVO> itemVOList = new ArrayList<ItemVO>();
		for(Item item : itemList) {
			ItemVO itemVO = new ItemVO();
			BeanUtils.copyProperties(item, itemVO);
			itemVOList.add(itemVO);
		}
		return new WealthResult<List<ItemVO>>(ResultCode.SUCCESS, itemVOList);
	}

	@Override
	public WealthResult<ItemVO> getById(Long id) {
		Item item = itemService.getById(id);
		if(item == null) {
			return new WealthResult<ItemVO>(ResultCode.RES_NOT_EXSISTED.getCode(),"商品为空",null);
		}
		ItemVO itemVO = new ItemVO();
		BeanUtils.copyProperties(item, itemVO);
		return new WealthResult<ItemVO>(ResultCode.SUCCESS, itemVO);
	}

}
