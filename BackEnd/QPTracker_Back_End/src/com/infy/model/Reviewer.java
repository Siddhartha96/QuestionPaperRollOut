package com.infy.model;

public class Reviewer {
	private String reviewerUsername;
	private String reviewerPassword;
	public QPEditor getQpDetails() {
		return qpDetails;
	}
	public void setQpDetails(QPEditor qpDetails) {
		this.qpDetails = qpDetails;
	}
	private QPEditor qpDetails;
	public String getReviewerUsername() {
		return reviewerUsername;
	}
	public void setReviewerUsername(String reviewerUsername) {
		this.reviewerUsername = reviewerUsername;
	}
	public String getReviewerPassword() {
		return reviewerPassword;
	}
	public void setReviewerPassword(String reviewerPassword) {
		this.reviewerPassword = reviewerPassword;
	}
	

}
