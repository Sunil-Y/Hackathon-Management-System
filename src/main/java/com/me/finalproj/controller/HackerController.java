package com.me.finalproj.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.me.finalproj.dao.EventDAO;
import com.me.finalproj.dao.hackerDAO;
import com.me.finalproj.exception.FERSGenericException;
import com.me.finalproj.pojo.Email;
import com.me.finalproj.pojo.Event;
import com.me.finalproj.pojo.hacker;
/*
	Author: Sunil Yadav on 23rd March 2019
*/
@Controller
@RequestMapping("/hacker/*")
public class hackerController {

	@Autowired
	@Qualifier("hackerDao")
	hackerDAO vDAO;

	@Autowired
	@Qualifier("eventDao")
	EventDAO eDAO;

	private static Logger log = Logger.getLogger(hackerController.class);

	@RequestMapping(value = "/hacker/register.htm")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("registration");
	}

	@RequestMapping("/hacker/newHacker.htm")
	public ModelAndView newhacker(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request == null || response == null) {
			log.info("Request or Response failed.");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}
		String username = request.getParameter("USERNAME");
		String password = request.getParameter("PASSWORD");
		String firstname = request.getParameter("FIRSTNAME");
		String lastname = request.getParameter("LASTNAME");
		String email = request.getParameter("EMAIL");
		String phoneno = request.getParameter("PHONENO");
		String place = request.getParameter("ADDRESS");

		log.info("creating new Hacker with UserName :" + username);

		hacker hacker = new hacker();
		Email em = new Email();
		em.setEmailAddress(email);
		if ((username.equals("admin")) && (password.equals("admin"))) {
			hacker.setAdmin(true);
		} else {
			hacker.setAdmin(false);
		}

		hacker.setUserName(username);
		hacker.setPassword(password);
		hacker.setFirstName(firstname);
		hacker.setLastName(lastname);
		hacker.setAddress(place);
		hacker.setPhoneNumber(phoneno);
		hacker.setEmail(em);
		em.setHack(hacker);

		boolean insertStatus = vDAO.inserthacker(hacker);

		ModelAndView mv = new ModelAndView();
		if (insertStatus == true) {
			mv.addObject("REGISTRATIONSTATUSMESSAGE", "User Registered Succesfully !!!");
			log.info("Succesfully created hacker " + username);
			mv.setViewName("registration");
		} else {
			mv.addObject("REGISTRATIONSTATUSMESSAGE",
					"USERNAME already exists.. please register again with different USERNAME..");
			log.info("Username " + username + " already exists and Hacker creation failed..");
			mv.setViewName("registration");
		}
		return mv;
	}

	@RequestMapping("/hacker/searchhacker.htm")
	public ModelAndView searchhacker(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request == null || response == null) {
			log.info("Request or Response failed for SEARCHhacker METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}
		HttpSession session = (HttpSession) request.getSession();
		String username = request.getParameter("USERNAME");
		String password = request.getParameter("PASSWORD");

		session.setAttribute("USERNAME", username);
		session.setAttribute("PASSWORD", password);

		log.info("Logging into FERS using username :" + username + " and password :" + password);

		hacker hacker = new hacker();
		hacker = vDAO.searchhacker(username, password);

		ModelAndView mv = new ModelAndView();

		List<Event> eventList = new ArrayList<Event>();
		eventList = eDAO.showAllEvents();
		if (hacker == null) {
			mv.addObject("ERROR", "Invalid Username / Password.");
			mv.setViewName("index");
			return mv;
		} else if (hacker.isAdmin()) {
			mv.addObject("hacker", hacker);
			mv.addObject("allEvents", eventList);
			mv.setViewName("adminmain");

			return mv;
		} else {

			log.info("hacker details available for the username :" + username);
			System.out.println("User Authenticated succeessfuuhiui");
			log.info("All events listed for the hacker :" + eventList);

			session.setAttribute("Sessionhacker", hacker);
			mv.addObject("hacker", hacker);
			mv.addObject("allEvents", eventList);

			mv.setViewName("hackermain");
			return mv;
		}
	}

	@RequestMapping(value = "/hacker/logout.htm")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession hs = request.getSession();
		hs.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/hacker/generatePdf.htm")
	public ModelAndView changePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("vid");
		hacker v = vDAO.gethackerById(id);

		Set<Event> eList = v.getRegisteredEvents();
		Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\kiran\\Desktop\\event.pdf"));
        
        document.open();
        document.newPage();
        document.add(new Paragraph("hacker Name: " + v.getFirstName() + " " + v.getLastName())); 
		document.add(new Paragraph("Phone Number: " + v.getPhoneNumber()));
		document.add(new Paragraph("Email Address: " + v.getEmail().getEmailAddress()));
		document.add(new Paragraph("Registered Events are: " ));
		document.add(new Paragraph());
		document.add(new Paragraph("Event Name  "  + " Event Type  "  + " Event Place " ));
		for(Event e: eList)
			document.add(new Paragraph(e.getName() + "		  " + e.getEventtype() + " 		 " + e.getPlace()));
        document.close();
        ModelAndView mv = new ModelAndView();
        mv.addObject("hacker", v);
		mv.addObject("allEvents", eList);
		mv.addObject("Error", "PDF Generated Successfully!");
		mv.setViewName("hackermain");
        return mv;

	}

	@RequestMapping(value = "/hacker/index.htm")
	public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("index");
	}
}
