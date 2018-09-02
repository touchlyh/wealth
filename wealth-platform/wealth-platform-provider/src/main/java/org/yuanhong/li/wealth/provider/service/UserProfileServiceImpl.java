package org.yuanhong.li.wealth.provider.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.yuanhong.li.wealth.api.meta.UserProfile;
import org.yuanhong.li.wealth.api.service.UserProfileService;
import org.yuanhong.li.wealth.provider.dao.UserProfileMapper;

@Component("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{
	
	@Resource
	private UserProfileMapper userProfileMapper;

	@Override
	public UserProfile addUserProfile(UserProfile userProfile) {
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
		return userProfileMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserProfile getByThirdId(String thirdId) {
		return userProfileMapper.selectByThirdId(thirdId);
	}

}
