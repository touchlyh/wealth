package org.yuanhong.li.wealth.facade;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.RoleResource;
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
	public WealthResult<List<RoleResource>> getRoleResourceList(Long roleId);
	
	/**
	 * 基于用户Id查询对应的资源列表
	 * @param userId
	 * @return
	 */
	public WealthResult<List<RoleResource>> getUserResourceList(Long userId);
	
	/**
	 * 查询某个parent下的角色对应的资源列表
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<RoleResource> getRoleResourceList(Long roleId, Long parentId);
	
	/**
	 * 查询某个parent下用户对应的资源列表
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<RoleResource> getUserResourceList(Long roleId, Long parentId);
}
