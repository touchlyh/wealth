package org.yuanhong.li.wealth.api.service;

import java.util.List;

import org.yuanhong.li.wealth.api.consts.RoleEnum;
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
	
	/**
	 * 基于用户和parent查询
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<RoleResource> getUserRoleResources(Long userId, Long parentId);
	
	/**
	 * 基于角色和parent查询
	 * @param roleId
	 * @param parentId
	 * @return
	 */
	public List<RoleResource> getRoleResources(Long roleId, Long parentId);
	
	/**
	 * 新增用户角色
	 * @param userId
	 * @param role
	 * @param duration 持续时间，会基于购买的会员时长区分
	 * @return
	 */
	public int addUserRole(Long userId,Long duration, RoleEnum role);
}
