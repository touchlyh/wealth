package org.yuanhong.li.wealth.facade.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.yuanhong.li.wealth.api.consts.ResultCode;
import org.yuanhong.li.wealth.api.consts.UserSource;
import org.yuanhong.li.wealth.api.meta.UserProfile;
import org.yuanhong.li.wealth.api.service.UserProfileService;
import org.yuanhong.li.wealth.api.utils.CheckSumBuilder;
import org.yuanhong.li.wealth.facade.LoginFacade;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.LoginUserVO;

@Component("loginFacade")
public class LoginFacadeImpl implements LoginFacade{
	
	@Resource
	private UserProfileService userProfileService;

	@Override
	public WealthResult<LoginUserVO> register(LoginUserVO userVO) {
		if(StringUtils.isEmpty(userVO.getThirdId()) || userVO.getThirdId().trim().length()==0) {
			return new WealthResult<LoginUserVO>(ResultCode.PARAM_ERROR.getCode(),"用户名为空", userVO);
		}
		if(StringUtils.isEmpty(userVO.getPasswd()) || userVO.getPasswd().trim().length() ==0) {
			return new WealthResult<LoginUserVO>(ResultCode.PARAM_ERROR.getCode(), "密码为空",userVO);
		}
		if(userVO.getThirdId().contains(" ") || userVO.getPasswd().contains(" ")) {
			return new WealthResult<LoginUserVO>(ResultCode.PARAM_ERROR.getCode(), "用户名密码不允许有空格",userVO);
		}
		String thirdId = userVO.getThirdId().trim();
		String passwd = userVO.getPasswd().trim();
		if(getByThirdId(thirdId) != null) {
			return new WealthResult<LoginUserVO>(ResultCode.RES_EXSISTED.getCode(), "该用户已存在",userVO);
		}
		UserProfile user = new UserProfile();
		user.setThirdId(thirdId);
		user.setSource(UserSource.WEALTH.name());
		user.setLastLoginTime(System.currentTimeMillis());
		user.setSalt(UUID.randomUUID().toString().substring(0, 5));
		StringBuilder sbPasswd = new StringBuilder(user.getSalt()).append(passwd);
		user.setPasswd(CheckSumBuilder.getMD5(sbPasswd.toString()));
		StringBuilder sbToken = new StringBuilder(user.getSalt()).append(thirdId);
		user.setToken(CheckSumBuilder.getMD5(sbToken.toString()));
		userVO = this.addUser(user);
		return new WealthResult<LoginUserVO>(ResultCode.SUCCESS, userVO);
	}
	
	@Override
	public WealthResult<LoginUserVO> login(LoginUserVO userVO) {
		if(StringUtils.isEmpty(userVO.getThirdId()) || userVO.getThirdId().trim().length()==0) {
			return new WealthResult<LoginUserVO>(ResultCode.PARAM_ERROR.getCode(),"用户名为空", userVO);
		}
		if(StringUtils.isEmpty(userVO.getPasswd()) || userVO.getPasswd().trim().length() ==0) {
			return new WealthResult<LoginUserVO>(ResultCode.PARAM_ERROR.getCode(), "密码为空",userVO);
		}
		if(userVO.getThirdId().contains(" ") || userVO.getPasswd().contains(" ")) {
			return new WealthResult<LoginUserVO>(ResultCode.PARAM_ERROR.getCode(), "用户名密码不允许有空格",userVO);
		}
		String thirdId = userVO.getThirdId().trim();
		String passwd = userVO.getPasswd().trim();
		LoginUserVO userInDB = getByThirdId(thirdId);
		if(userInDB == null) {
			return new WealthResult<LoginUserVO>(ResultCode.RES_NOT_EXSISTED.getCode(), "该用户不存在",userVO);
		}
		StringBuilder sbPasswd = new StringBuilder(userInDB.getSalt()).append(passwd);
		if(!CheckSumBuilder.getMD5(sbPasswd.toString()).equals(userInDB.getPasswd())) {
			return new WealthResult<LoginUserVO>(ResultCode.RES_NOT_EXSISTED.getCode(), "密码输入错误",userVO);
		}
		return new WealthResult<LoginUserVO>(ResultCode.SUCCESS, userInDB);
	}

	private LoginUserVO addUser(UserProfile user) {
		UserProfile userProfile = userProfileService.addUserProfile(user);
		return userProfileToLoginUserVO(userProfile);
	}

	private LoginUserVO userProfileToLoginUserVO(UserProfile userProfile) {
		if(userProfile == null) {
			return null;
		}
		LoginUserVO loginUserVO = new LoginUserVO();
		loginUserVO.setId(userProfile.getId());
		loginUserVO.setSource(userProfile.getSource());
		loginUserVO.setThirdId(userProfile.getThirdId());
		loginUserVO.setToken(userProfile.getToken());
		loginUserVO.setSalt(userProfile.getSalt());
		loginUserVO.setPasswd(userProfile.getPasswd());
		return loginUserVO;
	}

	private LoginUserVO getByThirdId(String thirdId) {
		UserProfile userProfile = userProfileService.getByThirdId(thirdId);
		return userProfileToLoginUserVO(userProfile);
	}

}
