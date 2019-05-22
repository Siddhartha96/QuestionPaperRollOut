package com.infy.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.BatchDetailsEntity;
import com.infy.entity.LoginEntity;
import com.infy.entity.QPEditorEntity;
import com.infy.entity.RequestEntity;
import com.infy.model.BatchDetails;
import com.infy.model.ETA;
import com.infy.model.Login;
import com.infy.model.QPEditor;
import com.infy.model.Requests;

@Repository("dao")
public class QPTrackerDAOImpl implements QPTrackerDAO{
	
	@Autowired
	SessionFactory sessionFactory;

//Used to Login
	@Override
	public Login checkLogin(Login login) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		LoginEntity loginEntity= session.get(LoginEntity.class,login.getUsername());
		
		if (loginEntity!=null){Login l=new Login();
			l.setUsername(loginEntity.getUsername());
			l.setPassword(loginEntity.getPassword());
		    l.setCategory(loginEntity.getCategory());
		    if(login.getPassword().equals(l.getPassword())){
		    return l;}
		}
		session.clear();
		return null;
	    
		
	}
	
	
//Adding New Qp And Generating Id Automatically
	@Override
	public String addQp(QPEditor qp2) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Calendar calendar=Calendar.getInstance();
		Integer seconds=calendar.get(Calendar.SECOND);
		QPEditorEntity qp1= new QPEditorEntity();
		qp1.setFile1(qp2.getFile1());
		qp1.setCreator_name(qp2.getCreator_name());
		qp1.setReviewerName(qp2.getReviewerName());
		qp1.setQpName(qp2.getQpName());
		qp1.setStatus("no");
		Integer id=(Integer)session.save(qp1);
		System.out.println(id);  
		qp1.setCreation_date(new Date(1).valueOf(LocalDate.now()));
	    String qpid="JEE"+"-"+LocalDate.now().getMonth().toString()+LocalDate.now().getYear()+"-"+qp2.getEmp_type()+"-"+qp2.getExam()+"-"+id;
	    qp1.setQpId(qpid);
	    session.persist(qp1);
		return "success";
	}
	
//Download Qp in zip Format 
	@Override
	public String downloadqp(String getid) throws Exception {
		System.out.println(getid);
		String url = "jdbc:oracle:thin:@10.123.79.59:1521:georli04";
	    String user = "T562845";
	    String password = "T562845";
	    Connection conn = DriverManager.getConnection(url, user, password); 
		String sql1 = "SELECT * FROM QPdetails1 WHERE qpId=?";
	        PreparedStatement statement1 = conn.prepareStatement(sql1);
	        statement1.setString(1,getid);
	        ResultSet result = statement1.executeQuery();
		while (result.next()) {
		      String name = result.getString(1);
		      String description = result.getString(2);
		      String qpname=result.getString(3);
		      File image = new File("C:\\Users\\saurav13.TRN\\Downloads\\"+qpname+".zip");
		      FileOutputStream fos = new FileOutputStream(image);

		      byte[] buffer = new byte[1024];
		      InputStream is = result.getBinaryStream(4); //In which column does file exist ,then that number is given as input
		      while (is.read(buffer) > 0) {
		        fos.write(buffer);
		      }
		      fos.close();
		      is.close();
		        } return "success"; 
	}
	
//	Getting all Question Paper
	public List<QPEditor> getAllQp()throws Exception
	{	Session session = sessionFactory.getCurrentSession();
		List<QPEditor> retQp=new ArrayList<QPEditor>();
		QPEditorEntity qpEE=new QPEditorEntity();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//		LocalDate localDate = LocalDate.now();

		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QPEditorEntity> criteriaQuery= builder.createQuery(QPEditorEntity.class);
        Root<QPEditorEntity> root = criteriaQuery.from(QPEditorEntity.class);
        criteriaQuery.select(root);
        List<QPEditorEntity> qpEditorEntityList = session.createQuery(criteriaQuery).list();
        for (QPEditorEntity qpEntity : qpEditorEntityList) {
        	QPEditor qpEditor = new QPEditor();
        	qpEditor.setQpId(qpEntity.getQpId());
        	qpEditor.setRemarks(qpEntity.getRemarks());
        	qpEditor.setQpName(qpEntity.getQpName());
        	qpEditor.setReviewedDate(java.sql.Date.valueOf(LocalDate.now()));
        	
        	qpEditor.setReviewerName(qpEntity.getReviewerName());
        	qpEditor.setCreator_name(qpEntity.getCreator_name());
        	retQp.add(qpEditor);
        	
        }
        return retQp;
		
		
	}
	

