package org.yuanhong.li.wealth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.yuanhong.li.wealth.controller.utils.CookieWealthUtils;
import org.yuanhong.li.wealth.facade.UserRoleFacade;
import org.yuanhong.li.wealth.facade.vo.result.WealthResult;
import org.yuanhong.li.wealth.facade.vo.user.UserProfileVO;

public class AuthorityFilter implements Filter {

	private UserRoleFacade userRoleFacade;

	private ServletContext context;
	
	public static final long VISTOR_ROLE_ID = 1L;
	
	private List<String> whiteList = new ArrayList<String>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		userRoleFacade = ctx.getBean("userRoleFacade", UserRoleFacade.class);
		whiteList.add("/api/user");
		whiteList.add("/api/system");
		whiteList.add("/health");
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
		String uri = request.getRequestURI();
		for(String whiteUri : whiteList) {
			if(uri.contains(whiteUri)) {
				chain.doFilter(req, resp);
				return;
			}
		}

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
		List<String> allowUris = null;
		if(isVisitor) {
			WealthResult<List<String>> vistorResult = userRoleFacade.getRoleUriList(VISTOR_ROLE_ID);
			allowUris = vistorResult.getData();
		} else {
			WealthResult<List<String>> vistorResult = userRoleFacade.getUserUriList(userProfileVO.getId());
			allowUris = vistorResult.getData();
		}
		//判断访问的uri是否有权限
		if(!CollectionUtils.isEmpty(allowUris) && allowUris.contains(uri)) {
			//有权限：交给下一个filter处理
			chain.doFilter(req, resp);
		}else {
			doForbidonRedirct(request, response, isVisitor);
		}
	}

	private void doForbidonRedirct(HttpServletRequest request, HttpServletResponse response, boolean isVisitor)
			throws IOException {
		//没有权限，分两部分
		if(isVisitor) {
			response.sendRedirect(request.getContextPath() + "/api/user/login");
		} else {
			//建议跳转到会员购买页
			response.sendRedirect(request.getContextPath() + "/api/system/info?code="+HttpServletResponse.SC_FORBIDDEN);
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
