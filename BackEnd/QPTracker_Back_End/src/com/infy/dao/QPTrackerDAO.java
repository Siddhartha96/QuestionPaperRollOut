package com.infy.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.infy.entity.QPEditorEntity;
import com.infy.model.BatchDetails;
import com.infy.model.ETA;
import com.infy.model.Login;
import com.infy.model.QPEditor;
import com.infy.model.Requests;

public interface QPTrackerDAO {
	
	public Login checkLogin(Login login) throws Exception;
	public String addQp(QPEditor qp2) throws Exception;
	public String downloadqp(String getid) throws Exception;
	public List<QPEditor> getAllQp()throws Exception;
	public String updateExistingQp(QPEditor getId)throws Exception;
	public String remarks(QPEditor qp)throws Exception;
	public List<QPEditor> reviewFiles(String name)throws Exception;
	public List<QPEditor> getFiles(String name)throws Exception;
	public List<String> displayReviewerName()throws Exception;
	public String changestatus(String qpId)throws Exception;
	public List<QPEditor> etaFiles()throws Exception;
	public String setRolledout(String qpId)throws Exception;
	public List<BatchDetails> batchDetails() throws Exception;
	public String changePassword(Login login) throws Exception;
	public Requests createrequest(Requests req) throws Exception;
	public List<Requests> getrequest(String name) throws Exception;
	public List<String> displayCreatorName()throws Exception;
}
