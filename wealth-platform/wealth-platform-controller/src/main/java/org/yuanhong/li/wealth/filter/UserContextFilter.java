package org.yuanhong.li.wealth.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.yuanhong.li.wealth.controller.utils.BaseUserContext;
import org.yuanhong.li.wealth.controller.utils.CookieWealthUtils;
import org.yuanhong.li.wealth.facade.UserRoleFacade;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.UserProfileVO;

public class UserContextFilter implements Filter{
	
	private UserRoleFacade userRoleFacade;

	private ServletContext context;

	@Override
	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		userRoleFacade = ctx.getBean("userRoleFacade", UserRoleFacade.class);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (resp instanceof HttpServletResponse) {
			HttpServletResponse realResp = (HttpServletResponse) resp;
			realResp.setHeader("Cache-Control", "no-store");
			realResp.setHeader("Pragrma", "no-cache");
			realResp.setDateHeader("Expires", 0);
		}
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding("utf-8");
		HttpServletResponse response = (HttpServletResponse) resp;
		response.addHeader("Cache-Control", "no-cache");
		try {
			doAuthority(req, resp, chain, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/api/system/info?code="+HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	private void doAuthority(ServletRequest req, ServletResponse resp, FilterChain chain, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException, ServletException {

		// 进行url参数处理
		StringBuilder url = new StringBuilder(request.getRequestURI());
		int i = 0;
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			if (++i == 1) {
				url.append("?");
			} else {
				url.append("&");
			}
			String paramName = parameterNames.nextElement();
			url.append(paramName);
			url.append("=");
			url.append(request.getParameter(paramName));
		}
		
		//获取cookie中的token
		String token = CookieWealthUtils.getCookieValue(request, CookieWealthUtils.COOKIE_USER_TOKEN, "");
		UserProfileVO userProfileVO = null;
		if(!StringUtils.isEmpty(token)) {
			WealthResult<UserProfileVO> userResult = userRoleFacade.getUserByToken(token);
			userProfileVO = userResult.getData();
		}
		//token为空，或者token查不到对应的用户，则认为是游客
		boolean isVisitor = (userProfileVO == null);
		if(isVisitor) {
			chain.doFilter(req, resp);
		} else {
			BaseUserContext.setUserId(userProfileVO.getId());
			BaseUserContext.setLoginToken(token);
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
	}

	public UserRoleFacade getUserRoleFacade() {
		return userRoleFacade;
	}

	public void setUserRoleFacade(UserRoleFacade userRoleFacade) {
		this.userRoleFacade = userRoleFacade;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

}
