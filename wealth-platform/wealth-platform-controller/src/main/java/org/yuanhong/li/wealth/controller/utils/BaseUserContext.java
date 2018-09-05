package org.yuanhong.li.wealth.controller.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息环境类，以线程为单位保存信息 用完记得调用clear方法，防止同线程复用
 * 
 * @author caocai
 * 
 */
public class BaseUserContext {
	private static ThreadLocal<String> visitorUserName = new ThreadLocal<String>();
	private static ThreadLocal<String> anoniVisitorUserName = new ThreadLocal<String>();
	private static ThreadLocal<String> currentVistIp = new ThreadLocal<String>();
	private static ThreadLocal<String> managerName = new ThreadLocal<String>();
	private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	
	private static ThreadLocal<String> beautyToken = new ThreadLocal<String>();
	private static ThreadLocal<Long> user = new ThreadLocal<Long>();
	private static ThreadLocal<List<Long>> userRole = new ThreadLocal<List<Long>>();
	private static ThreadLocal<String> omsManagerName = new ThreadLocal<String>();
	private static ThreadLocal<String> cmsManagerName = new ThreadLocal<String>();

	private static ThreadLocal<String> biCookie = new ThreadLocal<String>();
	
	private static ThreadLocal<Long> clientVersion = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return 0L;
		}
	};
	private static ThreadLocal<Integer> tokenVersion = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		}
	};

	private static ThreadLocal<String> userAgent = new ThreadLocal<String>() {
		protected String initialValue() {
			return "";
		}
	};
	private static ThreadLocal<String> proxyIp = new ThreadLocal<String>() {
		protected String initialValue() {
			return "";
		}
	};// 用于拿联通代理IP
	private static ThreadLocal<Map<String, Object>> headerMap = new ThreadLocal<Map<String, Object>>() {
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};// Request Header Map

	// 加密的请求解密后的
	private static ThreadLocal<HttpServletRequest> decryptedRequest = new ThreadLocal<HttpServletRequest>();

	public static void clear() {
		visitorUserName.remove();
		currentVistIp.remove();
		managerName.remove();
		userAgent.remove();
		anoniVisitorUserName.remove();
		proxyIp.remove();
		headerMap.remove();
		tokenVersion.remove();
		decryptedRequest.remove();
		clientVersion.remove();
		request.remove();
		response.remove();
		beautyToken.remove();
		user.remove();
		userRole.remove();
		omsManagerName.remove();
		biCookie.remove();
	}

	public static HttpServletResponse getResponse() {
		return response.get();
	}

	public static void setResponse(HttpServletResponse httpServletResponse) {
		response.set(httpServletResponse);
	}

	public static Long getClientVersion() {
		return clientVersion.get();
	}

	public static void setClientVersion(Long cv) {
		clientVersion.set(cv);
	}

	public static int getTokenVersion() {
		return tokenVersion.get();
	}

	public static HttpServletRequest getDecryptedRequest() {
		return decryptedRequest.get();
	}

	public static void setDecryptedRequest(HttpServletRequest request) {
		decryptedRequest.set(request);
	}

	public static boolean isRequestEncrypted() {
		return decryptedRequest.get() != null;
	}

	public static void setTokenVersion(int tokenVer) {
		tokenVersion.set(tokenVer);
	}

	public static void setAnoniVisitorName(String anoniUserName) {
		anoniVisitorUserName.set(anoniUserName);
	}

	public static String getAnoniVisitorName() {
		return anoniVisitorUserName.get();
	}

	public static void setManagerName(String s) {
		managerName.set(s);
	}

	public static String getManagerName() {
		return managerName.get();
	}

	public static void setRequest(HttpServletRequest req) {
		request.set(req);
	}

	public static HttpServletRequest getRequest() {
		return request.get();
	}

	public static void setVisitIp(String ip) {
		currentVistIp.set(ip);
	}

	public static String getVisitIp() {
		return currentVistIp.get();
	}

	public static String getVisitorUserName() {
		return visitorUserName.get();
	}

	public static void setVisitorUserName(String name) {
		visitorUserName.set(name);
	}

	public static String getUserAgent() {
		return userAgent.get() == null ? "" : userAgent.get();
	}

	public static void setUserAgent(String userAgent) {
		BaseUserContext.userAgent.set(userAgent);
	}

	public static void setProxyIp(String ip) {
		proxyIp.set(ip);
	}

	public static String getProxyIp() {
		return proxyIp.get();
	}

	public static void setHeaderMap(Map<String, Object> headerMap) {
		BaseUserContext.headerMap.set(headerMap);
	}

	public static Map<String, Object> getHeaderMap() {
		return headerMap.get();
	}
	
	public static String getLoginToken() {
		return beautyToken.get();
	}

	public static void setLoginToken(String token) {
		beautyToken.set(token);
	}
	
	public static Long getUserId() {
		return user.get();
	}

	public static void setUserId(Long userId) {
		user.set(userId);
	}
	
	public static List<Long> getUserRoleIds() {
		return userRole.get();
	}

	public static void setUserRoleIds(List<Long> userRoleIds) {
		userRole.set(userRoleIds);
	}
	
	public static void setOmsManagerName(String s) {
		omsManagerName.set(s);
	}

	public static String getOmsManagerName() {
		return omsManagerName.get();
	}

	public static String getCmsManagerName() {
		return cmsManagerName.get();
	}

	public static void setCmsManagerName(String s) {
		cmsManagerName.set(s);
	}

	public static void setBiCookie(String s) {
		biCookie.set(s);
	}
	
	public static String getBiCookie() {
		return biCookie.get();
	}
}
