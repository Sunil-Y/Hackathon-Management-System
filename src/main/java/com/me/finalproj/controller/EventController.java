package com.me.finalproj.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.me.finalproj.dao.EventDAO;
import com.me.finalproj.dao.HackerDAO;
import com.me.finalproj.pojo.Event;
import com.me.finalproj.pojo.Hacker;
/*
	Author: Sunil Yadav on 23rd March 2019
*/
@Controller
@RequestMapping("/event/*")
public class EventController {
	
	public EventController() {
		
	}

	@Autowired
	@Qualifier("eventDao")
	EventDAO eDAO;

	@Autowired
	@Qualifier("hackerDao")
	HackerDAO vDAO;

	@RequestMapping(value = "/event/eventCatalog.htm")
	public ModelAndView eventCatalog(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String adminSession = (String) session.getAttribute("admin");
		// EventDAO eDao = new EventDAO();
		List<Event> eventList = eDAO.showAllEvents();
		ModelAndView mv = new ModelAndView();
		mv.addObject("allEvents", eventList);
		mv.addObject("adminSession", adminSession);
		mv.setViewName("adminmain");
		return mv;
	}

	@RequestMapping(value = "/event/newEvent.htm")
	public ModelAndView newEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("admin", "yes");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addEvent");
		return mv;
	}

	@RequestMapping(value = "/event/addEvent.htm")
	public ModelAndView addEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String eventName = request.getParameter("eventName");
		String desc = request.getParameter("desc");
		String place = request.getParameter("place");
		String duration = request.getParameter("duration");
		String eventType = request.getParameter("eventType");
		String ticket = request.getParameter("ticket");
		String isAdd = request.getParameter("isAdd");

		Event event = new Event();
		event.setName(eventName);
		event.setDescription(desc);
		event.setPlace(place);
		event.setDuration(duration);
		event.setEventtype(eventType);
		event.setSeatsavailable(ticket);

		boolean flag = eDAO.addEvent(event);
		ModelAndView mv = new ModelAndView();
		if (flag == true) {
			mv.addObject("REGISTRATIONSTATUSMESSAGE", "Event Added Successfully");

		} else {
			mv.addObject("REGISTRATIONSTATUSMESSAGE", "here was error while saving the event");
		}
		mv.addObject("isAdd", isAdd);
		mv.setViewName("addEvent");
		return mv;
	}

	@RequestMapping(value = "/event/searchEventByNameCatalog.htm")
	public ModelAndView searchEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Inside searchby event name");
		String eventName = request.getParameter("eventname");
		HttpSession session = (HttpSession) request.getSession();

		String adminSession = (String) session.getAttribute("admin");
		System.out.println("Sessionnnn   " + adminSession);
		
		List<Event> events = eDAO.searchByEventName(eventName);
		ModelAndView mv = new ModelAndView();
		mv.addObject("allEvents", events);
		mv.setViewName("adminmain");

		return mv;
	}

	@RequestMapping(value = "/event/eventreg.htm")
	public ModelAndView registerEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String eventId = request.getParameter("eid");
		String hackerId = request.getParameter("vid");
		System.out.println("Event Id is " + eventId);
		System.out.println("hacker Id is " + hackerId);
		
		Event e = eDAO.getEventById(eventId);
		
		Hacker v = vDAO.getHackerById(hackerId);

		Set<Event> regEventList = new HashSet<Event>();
		regEventList = v.getRegisteredEvents();
		int seat = Integer.parseInt(e.getSeatsavailable());
		e.setSeatsavailable(String.valueOf((seat - 1)));
		eDAO.update(e);
		regEventList.add(e);
		v.setRegisteredEvents(regEventList);

		boolean f = vDAO.registerHackerToEvent(v);
		ModelAndView mv = new ModelAndView();
		if (f == true) {
			mv.addObject("RegError", "Event registered Successfully!");
		} else {
			mv.addObject("RegError", "Some error occured while registering for the event");
		}
		mv.addObject("hacker", v);
		
		List<Event> eventList = new ArrayList<Event>();
		eventList = eDAO.showAllEvents();
		mv.addObject("allEvents", eventList);
		mv.setViewName("hackermain");

		return mv;
	}

	@RequestMapping(value = "/event/eventunreg.htm")
	public ModelAndView unregisterEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		String eventId = request.getParameter("eid");
		String hackerId = request.getParameter("vid");
		Hacker v = vDAO.getHackerById(hackerId);
		Event e = eDAO.getEventById(eventId);
		int seat = Integer.parseInt(e.getSeatsavailable());
		e.setSeatsavailable(String.valueOf((seat + 1)));
		eDAO.update(e);
		Set<Event> test = v.getRegisteredEvents();

		Iterator<Event> itr = test.iterator();
		while (itr.hasNext()) {
			Event ev = itr.next();
			if (ev.getEventid() == Integer.parseInt(eventId)) {
				itr.remove();
			}
		}

		Hacker f = vDAO.unregisterEvent(v);
		ModelAndView mv = new ModelAndView();
		if (f != null) {
			mv.addObject("RegError", "Event unregistered Successfully!");
		} else {
			mv.addObject("RegError", "Some error occured while unregistering for the event");
		}
		mv.addObject("hacker", f);
		mv.addObject("regEvents", f.getRegisteredEvents());
		List<Event> eventList = new ArrayList<Event>();
		eventList = eDAO.showAllEvents();
		mv.addObject("allEvents", eventList);
		mv.setViewName("hackermain");

		return mv;
	}

	
	@RequestMapping(value = "/event/updateEvent.htm")
	public ModelAndView updateEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String eventId = request.getParameter("eventId");
		Event evt=eDAO.getEventById(eventId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("event",evt);
		mv.setViewName("updateEvent");
		return mv;
	}
	
	@RequestMapping(value = "/event/saveupdatedEvent.htm")
	public ModelAndView saveupdateEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String eventId = request.getParameter("id");
		Event evt=eDAO.getEventById(eventId);
		String eventName = request.getParameter("eventName");
		String desc = request.getParameter("desc");
		String place = request.getParameter("place");
		String duration = request.getParameter("duration");
		String eventType = request.getParameter("eventType");
		String ticket = request.getParameter("ticket");
		evt.setName(eventName);
		evt.setDescription(desc);
		evt.setPlace(place);
		evt.setDuration(duration);
		evt.setEventtype(eventType);
		evt.setSeatsavailable(ticket);
		
		eDAO.update(evt);
		ModelAndView mv = new ModelAndView();
		mv.addObject("event",evt);
		mv.addObject("REGISTRATIONSTATUSMESSAGE","Event Updated Successfully!!");
		mv.setViewName("updateEvent");
		return mv;
	}
	
	
	@RequestMapping(value = "/event/deleteEvent.htm")
	public ModelAndView deleteEvent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String eventId = request.getParameter("eventId");
		Event evt=eDAO.getEventById(eventId);
		eDAO.delete(evt);
		ModelAndView mv = new ModelAndView();
		mv.addObject("RegError","Event deleted  Successfully!!");
		List<Event> eventList = eDAO.showAllEvents();
		mv.addObject("allEvents", eventList);
		mv.setViewName("adminmain");
		return mv;
	}
	
	@RequestMapping(value = "/event/ajax.htm")
	public ModelAndView ajax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return new ModelAndView("eventCatalog");
	}
}
