package org.yuanhong.li.wealth.facade;

import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.LoginUserVO;

public interface LoginFacade {

	/**
	 * 用户注册操作
	 * 用户名，密码，来源
	 * @param userVO
	 * @return
	 */
	public WealthResult<LoginUserVO> register(LoginUserVO userVO);
	
	/**
	 * 用户登录操作
	 * @param userVO
	 * @return
	 */
	public WealthResult<LoginUserVO> login(LoginUserVO userVO);
}
