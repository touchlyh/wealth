package org.yuanhong.li.wealth.facade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yuanhong.li.wealth.api.consts.ResultCode;
import org.yuanhong.li.wealth.api.meta.RoleResource;
import org.yuanhong.li.wealth.api.meta.UserProfile;
import org.yuanhong.li.wealth.api.service.UserProfileService;
import org.yuanhong.li.wealth.api.service.UserRoleService;
import org.yuanhong.li.wealth.facade.UserRoleFacade;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.UserProfileVO;

@Component("userRoleFacade")
public class UserRoleFacadeImpl implements UserRoleFacade{

	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private UserProfileService userProfileService;
	
	@Override
	public WealthResult<UserProfileVO> getUserByToken(String token) {
		UserProfile user = userProfileService.getByToken(token);
		if(user == null) {
			return new WealthResult<UserProfileVO>(ResultCode.RES_NOT_EXSISTED.getCode(),"用户不存在",null);
		}
		UserProfileVO userVO = new UserProfileVO();
		BeanUtils.copyProperties(user, userVO);
		return new WealthResult<UserProfileVO>(ResultCode.SUCCESS,userVO);
	}

	@Override
	public WealthResult<List<RoleResource>> getRoleResourceList(Long roleId) {
		List<RoleResource> roleResList = userRoleService.getRoleResources(roleId);
		if(CollectionUtils.isEmpty(roleResList)) {
			return new WealthResult<List<RoleResource>>(ResultCode.RES_NOT_EXSISTED.getCode(),"没有角色对应资源:"+roleId,null);
		}
		return new WealthResult<List<RoleResource>>(ResultCode.SUCCESS,roleResList);
	}

	@Override
	public WealthResult<List<RoleResource>> getUserResourceList(Long userId) {
		List<RoleResource> roleResList = userRoleService.getUserRoleResources(userId);
		if(CollectionUtils.isEmpty(roleResList)) {
			return new WealthResult<List<RoleResource>>(ResultCode.RES_NOT_EXSISTED.getCode(),"没有用户对应资源:"+userId,null);
		}
		return new WealthResult<List<RoleResource>>(ResultCode.SUCCESS,roleResList);
	}

	@Override
	public List<RoleResource> getRoleResourceList(Long roleId, Long parentId) {
		return userRoleService.getRoleResources(roleId, parentId);
	}

	@Override
	public List<RoleResource> getUserResourceList(Long userId, Long parentId) {
		return userRoleService.getUserRoleResources(userId, parentId);
	}

}
