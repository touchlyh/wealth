package org.yuanhong.li.wealth.api.service;

import org.yuanhong.li.wealth.api.meta.UserProfile;

public interface UserProfileService {

	/**
	 * 新增用户信息
	 * @param userProfile
	 * @return
	 */
	public UserProfile addUserProfile(UserProfile userProfile);
	
	/**
	 * 基于ID查询用户信息
	 * @param id
	 * @return
	 */
	public UserProfile getById(Long id);
	
	/**
	 * 基于用户thirdId查询信息
	 * @param thirdId
	 * @return
	 */
	public UserProfile getByThirdId(String thirdId);
	
	/**
	 * 基于用户的token查询信息
	 * @param token
	 * @return
	 */
	public UserProfile getByToken(String token);
}