//	Updating Existing Question Paper
	public String updateExistingQp(QPEditor qp2)throws Exception
	{
	
		Session session = sessionFactory.getCurrentSession();
		QPEditorEntity qp1= new QPEditorEntity();
		String url = "jdbc:oracle:thin:@10.123.79.59:1521:georli04";
	    String user = "T562845";
	    String password = "T562845";
	    Connection conn = DriverManager.getConnection(url, user, password);

		String sql = "UPDATE QPdetails1 SET file1=? WHERE qpId=?";
        PreparedStatement statement = conn.prepareStatement(sql);

        System.out.println("after1");
        System.out.println("after2");
        statement.setBytes(1,qp2.getFile1());
        statement.setString(2,qp2.getQpId());
        int row = statement.executeUpdate();
        conn.close();
		return "success";
	}
	
//Updating Remarks
	public String remarks(QPEditor qp)throws Exception
	{
		Session session = sessionFactory.getCurrentSession();
		QPEditorEntity qp1= new QPEditorEntity();
		String url = "jdbc:oracle:thin:@10.123.79.59:1521:georli04";
	    String user = "T562845";
	    String password = "T562845";
	    Connection conn = DriverManager.getConnection(url, user, password);
	    String[] parts=qp.getQpId().split("-");
	    Integer id=Integer.parseInt(parts[5]);
	    
	    qp1=session.get(QPEditorEntity.class,id);
	    qp1.setRemarks(qp.getRemarks());
	    session.persist(qp1);
		return "Remark sent successfully";
	}
	
//Get file of the particular reviewer	
	@Override
	public List<QPEditor> reviewFiles(String name)throws Exception
	{List<QPEditor> list1=new ArrayList<QPEditor>();
	System.out.println(name);
	name=name.replace(".TRN","");
	System.out.println(name);
		Session session = sessionFactory.getCurrentSession();
		QPEditorEntity qp1= new QPEditorEntity();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QPEditorEntity> criteriaQuery= builder.createQuery(QPEditorEntity.class);
        Root<QPEditorEntity> root = criteriaQuery.from(QPEditorEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("reviewerName"),name));
        
        List<QPEditorEntity> customerEntityList = session.createQuery(criteriaQuery).getResultList();
        for (QPEditorEntity qPEditorEntity : customerEntityList) {
        	System.out.println(qPEditorEntity.getQpId());
        	System.out.println(qPEditorEntity.getStatus());
        	if(qPEditorEntity.getStatus()!=null && qPEditorEntity.getStatus().equals("no")){
        	QPEditor qp2 = new QPEditor();
        	qp2.setQpId(qPEditorEntity.getQpId());
        	qp2.setQpName(qPEditorEntity.getQpName());
        	qp2.setRemarks(qPEditorEntity.getRemarks());
        	list1.add(qp2);
        }
        } 
	   return list1;
	}
//	Search Command in Case Insensitive
	@Override
	public List<QPEditor> getFiles(String name)throws Exception
	{List<QPEditor> list1=new ArrayList<QPEditor>();
		String name1=name.toLowerCase();

		Session session = sessionFactory.getCurrentSession();
		QPEditorEntity qp1= new QPEditorEntity();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QPEditorEntity> criteriaQuery= builder.createQuery(QPEditorEntity.class);
        Root<QPEditorEntity> root = criteriaQuery.from(QPEditorEntity.class);
        criteriaQuery.select(root);
//        criteriaQuery.where(builder.equal(root.get("qpName"),name1));
        
        List<QPEditorEntity> customerEntityList = session.createQuery(criteriaQuery).getResultList();
        for (QPEditorEntity qPEditorEntity : customerEntityList) {
        	String first=qPEditorEntity.getQpName().toLowerCase();
        	String pattern="[a-zA-Z-0-9 ]*"+name1+"[ a-zA-Z-0-9]*";
        	if(first.matches(pattern)){
        	QPEditor qp2 = new QPEditor();
        	qp2.setQpId(qPEditorEntity.getQpId());
        	qp2.setQpName(qPEditorEntity.getQpName());
        	qp2.setRemarks(qPEditorEntity.getRemarks());
        	list1.add(qp2);}
        	
        }
   
	    
		return list1;
	}
	
