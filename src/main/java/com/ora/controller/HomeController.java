package com.ora.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ora.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Inject
	TestService testService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		List<HashMap> testList = testService.list();
		
		StringBuilder str = new StringBuilder();
		str.append("<table>");
		for (int i = 0; i < testList.size(); i++) {
			str.append("<tr>");
			str.append("<td>"+testList.get(i).get("USERID")+"</td>");
			str.append("<td>"+testList.get(i).get("TITLE")+"</td>");
			str.append("<td>"+testList.get(i).get("CONTENT")+"</td>");
			str.append("<td>"+testList.get(i).get("READCOUNT")+"</td>");
			str.append("</tr>");
		}
		str.append("</table>");
		
		model.addAttribute("drawTable", str);
		model.addAttribute("serverTime", formattedDate );
		
		
		return "home";
	}
	
}
