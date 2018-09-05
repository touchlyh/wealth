package org.yuanhong.li.wealth.provider.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.yuanhong.li.wealth.api.meta.Item;
import org.yuanhong.li.wealth.api.service.ItemService;
import org.yuanhong.li.wealth.provider.dao.ItemMapper;

@Component("itemService")
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;
	
	@Override
	public List<Item> queryAllItemsByPrice() {
		return itemMapper.queryAllItemsByPrice();
	}

	@Override
	public Item getById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

}
