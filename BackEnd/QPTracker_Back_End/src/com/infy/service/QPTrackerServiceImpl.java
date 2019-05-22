package com.infy.service;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.print.DocFlavor.BYTE_ARRAY;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.core.util.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.QPTrackerDAO;
import com.infy.model.BatchDetails;
import com.infy.model.ETA;
import com.infy.model.Login;
import com.infy.model.QPEditor;
import com.infy.model.Requests;

@Service("qpTrackerService")
@Transactional(readOnly=true)
public class QPTrackerServiceImpl implements QPTrackerService{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Autowired
	private QPTrackerDAO dao; 

//Check for login
	@Override
	@Transactional(readOnly = true)
	public Login checkLogin(Login login) throws Exception {
		Login login1=dao.checkLogin(login);
		if (login1==null){
			throw new Exception("Service.INVALID_LOGIN_CREDENTIALS");
		}
		else if (!login1.getCategory().equals(login.getCategory()))
	    {
	    	throw new Exception("Service.INVALID_CATEGORY");
	    }
		return login1;
	}
	
	
//	
	public String get_path(String directoryName,List<File> files,QPEditor qp2)
	{
		String path1=qp2.getPath();
		System.out.println(path1);
		String path2=path1.replace("C:\\fakepath\\","");
		System.out.println(path2);
		File directory = new File(directoryName);
		Integer c=1;
		File[] flist=directory.listFiles();
		String p="";
        if(flist!=null)
        {
        	for(File f:flist)
        	{
        		if(f.isFile())
        		{files.add(f);
        	if(f.getName().equals(path2)){p=p+f.getAbsolutePath();
        	c=0;
        	break;
        	}}
        		if(f.isDirectory())
        		{ 
        			files.add(f);
        			get_path(f.getAbsolutePath(),files,qp2);
        		}}
        	}
        
        if(c!=0){
        for(File f1:files){if(f1.getName().equals(path2))
		{
			p=p+f1.getAbsolutePath();
			break;
		}}
        	
        }
        
       return p;
	}
	
//	Upload Qp From the Pre Defined Path
	@Override
	@Transactional(readOnly = false,propagation  = Propagation.REQUIRES_NEW)
	 public QPEditor addqp(QPEditor qpe) throws Exception {
		String workingDirectory=System.getProperty("user.home");
		List<File> f1=new ArrayList<File>();
		String path2=qpe.getPath().replace("C:\\fakepath\\","");
		String path3="C:\\Users\\saurav13.TRN\\Desktop\\Upload\\"+path2;
		System.out.println(path3);
		 File in=new File(path3);
		 FileInputStream fin=new FileInputStream(in);
		 byte[] buffer = new byte[(int)in.length()];
		 System.out.println("before");
		 fin.read(buffer);
		 System.out.println("after");
		 qpe.setFile1(buffer);			
			String s=dao.addQp(qpe);
			if(s=="success"){System.out.println(s);}
			
			return qpe;	
		
	}
	
	@Override
	public void extractEntry(ZipEntry entry, InputStream is) throws Exception {
		System.out.println(entry.getName());
		String s=entry.getName().replace("qp1/", "");
		System.out.println(s);
	
		String extracted_file="C:\\Users\\siddhartha06.TRN\\Documents\\qp1.zip";
		FileOutputStream fos=null;
		try
		{
			fos=new FileOutputStream(extracted_file);
			ZipOutputStream zos=new ZipOutputStream(fos);
			
			File f=new File(s);
			String path=f.getCanonicalPath();
			FileInputStream fis=new FileInputStream(path);
			ZipEntry zee=new ZipEntry(s);
			zos.putNextEntry(zee);
			byte[] bytes=new byte[1024];
			
			final byte[] buf=new byte[8192];
			Integer re=0;
			Integer length;
			while((length=fis.read(bytes))>=0)
			{
				System.out.println("hiiii");
				System.out.println(bytes);
				zos.write(bytes, 0, length);
			}
			zos.closeEntry();
			fis.close();
		}
		finally
		{
			fos.close();
		}
		
	}
	
//Download Qp
	@Override
	@Transactional(readOnly = true)
	 public QPEditor downloadqp(String getid) throws Exception {
		String message=dao.downloadqp(getid);
		QPEditor ed=new QPEditor();
		ed.setMessage(message);
		return ed;
	}
	
