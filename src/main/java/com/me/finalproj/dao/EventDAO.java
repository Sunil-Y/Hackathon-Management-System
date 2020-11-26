package com.me.finalproj.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.me.finalproj.pojo.Event;
/*
	Author: Sunil Yadav on 23rd March 2019
*/
public class EventDAO extends DAO {

	public List<Event> showAllEvents() throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Event");
			List<Event> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the events", e);
		} finally {
			close();
		}
	}

	public boolean addEvent(Event event) throws Exception {

		try {
			begin();
			getSession().save(event);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating event: " + e.getMessage());

		} finally {
			close();
		}

	}

	public List<Event> searchByEventName(String query) throws Exception {

		try {
			Criteria c = getSession().createCriteria(Event.class);
			c.add(Restrictions.like("name", "%" + query + "%"));
			List<Event> eList = (ArrayList<Event>) c.list();
			for (Event e : eList) {
				System.out.println("Event name " + e.getName());
			}
			return eList;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the events", e);
		} finally {
			close();
		}

	}

	public Event getEventById(String id) throws Exception {

		try {
			begin();
			Query q = getSession().createQuery("from Event where eventid = :eid");
			q.setString("eid", id);
			Event e = (Event)q.uniqueResult();
			commit();
			return e;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the events", e);
		} finally {
			close();
		}

	}
	
	public void update(Event event) throws Exception {

		try {
			begin();
			getSession().update(event);
			commit();
			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating event: " + e.getMessage());

		} finally {
			close();
		}

	}
	
	public void delete(Event event) throws Exception {

		try {
			begin();
			getSession().delete(event);
			commit();
			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating event: " + e.getMessage());

		} finally {
			close();
		}

	}

}
