package com.infy.entity;



import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;





import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="QPdetails1")
@GenericGenerator(name = "idGen", strategy = "increment")
public class QPEditorEntity {
@Id
@GeneratedValue(generator = "idGen")
private Integer id;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
private String qpId;

private String qpName;
private byte[] file1;
private String remarks;
private Date reviewedDate;
private Date rolled_out_date;
private String creator_name;
private Date creation_date;
public String getCreator_name() {
	return creator_name;
}
public void setCreator_name(String creator_name) {
	this.creator_name = creator_name;
}
public Date getCreation_date() {
	return creation_date;
}
public void setCreation_date(Date creation_date) {
	this.creation_date = creation_date;
}
public String getEta_name() {
	return eta_name;
}
public void setEta_name(String eta_name) {
	this.eta_name = eta_name;
}
private String eta_name;
public Date getRolled_out_date() {
	return rolled_out_date;
}
public void setRolled_out_date(Date rolled_out_date) {
	this.rolled_out_date = rolled_out_date;
}
private String reviewerName;
private String status;

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getReviewedDate() {
	return reviewedDate;
}
public String getReviewerName() {
	return reviewerName;
}
public void setReviewerName(String reviewerName) {
	this.reviewerName = reviewerName;
}
public void setReviewedDate(Date reviewedDate) {
	this.reviewedDate = reviewedDate;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
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

}
