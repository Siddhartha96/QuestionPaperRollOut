package com.infy.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="batchDetails")
public class BatchDetailsEntity {
	@Id
	private String batch;
	
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

}
