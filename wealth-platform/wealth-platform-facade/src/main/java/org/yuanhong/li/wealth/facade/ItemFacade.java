package org.yuanhong.li.wealth.facade;

import java.util.List;

import org.yuanhong.li.wealth.facade.vo.item.ItemVO;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;

public interface ItemFacade {

	/**
	 * 查询所有的商品
	 * @return
	 */
	public WealthResult<List<ItemVO>> queryAllItems();
	
	/**
	 * 基于ID查询商品
	 * @return
	 */
	public WealthResult<ItemVO> getById(Long id);
}
