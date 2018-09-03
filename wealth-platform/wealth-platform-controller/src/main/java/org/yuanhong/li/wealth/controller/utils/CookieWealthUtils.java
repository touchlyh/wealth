package org.yuanhong.li.wealth.controller.utils;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.yuanhong.li.wealth.api.utils.DateWealthUtils;

public class CookieWealthUtils {
	
	public static final int COOKIE_MAXAGE = 30*24*3600;

	public static final int COOKIE_AGE_YEAR = 31536000;
	
	public static final String COOKIE_USER_TOKEN = "UT";

	public static String getCookieValue(HttpServletRequest request, 
			String cookieName, String defaultValue) throws Exception{
		if (request == null) {
			return defaultValue;
		}
		Cookie cookieList[] = request.getCookies();
		if (cookieList == null || cookieName == null)
			return defaultValue;
		for (int i = 0; i < cookieList.length; i++) {
			if (cookieList[i].getName().equals(cookieName))
				return cookieList[i].getValue();
		}
		return defaultValue;
	}

	public static void rmCookie(HttpServletRequest request, HttpServletResponse response, 
			String cookieName) throws Exception {
		Cookie cookieList[] = request.getCookies();

		if (cookieList == null)
			return;
		for (int i = 0; i < cookieList.length; i++) {
			if (cookieList[i].getName().equals(cookieName)) {
				cookieList[i].setValue(null);
				cookieList[i].setMaxAge(0);
				cookieList[i].setPath("/");
				response.addCookie(cookieList[i]);
			}
		}
	}

	public static void rmCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String domain) throws Exception {
		Cookie cookieList[] = request.getCookies();

		if (cookieList == null)
			return;
		for (int i = 0; i < cookieList.length; i++) {
			if (cookieList[i].getName().equals(cookieName)) {
				cookieList[i].setValue(null);
				cookieList[i].setMaxAge(0);
				cookieList[i].setPath("/");
				if (!StringUtils.isEmpty(domain)) {
					cookieList[i].setDomain(domain);
				}
				response.addCookie(cookieList[i]);
			}
		}
	}

	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage,
			boolean httpOnly) throws Exception {
		if (cookieName != null && cookieValue != null) {
			// 判断是否是web请求
			if (httpOnly) {
				// 设置http-only
				String cookieHeader;
				StringBuilder sb = new StringBuilder(java.net.URLEncoder.encode(cookieName, "UTF-8")).append("=")
						.append(cookieValue);
				if (cookieMaxage > 0) {
					// Derived from the format used in RFC 1123
					String dateString = DateWealthUtils
							.formatRFC1123(new Date(System.currentTimeMillis() + cookieMaxage * 1000L));
					sb.append("; Expires=").append(dateString);
				}
				sb.append("; Path=/").append("; HttpOnly");
				// sb.append("; Domain=").append(getDomain());
				cookieHeader = sb.toString();
				response.addHeader("SET-COOKIE", cookieHeader);
			} else {
				Cookie theCookie = null;
				theCookie = new Cookie(java.net.URLEncoder.encode(cookieName, "UTF-8"), cookieValue);
				theCookie.setPath("/");
				if (cookieMaxage > 0) {
					theCookie.setMaxAge(cookieMaxage);
				}
				// theCookie.setDomain(getDomain());
				theCookie.toString();
				response.addCookie(theCookie);
			}
		}
	}

	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage,
			boolean httpOnly, String domain) throws Exception{
		if (cookieName != null && cookieValue != null) {
			// 判断是否是web请求
			if (httpOnly) {
				// 设置http-only
				String cookieHeader;
				StringBuilder sb = new StringBuilder(java.net.URLEncoder.encode(cookieName, "UTF-8")).append("=")
						.append(cookieValue);
				if (cookieMaxage > 0) {
					// Derived from the format used in RFC 1123
					String dateString = DateWealthUtils
							.formatRFC1123(new Date(System.currentTimeMillis() + cookieMaxage * 1000L));
					sb.append("; Expires=").append(dateString);
				}
				sb.append("; Path=/").append("; HttpOnly");
				if (!StringUtils.isEmpty(domain)) {
					sb.append("; Domain=").append(domain);
				}
				cookieHeader = sb.toString();
				response.addHeader("SET-COOKIE", cookieHeader);
			} else {
				Cookie theCookie = null;
				theCookie = new Cookie(java.net.URLEncoder.encode(cookieName, "UTF-8"), cookieValue);
				theCookie.setPath("/");
				if (cookieMaxage > 0) {
					theCookie.setMaxAge(cookieMaxage);
				}
				theCookie.toString();
				response.addCookie(theCookie);
			}
		}
	}

}
