package org.yuanhong.li.wealth.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SystemController {

	@RequestMapping(value = "/api/hello/{name}", method = RequestMethod.GET)
	public String htmlRender(Model model, @PathVariable("name") String name) {
		List<String> countries = new ArrayList<String>();
		countries.add("India");
		countries.add("United States");
		countries.add("Germany");
		countries.add("France");
		model.addAttribute("countries",countries);
		return "system/error";
	}
	
	@RequestMapping(value = "/api/system/info", method = RequestMethod.GET)
	public String noAuthority(Model model,HttpServletResponse httpResponse,
			@RequestParam(value = "code",required = false, defaultValue="404") String code) {
		model.addAttribute("code", code);
		httpResponse.setStatus(Integer.valueOf(code));
		return "system/system";
	}
	
}
