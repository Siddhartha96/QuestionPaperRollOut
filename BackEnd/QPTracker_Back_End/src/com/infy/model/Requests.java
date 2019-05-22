package com.infy.model;

import java.time.LocalDate;

public class Requests {
	private Integer reqId;
public Integer getReqId() {
		return reqId;
	}
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}
private String emptype;
private String component;
private String creator_name;
private String reviewer_name;
private LocalDate deadline_date;
private String message;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getEmptype() {
	return emptype;
}
public void setEmptype(String emptype) {
	this.emptype = emptype;
}
public String getComponent() {
	return component;
}
public void setComponent(String component) {
	this.component = component;
}
public String getCreator_name() {
	return creator_name;
}
public void setCreator_name(String creator_name) {
	this.creator_name = creator_name;
}
public String getReviewer_name() {
	return reviewer_name;
}
public void setReviewer_name(String reviewer_name) {
	this.reviewer_name = reviewer_name;
}
public LocalDate getDeadline_date() {
	return deadline_date;
}
public void setDeadline_date(LocalDate deadline_date) {
	this.deadline_date = deadline_date;
}

}
