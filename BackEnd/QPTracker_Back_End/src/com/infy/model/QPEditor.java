package com.infy.model;

import java.io.File;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;



public class QPEditor {
private String qpId;
private String emp_type;
private String eta_name;
public String getEta_name() {
	return eta_name;
}
public void setEta_name(String eta_name) {
	this.eta_name = eta_name;
}
public String getCreator_name() {
	return creator_name;
}
public void setCreator_name(String creator_name) {
	this.creator_name = creator_name;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
private LocalDate creation_date;
private String exam;
private String status;
private Date rolled_out_date;
private String creator_name;
private Integer id;

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getRolled_out_date() {
	return rolled_out_date;
}
public void setRolled_out_date(Date rolled_out_date) {
	this.rolled_out_date = rolled_out_date;
}
private String qpName;
private  byte[] file1;
private String remarks;
private String message;
private String path;
private Date reviewedDate;
private String reviewerName;


public String getReviewerName() {
	return reviewerName;
}
public void setReviewerName(String reviewerName) {
	this.reviewerName = reviewerName;
}
public String getEmp_type() {
	return emp_type;
}
public void setEmp_type(String emp_type) {
	this.emp_type = emp_type;
}
public LocalDate getCreation_date() {
	return creation_date;
}
public void setCreation_date(LocalDate creation_date) {
	this.creation_date = creation_date;
}
public String getExam() {
	return exam;
}
public void setExam(String exam) {
	this.exam = exam;
}

public Date getReviewedDate() {
	return reviewedDate;
}
public void setReviewedDate(Date reviewedDate) {
	this.reviewedDate = reviewedDate;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public byte[] getFile1() {
	return file1;
}
public void setFile1(byte[] file1) {
	this.file1 = file1;
}
public String getQpId() {
	return qpId;
}
public void setQpId(String qpId) {
	this.qpId = qpId;
}
public String getQpName() {
	return qpName;
}
public void setQpName(String qpName) {
	this.qpName = qpName;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}

}
