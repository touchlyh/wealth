package org.yuanhong.li.wealth.provider.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yuanhong.li.wealth.api.meta.Role;
import org.yuanhong.li.wealth.api.meta.RoleResource;
import org.yuanhong.li.wealth.api.meta.UserRole;
import org.yuanhong.li.wealth.api.service.UserRoleService;
import org.yuanhong.li.wealth.provider.dao.RoleMapper;
import org.yuanhong.li.wealth.provider.dao.RoleResourceMapper;
import org.yuanhong.li.wealth.provider.dao.UserRoleMapper;

@Component("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Resource
	private RoleResourceMapper roleResourceMapper;

	@Override
	public List<Role> getUserRoles(Long userId) {
		List<UserRole> userRoleList = userRoleMapper.selectByUserId(userId);
		if(CollectionUtils.isEmpty(userRoleList)) {
			return null;
		}
		List<Long> roleIds = userRoleList.stream().map(ur->ur.getRoleId()).collect(Collectors.toList());
		
		return roleMapper.selectByIdList(roleIds);
	}

	@Override
	public List<RoleResource> getUserRoleResources(Long userId) {
		List<UserRole> userRoleList = userRoleMapper.selectByUserId(userId);
		if(CollectionUtils.isEmpty(userRoleList)) {
			return null;
		}
		List<Long> roleIds = userRoleList.stream().map(ur->ur.getRoleId()).collect(Collectors.toList());
		
		return roleResourceMapper.selectByRoleIdList(roleIds);
	}

	@Override
	public List<RoleResource> getRoleResources(Long roleId) {
		return roleResourceMapper.selectByRoleId(roleId);
	}

	@Override
	public List<RoleResource> getUserRoleResources(Long userId, Long parentId) {
		List<UserRole> userRoleList = userRoleMapper.selectByUserId(userId);
		if(CollectionUtils.isEmpty(userRoleList)) {
			return null;
		}
		List<Long> roleIds = userRoleList.stream().map(ur->ur.getRoleId()).collect(Collectors.toList());
		
		return roleResourceMapper.selectByRoleIdList(roleIds, parentId);
	}

	@Override
	public List<RoleResource> getRoleResources(Long roleId, Long parentId) {
		return roleResourceMapper.selectByRoleId(roleId, parentId);
	}

}
