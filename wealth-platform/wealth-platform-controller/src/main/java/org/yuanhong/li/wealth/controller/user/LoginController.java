package org.yuanhong.li.wealth.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yuanhong.li.wealth.facade.LoginFacade;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.LoginUserVO;

@Controller
public class LoginController {
	
	private static String HOME_PAGE_URI = "/api/hello/yuanhong.li";
	
	@Resource
	private LoginFacade loginFacade;

	@RequestMapping(value = "/api/user/login", method = RequestMethod.GET)
	public String getRegisterPage(Model model) {
		return "/user/login";
	}
	
	@RequestMapping(value = "/api/user/register.do", method = RequestMethod.POST)
	public String doRegister(Model model, HttpServletResponse httpResponse,
            @RequestParam(value = "thirdId",required = true) String thirdId, 
            @RequestParam(value = "password",required = true) String password,
            @RequestParam(value = "source",required = false, defaultValue="phone") String source,
            @RequestParam(value = "next",required = false)String next) {
		LoginUserVO userVO = new LoginUserVO();
		userVO.setThirdId(thirdId);
		userVO.setPasswd(password);
		userVO.setSource(source);
		WealthResult<LoginUserVO> result = loginFacade.register(userVO);
		if(result.isSuccess()) {
			return !StringUtils.isEmpty(next) ? "redirect:"+next : "redirect:"+HOME_PAGE_URI;
		} else {
			model.addAttribute("msg", result.getMsg());
			model.addAttribute("code", result.getCode());
			return "/user/login";
		}
	}
	
	@RequestMapping(value = "/api/user/login.do", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletResponse httpResponse,
            @RequestParam String thirdId, @RequestParam String password,
            @RequestParam(value = "next",required = false)String next) {
		LoginUserVO userVO = new LoginUserVO();
		userVO.setThirdId(thirdId);
		userVO.setPasswd(password);
		WealthResult<LoginUserVO> result = loginFacade.login(userVO);
		if(result.isSuccess()) {
			return !StringUtils.isEmpty(next) ? "redirect:"+next : "redirect:"+HOME_PAGE_URI;
		} else {
			model.addAttribute("msg", result.getMsg());
			model.addAttribute("code", result.getCode());
			model.addAttribute("thirdId", thirdId);
			return "/user/login";
		}
	}
}
