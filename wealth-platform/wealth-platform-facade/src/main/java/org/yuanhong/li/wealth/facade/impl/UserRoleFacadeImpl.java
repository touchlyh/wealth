package org.yuanhong.li.wealth.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

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
	public WealthResult<List<String>> getRoleUriList(Long roleId) {
		List<RoleResource> roleResList = userRoleService.getRoleResources(roleId);
		if(CollectionUtils.isEmpty(roleResList)) {
			return new WealthResult<List<String>>(ResultCode.RES_NOT_EXSISTED.getCode(),"没有角色对应资源:"+roleId,null);
		}
		List<String> resources = roleResList.stream().map(rr->rr.getResource()).collect(Collectors.toList());
		return new WealthResult<List<String>>(ResultCode.SUCCESS,resources);
	}

	@Override
	public WealthResult<List<String>> getUserUriList(Long userId) {
		List<RoleResource> roleResList = userRoleService.getUserRoleResources(userId);
		if(CollectionUtils.isEmpty(roleResList)) {
			return new WealthResult<List<String>>(ResultCode.RES_NOT_EXSISTED.getCode(),"没有用户对应资源:"+userId,null);
		}
		List<String> resources = roleResList.stream().map(rr->rr.getResource()).collect(Collectors.toList());
		return new WealthResult<List<String>>(ResultCode.SUCCESS,resources);
	}

}