//	Displaying Reviewer Name`
	
	@Override
	public List<String> displayReviewerName()throws Exception
	
	{
		List<String> name=new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoginEntity> criteriaQuery= builder.createQuery(LoginEntity.class);
        Root<LoginEntity> root = criteriaQuery.from(LoginEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("category"),"Reviewer"));
        List<LoginEntity> customerEntityList = session.createQuery(criteriaQuery).getResultList();
        for (LoginEntity qPEditorEntity : customerEntityList) {
        	name.add(qPEditorEntity.getUsername());
        }
		
		return name;
	}
	
	
	//change status to yes when Qp is set ready to roll out
	@Override
	public String changestatus(String qpId)throws Exception{
		Session session = sessionFactory.getCurrentSession();
		QPEditorEntity qe=new QPEditorEntity();
		try{String[] parts=qpId.split("-");
	    Integer id=Integer.parseInt(parts[5]);
			qe=session.get(QPEditorEntity.class,id);
			qe.setStatus("Yes");
			qe.setReviewedDate(java.sql.Date.valueOf(LocalDate.now()));
			
		}catch(Exception e){
			throw e;
		}
		return "success";
	}
	
	
	
	
	//Get all Qp When qp Is ready to roll out 
		public List<QPEditor> etaFiles()throws Exception{
		List<QPEditor> list1=new ArrayList<QPEditor>();
//		String name1=batchname.toLowerCase();

		Session session = sessionFactory.getCurrentSession();
		QPEditorEntity qp1= new QPEditorEntity();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QPEditorEntity> criteriaQuery= builder.createQuery(QPEditorEntity.class);
        Root<QPEditorEntity> root = criteriaQuery.from(QPEditorEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("status"),"Yes"));
        List<QPEditorEntity> customerEntityList = session.createQuery(criteriaQuery).getResultList();
        for (QPEditorEntity qPEditorEntity : customerEntityList) {
        	QPEditor qp=new QPEditor();
        	qp.setQpId(qPEditorEntity.getQpId());
        	qp.setQpName(qPEditorEntity.getQpName());
        	qp.setReviewerName(qPEditorEntity.getReviewerName());
        	qp.setCreator_name(qPEditorEntity.getCreator_name());
        	qp.setStatus(qPEditorEntity.getStatus());
        	
        	qp.setReviewedDate(qPEditorEntity.getReviewedDate());
        	qp.setRolled_out_date(qPEditorEntity.getRolled_out_date());
        	System.out.println(qp.getQpId());
        	list1.add(qp);}
        return list1;
       
       
		
	}
		
	
	//set rolled out date when roll out button is clicked
	
	@Override
	public String setRolledout(String qpId)throws Exception{
		Session session = sessionFactory.getCurrentSession();
	LocalDate ld=LocalDate.now();
	String s=ld.toString();
		QPEditorEntity qe=new QPEditorEntity();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QPEditorEntity> criteriaQuery= builder.createQuery(QPEditorEntity.class);
        Root<QPEditorEntity> root = criteriaQuery.from(QPEditorEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("qpId"),qpId));
        QPEditorEntity customerEntityList = session.createQuery(criteriaQuery).getSingleResult();
        customerEntityList.setRolled_out_date(java.sql.Date.valueOf(LocalDate.now()));
        session.persist(customerEntityList);
		
		return "success";
	}
	
	
//	Get all currently existing batch List when 
	
	 @Override
	 public List<BatchDetails> batchDetails() throws Exception {
		List<BatchDetails> list1=new ArrayList<BatchDetails>();
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BatchDetailsEntity> criteriaQuery= builder.createQuery(BatchDetailsEntity.class);
        Root<BatchDetailsEntity> root = criteriaQuery.from(BatchDetailsEntity.class);
        criteriaQuery.select(root);
        List<BatchDetailsEntity> qpEditorEntityList = session.createQuery(criteriaQuery).list();
        for(BatchDetailsEntity be:qpEditorEntityList){
        	BatchDetails bd=new BatchDetails();
        	bd.setBatch(be.getBatch());
        	list1.add(bd);
        }
		return list1;
	}
	 
