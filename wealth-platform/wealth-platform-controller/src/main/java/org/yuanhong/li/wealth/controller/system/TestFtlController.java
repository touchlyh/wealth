package org.yuanhong.li.wealth.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestFtlController {

	@RequestMapping(value = "/api/hello/{name}", method = RequestMethod.GET)
	public String htmlRender(Model model, @PathVariable("name") String name) {
		List<String> countries = new ArrayList<String>();
		countries.add("India");
		countries.add("United States");
		countries.add("Germany");
		countries.add("France");
		model.addAttribute("countries",countries);
		return "test/test";
	}
}
