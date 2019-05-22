package com.infy.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;

import com.infy.model.BatchDetails;
import com.infy.model.ETA;
import com.infy.model.Login;
import com.infy.model.QPEditor;
import com.infy.model.Requests;

public interface QPTrackerService {
	
	public Login checkLogin(Login login) throws Exception;
	public QPEditor addqp(QPEditor qpe) throws Exception;
//	public QPEditor getqp(QPEditor qpe1) throws Exception;
	public QPEditor downloadqp(String getid) throws Exception;
	public void extractEntry(final ZipEntry entry, InputStream is)throws Exception;
	public List<QPEditor> getAllQp()throws Exception;
	public QPEditor updateExistingQp(QPEditor qp)throws Exception;
	public QPEditor remarks(QPEditor qp)throws Exception;
	public List<QPEditor> reviewFiles(String name)throws Exception;
	public List<QPEditor> getFiles(String name)throws Exception;
	public List<String> displayReviewerName()throws Exception;
	public String changestatus(String qpId)throws Exception;
	public List<QPEditor> etaFiles(ETA eta)throws Exception;
	public String setRolledout(String qpId)throws Exception;
	public List<BatchDetails> batchDetails() throws Exception;
	public String changePassword(Login login) throws Exception;
	public Requests createrequest(Requests req) throws Exception;
	public List<Requests> getrequest(String name) throws Exception;
	public List<String> displayCreatorName()throws Exception;
	public List<QPEditor> getAlletaFiles()throws Exception;
}
