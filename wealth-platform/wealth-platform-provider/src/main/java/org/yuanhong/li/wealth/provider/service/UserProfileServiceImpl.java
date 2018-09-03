package org.yuanhong.li.wealth.provider.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.yuanhong.li.wealth.api.meta.UserProfile;
import org.yuanhong.li.wealth.api.service.UserProfileService;
import org.yuanhong.li.wealth.provider.dao.UserProfileMapper;

@Component("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{
	
	@Resource
	private UserProfileMapper userProfileMapper;

	@Override
	public UserProfile addUserProfile(UserProfile userProfile) {
		if(userProfile == null) {
			return null;
		}
		userProfile.setStatus(0L);
		userProfile.setIsDeleted(0L);
		int record = userProfileMapper.insertSelective(userProfile);
		if(record == 1) {
			return this.getById(userProfile.getId());
		}
		return null;
	}

	@Override
	public UserProfile getById(Long id) {
		if(id== null) {
			return null;
		}
		return userProfileMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserProfile getByThirdId(String thirdId) {
		if(StringUtils.isEmpty(thirdId)) {
			return null;
		}
		return userProfileMapper.selectByThirdId(thirdId);
	}

	@Override
	public UserProfile getByToken(String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		return userProfileMapper.selectByToken(token);
	}

}
