package com.me.finalproj.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.me.finalproj.pojo.Hacker;

@Entity
@Table(name="event_table")
public class HackEvent {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eventID", unique = true, nullable = false)
    private int eventid;
	
	@Column(name="EventName")
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Place")
	private String place;
	
	@Column(name="Duration")
	private String duration;
	
	@Column(name="EventType")
	private String eventtype;
	
	@Column(name="SeatsAvailable")
	private String seatsavailable;
	
	@ManyToMany( fetch = FetchType.LAZY, mappedBy = "registeredEvents", cascade = CascadeType.ALL  )
	private Set<Hacker> hackersRegistered = new HashSet<Hacker>();

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getSeatsavailable() {
		return seatsavailable;
	}

	public void setSeatsavailable(String seatsavailable) {
		this.seatsavailable = seatsavailable;
	}

	public Set<Hacker> getHackersRegistered() {
		return hackersRegistered;
	}

	public void setHackersRegistered(Set<Hacker> hackersRegistered) {
		this.hackersRegistered = hackersRegistered;
	}
}