	public List<QPEditor> getAllQp()throws Exception
	{	
		List<QPEditor> retQp=new ArrayList<QPEditor>();
		try{
			retQp=dao.getAllQp();
			if(retQp==null || retQp.isEmpty())
			{
				throw new Exception("Service.EMPTY_DATABASE");
			}
			}
		catch(Exception e)
			{
				throw e;
			}
			return retQp;
	}

//	Update Existing qp
	@Override
	@Transactional(readOnly = false,propagation  = Propagation.REQUIRES_NEW)
	public QPEditor updateExistingQp(QPEditor qp)throws Exception
	{
//		QPEditor qpEditor=new QPEditor();
	
		
			System.out.println("hi");

			String path2=qp.getPath().replace("C:\\fakepath\\","");
			String path3="C:\\Users\\saurav13.TRN\\Desktop\\Upload\\"+path2;
			System.out.println(path3);
			 File in=new File(path3);
			 FileInputStream fin=new FileInputStream(in);
			 byte[] buffer = new byte[(int)in.length()];
			 System.out.println("before");
			 fin.read(buffer);
			 System.out.println("after");
			 qp.setFile1(buffer);			
				String s=dao.updateExistingQp(qp);
				if(s=="success"){System.out.println(s);}
				//ZipFile zp=new ZipFile(addQp(qp2));
				return qp;
	
	}
	

//	Set remarks
	@Override
	@Transactional(readOnly = false,propagation  = Propagation.REQUIRES_NEW)
	public QPEditor remarks(QPEditor qp)throws Exception
	{
//		QPEditor qpEditor=new QPEditor();
	
				String s=dao.remarks(qp);
				qp.setMessage(s);
				return qp;
	
	}
	

//	Get all the reviewer file
	@Override
	public List<QPEditor> reviewFiles(String name)throws Exception
	{	
		List<QPEditor> retQp=new ArrayList<QPEditor>();
		try{
			retQp=dao.reviewFiles(name);
			}
		catch(Exception e)
			{
				throw e;
			}
			return retQp;
	}
	
// 	 Get all the files
	@Override
	public List<QPEditor> getFiles(String name)throws Exception
	{	
		List<QPEditor> retQp=new ArrayList<QPEditor>();
		try{
			retQp=dao.getFiles(name);
			}
		catch(Exception e)
			{
				throw e;
			}
			return retQp;
	}
	
//	Display Reviewer name
	@Override
	public List<String> displayReviewerName()throws Exception
	{
		List<String> name=new ArrayList<String>();
		List<String> name2=new ArrayList<String>();
		try{
			name=dao.displayReviewerName();
			for(String s:name){
				String name1=s.replace(".TRN","");
				name2.add(name1);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return name2;
	}
	
	
	//change status when ready to roll out is pressed
	@Override
	@Transactional(readOnly = false,propagation  = Propagation.REQUIRES_NEW)
	public String changestatus(String qpId)throws Exception{
		String message;
		try{
			message=dao.changestatus(qpId);
		}
		catch(Exception e){
			throw e;
		}
		return message;
	}
	
	
	//get ETA Files according to batch
	
	@Override
	public List<QPEditor> etaFiles(ETA eta)throws Exception{
		List<QPEditor> retQp=new ArrayList<QPEditor>();
		List<QPEditor> retQp2=new ArrayList<QPEditor>();
		try{
			retQp=dao.etaFiles();
			if(retQp==null){
				System.out.println("null");
			}
			for(QPEditor list:retQp){
				String id=list.getQpId();
				System.out.println(id);
				Date date=new Date(0);
				String[] parts=id.split("-");
				String calendar=parts[1];
				String[] batchpart=eta.getBatchname().split("-");
				
				String examtype=batchpart[1];
				String qpexamtype=parts[2];
				System.out.println(examtype);
				System.out.println(qpexamtype);
				System.out.println(calendar);
				Integer batchInt=0;
				Integer subInt=0;
				String subMonth=calendar.substring(0,3);
				Integer lent=calendar.length();
				Integer subYear=Integer.parseInt(calendar.substring(lent-2,lent));
				String batchMonth=eta.getBatchname().substring(0,3);
				Integer batchYear=Integer.parseInt(eta.getBatchname().substring(3,5));
				System.out.println(batchMonth);
				System.out.println(batchYear);
				System.out.println(subMonth);
				System.out.println(subYear);
				
				Integer yearDiff=batchYear-subYear;
				if(yearDiff<=1){
					if(batchMonth.equals("Jan")){
						 batchInt=1;
					}
					if(batchMonth.equals("Feb")){
						 batchInt=2;
					}
					if(batchMonth.equals("Mar")){
						batchInt=3;
					}
					if(batchMonth.equals("Apr")){
						batchInt=4;
					}
					if(batchMonth.equals("May")){
						 batchInt=5;
					}
					if(batchMonth.equals("Jun")){
						 batchInt=6;
					}
					if(batchMonth.equals("Jul")){
						 batchInt=7;
					}
					if(batchMonth.equals("Aug")){
						 batchInt=8;
					}
					if(batchMonth.equals("Sep")){
						 batchInt=9;
					}
					if(batchMonth.equals("Oct")){
						 batchInt=10;
					}
					if(batchMonth.equals("Nov")){
						 batchInt=11;
					}
					if(batchMonth.equals("Dec")){
						 batchInt=12;
					}
					
					if(subMonth.equals("JAN")){
						subInt=1;
					}
					if(subMonth.equals("FEB")){
						subInt=2;
					}
					if(subMonth.equals("MAR")){
						subInt=3;
					}
					if(subMonth.equals("APR")){
						subInt=4;
					}
					if(subMonth.equals("MAY")){
						subInt=5;
					}
					if(subMonth.equals("JUN")){
						subInt=6;
					}
					if(subMonth.equals("JUL")){
						subInt=7;
					}
					if(subMonth.equals("AUG")){
						subInt=8;
					}
					if(subMonth.equals("SEP")){
						subInt=9;
					}
					if(subMonth.equals("OCT")){
						subInt=10;
					}
					if(subMonth.equals("NOV")){
						subInt=11;
					}
					if(subMonth.equals("DEC")){
						subInt=12;
					}
					if((batchInt-subInt>0 && subYear!=batchYear) || list.getRolled_out_date()==null){
						Integer exam=Integer.parseInt(parts[4]);
						Integer exam1=Integer.parseInt(eta.getExam().substring(2,3));
						if(exam==exam1 && examtype.equals(qpexamtype) ){
						
						retQp2.add(list);}
					}
					if(list.getRolled_out_date()!=null && examtype.equals(qpexamtype)){
						Integer exam=Integer.parseInt(parts[4]);
						Integer exam1=Integer.parseInt(eta.getExam().substring(2,3));
						if(exam==exam1 && examtype.equals(qpexamtype) ){
						
						Integer year=Integer.parseInt(list.getRolled_out_date().toString().substring(0,4));
						System.out.println(year);
						Integer month=Integer.parseInt(list.getRolled_out_date().toString().substring(5,7));
						if(batchYear-year==1 && batchInt-month>0){
							retQp2.add(list);
						}}
					}
				}
				
				else if(yearDiff>1 || list.getRolled_out_date()==null ){
					Integer exam=Integer.parseInt(parts[4]);
					Integer exam1=Integer.parseInt(eta.getExam().substring(2,3));
					if(exam==exam1 && examtype.equals(qpexamtype)){
					
					retQp2.add(list);}
					if(list.getRolled_out_date()!=null){
						if(exam==exam1 && examtype.equals(qpexamtype) ){
						Integer year=Integer.parseInt(list.getRolled_out_date().toString().substring(0,4));
						System.out.println(year);
						Integer month=Integer.parseInt(list.getRolled_out_date().toString().substring(5,7));
						if(batchYear-year==1 && batchInt-month>0){
							retQp2.add(list);
						}}
					}
				
				}
			}
			}
		catch(Exception e)
			{
				throw e;
			}
		return retQp2;
	}
	
	
	//set rolled out date
	@Override
	@Transactional(readOnly = false,propagation  = Propagation.REQUIRES_NEW)
	public String setRolledout(String qpId)throws Exception{
		String message="";
		try{
			message=dao.setRolledout(qpId);
		}catch(Exception e){throw e;}
		return message;
	}
	
//	Get all the Existing Batch 
	@Override
	@Transactional(readOnly = true)
	 public List<BatchDetails> batchDetails() throws Exception {
		List<BatchDetails> list1=new ArrayList<BatchDetails>();
		list1=dao.batchDetails();
		return list1;
	}
	
//	forgot password
	@Override
	@Transactional(readOnly = false)
	public String changePassword(Login login) throws Exception
	{
		String message;
		try{
			message=dao.changePassword(login);
		}
		catch(Exception e){
			throw e;
		}
		return message;
	}
	
//	Create Request
	
	@Override
	@Transactional(readOnly = false)
	public Requests createrequest(Requests req) throws Exception{
		try{
			Requests re=dao.createrequest(req);
			return re;
		}catch(Exception e){
			throw e;
		}
	}
	
//	Send Request
	@Override
	@Transactional(readOnly = true)
	public List<Requests> getrequest(String name) throws Exception{
		try{List<Requests> list1=new ArrayList<>();
			 list1=dao.getrequest(name);
			return list1;
		}catch(Exception e){
			throw e;
		}
		
	}
	
//	Display Creator Name
	
	@Override
	public List<String> displayCreatorName()throws Exception
	{
		List<String> name=new ArrayList<String>();
		List<String> name2=new ArrayList<String>();
		try{
			name=dao.displayCreatorName();
//			for(String s:name){
//				String name1=s.replace(".TRN","");
//				name2.add(name1);
//			}
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return name;
	}
	
	
	//get all eta
	@Override
	public List<QPEditor> getAlletaFiles()throws Exception{
		List<QPEditor> retQp=new ArrayList<QPEditor>();
		List<QPEditor> retQp2=new ArrayList<QPEditor>();
		try{
			retQp=dao.etaFiles();
			if(retQp==null){
				System.out.println("null");
			}}catch(Exception e){throw e;}
			return retQp;}
}
