package org.yuanhong.li.wealth.facade;

import java.util.List;

import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.UserProfileVO;

public interface UserRoleFacade {

	/**
	 * 基于token查询对应的用户信息
	 * @param token
	 * @return
	 */
	public WealthResult<UserProfileVO> getUserByToken(String token);
	
	/**
	 * 基于角色ID查询对应的资源列表
	 * @param roleId
	 * @return
	 */
	public WealthResult<List<String>> getRoleUriList(Long roleId);
	
	/**
	 * 基于用户Id查询对应的资源列表
	 * @param userId
	 * @return
	 */
	public WealthResult<List<String>> getUserUriList(Long userId);
}
