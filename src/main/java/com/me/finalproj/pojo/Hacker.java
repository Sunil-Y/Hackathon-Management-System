package com.me.finalproj.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


import com.me.finalproj.pojo.Event;
import com.me.finalproj.pojo.Person;

@Entity
@Table(name = "hacker_table")
@PrimaryKeyJoinColumn(name = "hackerId")
public class Hacker extends Person{

	@ManyToMany(targetEntity = Event.class, fetch = FetchType.EAGER,cascade = { CascadeType.REMOVE })
	@JoinTable(name = "hackerEvents", 
				joinColumns = { @JoinColumn(name = "hackerId",nullable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "eventID",nullable = false) })
	private Set<Event> registeredEvents = new HashSet<Event>();
	
	@Column(name="Address")
	private String address;
	
	@OneToOne(mappedBy = "hack", cascade = CascadeType.ALL)
	private Email email;
	
	@Column(name="AdminStatus")
	private boolean admin;

	
	public Hacker() {
		
	}

	public Set<Event> getRegisteredEvents() {
		return registeredEvents;
	}

	public void setRegisteredEvents(Set<Event> registeredEvents) {
		this.registeredEvents = registeredEvents;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}	
	
}
