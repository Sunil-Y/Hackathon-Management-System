package com.me.finalproj.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.finalproj.dao.EventDAO;
import com.me.finalproj.pojo.Event;
/*
	Author: Sunil Yadav on 23rd March 2019
*/
@Controller
public class AjaxController {

	@Autowired
	@Qualifier("eventDao")
	EventDAO eDAO;

	@RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxService(HttpServletRequest request) throws Exception {

		List<Event> eventList = new ArrayList<Event>();
		eventList = eDAO.showAllEvents();

		String queryString = request.getParameter("course");
		String result = "";
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getName().toLowerCase().contains(queryString.toLowerCase())) {
				result += eventList.get(i).getName() + ",";
			}
		}

		return result;
	}
}
