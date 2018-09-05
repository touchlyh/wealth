package org.yuanhong.li.wealth.api.service;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.Item;

public interface ItemService {

	/**
	 * 查询所有的商品列表，按照价格排序
	 * @return
	 */
	public List<Item> queryAllItemsByPrice();
	
	/**
	 * 根据ID查询商品详情
	 * @param id
	 * @return
	 */
	public Item getById(Long id);
}
