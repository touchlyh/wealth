package org.yuanhong.li.wealth.api.service;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.Role;
import org.yuanhong.li.wealth.api.meta.RoleResource;

public interface UserRoleService {

	/**
	 * 基于用户ID查询用户角色
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRoles(Long userId);
	
	/**
	 * 基于用户的ID查询用户的资源权限列表
	 * @param userId
	 * @return
	 */
	public List<RoleResource> getUserRoleResources(Long userId);
	
	/**
	 * 基于角色查询角色对应的资源权限列表
	 * @param roleId
	 * @return
	 */
	public List<RoleResource> getRoleResources(Long roleId);
}
