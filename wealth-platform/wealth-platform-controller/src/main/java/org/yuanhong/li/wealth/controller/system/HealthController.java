package org.yuanhong.li.wealth.controller.system;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuanhong.li.wealth.controller.utils.HttpWealthUtils;

@Controller
@RequestMapping("/health")
public class HealthController implements InitializingBean {
	
	private static final String[] IP_WHITE_LIST = {"172.17.1.18", "127.0.0.1"};
	
	private Set<String> ipSet;

	private boolean available = true;
	
	@RequestMapping("/status")
	@ResponseBody
	public String getStatus(HttpServletRequest req,HttpServletResponse resp) {
		if (available) {
			resp.setStatus(HttpServletResponse.SC_OK);
			return "ok";
		} else {
			resp.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			return "service unavailable";
		}
	}
	
	@RequestMapping("/offline")
	@ResponseBody
	public String offline(HttpServletRequest req,HttpServletResponse resp) {
		String ip = HttpWealthUtils.getIpAddress(req);
		if (!ipSet.contains(ip)) {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return "forbidden";
		}
		
		available = false;
		return "offline done";
	}
	
	@RequestMapping("/active")
	@ResponseBody
	public String active(HttpServletRequest req,HttpServletResponse resp) {
		String ip = HttpWealthUtils.getIpAddress(req);
		if (!ipSet.contains(ip)) {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return "forbidden";
		}
		
		available = true;
		return "active done";
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		ipSet = new HashSet<>(Arrays.asList(IP_WHITE_LIST));
	}
}
