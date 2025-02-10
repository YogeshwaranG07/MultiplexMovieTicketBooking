package com.cts.multiplexmoviebooking.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Hall {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hallId;
	private String hallDesc;
	private int capacity;
	@OneToMany	
	private List<HallCapacity> hallcapacity;
	
	public List<HallCapacity> getHallcapacity() {
		return hallcapacity;
	}
	public void setHallcapacity(List<HallCapacity> hallcapacity) {
		this.hallcapacity = hallcapacity;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public String getHallDesc() {
		return hallDesc;
	}
	public void setHallDesc(String hallDesc) {
		this.hallDesc = hallDesc;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
