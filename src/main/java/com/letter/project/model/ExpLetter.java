package com.letter.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeData")
public class ExpLetter {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "name")
    private String name;
	@Column(name = "empcode")
    private Long empcode;
	@Column(name = "email")
    private String email;
	@Column(name = "designation")
    private String designation;
	@Column(name = "joindate")
    private String joindate;
	@Column(name = "resignationdate")
    private String resignationdate;
	@Column(name = "enddate")
    private String enddate;
	@Column(name = "location")
    private String location;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getEmpcode() {
		return empcode;
	}
	public void setEmpcode(Long empcode) {
		this.empcode = empcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getResignationdate() {
		return resignationdate;
	}
	public void setResignationdate(String resignationdate) {
		this.resignationdate = resignationdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
