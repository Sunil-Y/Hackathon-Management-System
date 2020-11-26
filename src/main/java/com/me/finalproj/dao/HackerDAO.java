package com.me.finalproj.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.me.finalproj.pojo.Event;
import com.me.finalproj.pojo.Hacker;
/*
	Author: Sunil Yadav on 23rd March 2019
*/
public class HackerDAO extends DAO {

	public boolean insertHacker(Hacker hacker) throws Exception {

		boolean userFound = false;
		try {
			begin();
			Query q = getSession().createQuery("from Person where username = :userName");
			q.setString("userName", hacker.getUserName());
			Hacker v = (Hacker) q.uniqueResult();
			if (v != null) {
				userFound = true;
				System.out.println("Vistor with USERNAME already exists in Database");
				return false;

			}

			if (userFound == false) {

				getSession().save(hacker);
				commit();
				return true;
			}
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating hacker: " + e.getMessage());
		} finally {
			close();
		}
		return false;
	}

	public Hacker searchHacker(String username, String password) throws Exception {

		try {
			begin();
			Query q = getSession().createQuery("from Person where username = :userName AND password = :passwd");
			q.setString("userName", username);
			q.setString("passwd", password);
			Hacker v = (Hacker) q.uniqueResult();
			return v;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while authenticating hacker: " + e.getMessage());

		} finally {
			close();
		}

	}

	public Hacker getHackerById(String id) throws Exception {

		try {
			begin();
			Query q = getSession().createQuery("from Hacker where hackerId = :vid");
			q.setString("vid", id);
			Hacker e = (Hacker) q.uniqueResult();
			commit();
			return e;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the events", e);
		} finally {
			close();
		}

	}

	public boolean registerHackerToEvent(Hacker v) throws Exception {
		try {
			begin();
			//Hacker vi=(Hacker)getSession().merge(v);
			getSession().update(v);
			commit();
			return true;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating hacker: " + e.getMessage());
		} finally {
			close();
		}

	}
	
	public Hacker unregisterEvent(Hacker v) throws Exception {
		try {
			begin();
			getSession().update(v);
			commit();
			return v;
		} catch (HibernateException ex) {
			rollback();
			throw new Exception("Exception while creating hacker: " + ex.getMessage());
		} finally {
			close();
		}

	}

}