//	 Forgot password 
	 @Override
	 public String changePassword(Login login) throws Exception
	 {
		 Session session = sessionFactory.getCurrentSession();
			LoginEntity qe=session.get(LoginEntity.class,login.getUsername());
				if(qe!=null){
					Login l=new Login();
					l.setsQuestion(qe.getsQuestion());
					l.setsAnswer(qe.getsAnswer());
					if(login.getNewPassword().equals(login.getConfirmPassword()))
					{
						if(l.getsQuestion().equals(login.getsQuestion()) && l.getsAnswer().equals(login.getsAnswer()))
						{							
							LoginEntity loginEntity=new LoginEntity();
							String url = "jdbc:oracle:thin:@10.123.79.59:1521:georli04";
						    String user = "T562845";
						    String password = "T562845";
						    Connection conn = DriverManager.getConnection(url, user, password);
	    
						    String sql = "UPDATE Login SET password=? WHERE username=?";
					        PreparedStatement statement = conn.prepareStatement(sql);

					        System.out.println("after1");
					        System.out.println("after2");
					        statement.setString(1,login.getNewPassword());
					        statement.setString(2,login.getUsername());
					        int row = statement.executeUpdate();
					        conn.close();
							return "Password Changed Successfully";
						}
						return "Security Question";
					}
					return "Password doesnot match";
					
				}
				return "Incorrect Username";
		 }

//	 Used to send request
	 public Requests createrequest(Requests req) throws Exception{
		 Requests r=new Requests();
		 Session session = sessionFactory.getCurrentSession();
		 RequestEntity re=new RequestEntity();
		 re.setReviewer_name(req.getReviewer_name());
		 re.setComponent(req.getComponent());
		 re.setCreator_name(req.getCreator_name());
		 re.setDeadline_date(req.getDeadline_date());
		 re.setEmptype(req.getEmptype());
		 Integer id=(Integer)session.save(re);
		 re.setReqId(id);
		 return r;
	 }
	 
	 
	 
	 
	 
//Get request when request is created
	 public List<Requests> getrequest(String name) throws Exception{
		 List<Requests> list1=new ArrayList<>();
		 Session session = sessionFactory.getCurrentSession();
		 try{
		 CriteriaBuilder builder = session.getCriteriaBuilder();
	     CriteriaQuery<RequestEntity> criteriaQuery= builder.createQuery(RequestEntity.class);
	     Root<RequestEntity> root = criteriaQuery.from(RequestEntity.class);
	     criteriaQuery.select(root);
	     criteriaQuery.where(builder.equal(root.get("creator_name"),name));
	     List<RequestEntity> customerEntityList = session.createQuery(criteriaQuery).getResultList();
	     for(RequestEntity r1:customerEntityList){
	    	 Requests r=new Requests();
	    	 r.setComponent(r1.getComponent());
	    	 r.setCreator_name(r1.getCreator_name());
	    	 r.setDeadline_date(r1.getDeadline_date());
	    	 r.setEmptype(r1.getEmptype());
	    	 r.setReqId(r1.getReqId());
	    	 r.setReviewer_name(r1.getReviewer_name());
	    	 list1.add(r);
	 
	     }
	     return list1;
		 }catch(Exception e){
	    	 throw e;
	     }
	 }
	 
	 
	 
//	 Display all the Creator name
	 @Override
		public List<String> displayCreatorName()throws Exception
		
		{
			List<String> name=new ArrayList<>();
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<LoginEntity> criteriaQuery= builder.createQuery(LoginEntity.class);
	        Root<LoginEntity> root = criteriaQuery.from(LoginEntity.class);
	        criteriaQuery.select(root);
	        criteriaQuery.where(builder.equal(root.get("category"),"QPEditor"));
	        List<LoginEntity> customerEntityList = session.createQuery(criteriaQuery).getResultList();
	        for (LoginEntity qPEditorEntity : customerEntityList) {
	        	name.add(qPEditorEntity.getUsername());
	        }
			
			return name;
		}
}
