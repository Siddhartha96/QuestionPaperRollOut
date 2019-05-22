package com.infy.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.BatchDetails;
import com.infy.model.ETA;
import com.infy.model.Login;
import com.infy.model.QPEditor;
import com.infy.model.Requests;
import com.infy.service.QPTrackerService;
import com.infy.service.QPTrackerServiceImpl;
import com.infy.utility.ContextFactory;

@CrossOrigin
@RestController
@RequestMapping(value="QPTracker")
public class QPTrackerAPI {
	
	private QPTrackerService service;
	
	@RequestMapping(method=RequestMethod.POST,value="checkLogin")
	public ResponseEntity<Login> checkLogin(@RequestBody Login login)
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<Login> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			Login login1=new Login();
			login1=service.checkLogin(login);
			login1.setMessage("success");
			responseEntity=new ResponseEntity<Login>(login1,HttpStatus.OK);
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<Login>(fb,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="createqp")
	public ResponseEntity<QPEditor> createqp(@RequestBody QPEditor qpcreate){
		System.out.println("hi");
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<QPEditor> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			QPEditor qp1=new QPEditor();
			qp1=service.addqp(qpcreate);
			System.out.println("api");
			qp1.setMessage("success");
			responseEntity=new ResponseEntity<QPEditor>(qp1,HttpStatus.OK);
			
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				QPEditor qp1=new QPEditor();
				qp1.setMessage("SIDD");
				responseEntity = new ResponseEntity<QPEditor>(qp1,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	
	@RequestMapping(method=RequestMethod.GET,value="download/{getid}")
	public ResponseEntity<QPEditor> downloadqp(@PathVariable("getid") String getid){

		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<QPEditor> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			QPEditor qpp=new QPEditor();
		qpp= service.downloadqp(getid);
		responseEntity=new ResponseEntity<QPEditor>(qpp,HttpStatus.OK);
		return responseEntity;
		}catch(Exception e){
			QPEditor qpp=new QPEditor();
			qpp.setMessage("you are fucked!!");
			responseEntity = new ResponseEntity<QPEditor>(qpp,HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="getAllQp")
	public ResponseEntity<List<QPEditor>> getAllQp()
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<QPEditor>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<QPEditor> getdt=new ArrayList<QPEditor>();
			getdt=service.getAllQp();
			responseEntity=new ResponseEntity<List<QPEditor>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<QPEditor>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
//Update Existing QP
	@RequestMapping(method=RequestMethod.POST,value="updateqp")
	public ResponseEntity<QPEditor> updateExistingQp(@RequestBody QPEditor qpcreate){
		System.out.println("hi");
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<QPEditor> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			QPEditor qp1=new QPEditor();
			qp1=service.updateExistingQp(qpcreate);
			System.out.println("api");
			qp1.setMessage("success");
			responseEntity=new ResponseEntity<QPEditor>(qp1,HttpStatus.OK);
			
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				QPEditor qp1=new QPEditor();
				qp1.setMessage("SIDD");
				responseEntity = new ResponseEntity<QPEditor>(qp1,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="remarks")
	public ResponseEntity<QPEditor> remarks(@RequestBody QPEditor remarks){
		System.out.println("hi");
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<QPEditor> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			QPEditor qp1=new QPEditor();
			qp1=service.remarks(remarks);
			System.out.println("api");
			qp1.setMessage("Remarks Added Successfully");
			responseEntity=new ResponseEntity<QPEditor>(qp1,HttpStatus.OK);
			
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				QPEditor qp1=new QPEditor();
				qp1.setMessage("SIDD");
				responseEntity = new ResponseEntity<QPEditor>(qp1,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="reviewFiles/{name}")
	public ResponseEntity<List<QPEditor>> reviewFiles(@PathVariable String name)
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<QPEditor>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<QPEditor> getdt=new ArrayList<QPEditor>();
			getdt=service.reviewFiles(name);
			responseEntity=new ResponseEntity<List<QPEditor>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<QPEditor>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="getFiles/{name}")
	public ResponseEntity<List<QPEditor>> getFiles(@PathVariable String name)
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<QPEditor>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<QPEditor> getdt=new ArrayList<QPEditor>();
			getdt=service.getFiles(name);
			responseEntity=new ResponseEntity<List<QPEditor>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<QPEditor>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="displayReviewerName")
	public ResponseEntity<List<String>> displayReviewerName()
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<String>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<String> getdt=new ArrayList<String>();
			getdt=service.displayReviewerName();
			responseEntity=new ResponseEntity<List<String>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	//change status after clicking "Ready to rollout" button in front
	
	@RequestMapping(method=RequestMethod.GET,value="changestatus/{qpId}")
	public ResponseEntity<String> changestatus(@PathVariable String qpId)
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<String> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			String getdt;
			getdt=service.changestatus(qpId);
			responseEntity=new ResponseEntity<String>(HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	
	//Get files for a batch
	@RequestMapping(method=RequestMethod.POST,value="etaFiles")
	public ResponseEntity<List<QPEditor>> etaFiles(@RequestBody ETA eta)
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<QPEditor>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<QPEditor> getdt=new ArrayList<QPEditor>();
			getdt=service.etaFiles(eta);
			responseEntity=new ResponseEntity<List<QPEditor>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<QPEditor>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	//Setting rolled out date
	
	@RequestMapping(method=RequestMethod.POST,value="setRolledout")
	public ResponseEntity<String> setRolledout(@RequestBody String qpId)
	{System.out.println(qpId);
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<String> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			String getdt;
			getdt=service.setRolledout(qpId);
			getdt="Rolled out Successfully";
			responseEntity=new ResponseEntity<String>(getdt,HttpStatus.OK);
			System.out.println("Hello");
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="batchDetails")
	public ResponseEntity<List<BatchDetails>> batchDetails()
	{System.out.println();
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<BatchDetails>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<BatchDetails> getdt=new ArrayList<BatchDetails>();
			getdt=service.batchDetails();
			responseEntity=new ResponseEntity<List<BatchDetails>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<BatchDetails>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
// Forgot password then change it	
	@RequestMapping(method=RequestMethod.POST,value="changePassword")
	public ResponseEntity<Login> changePassword(@RequestBody Login login){
//		System.out.println("hi");
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<Login> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			String qp1;
			Login l=new Login();
			qp1=service.changePassword(login);
			System.out.println("api");
			l.setMessage(qp1);
			responseEntity=new ResponseEntity<Login>(l,HttpStatus.OK);
			
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login qp1=new Login();
				qp1.setMessage("SIDD");
				responseEntity = new ResponseEntity<Login>(qp1,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	//Create Request
	@RequestMapping(method=RequestMethod.POST,value="createrequest")
	public ResponseEntity<Requests> createrequest(@RequestBody Requests req){
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<Requests> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			Requests qp1=new Requests();
			Requests l=new Requests();
			qp1=service.createrequest(req);
			l.setMessage("Request sent successfully");
			responseEntity=new ResponseEntity<Requests>(l,HttpStatus.OK);
			
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Requests qp1=new Requests();
				
				responseEntity = new ResponseEntity<Requests>(qp1,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="getrequest")
	public ResponseEntity<List<Requests>> getrequest(@RequestBody String name){
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<Requests>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			Requests qp1=new Requests();
			List<Requests> l=new ArrayList<Requests>();
			l=service.getrequest(name);
		
			responseEntity=new ResponseEntity<List<Requests>>(l,HttpStatus.OK);
			
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				List<Requests> l=new ArrayList<Requests>();
				Requests qp1=new Requests();
				qp1.setMessage(errorMessage);
				l.add(qp1);
				
				responseEntity = new ResponseEntity<List<Requests>>(l,HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="displayCreatorName")
	public ResponseEntity<List<String>> displayCreatorName()
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<String>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<String> getdt=new ArrayList<String>();
			getdt=service.displayCreatorName();
			responseEntity=new ResponseEntity<List<String>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="getAlletaFiles")
	public ResponseEntity<List<QPEditor>> getAlletaFiles()
	{
		Environment environment=ContextFactory.getContext().getEnvironment();
		ResponseEntity<List<QPEditor>> responseEntity;
		service=(QPTrackerService) ContextFactory.getContext().getBean(QPTrackerServiceImpl.class);
		try{
			List<QPEditor> getdt=new ArrayList<QPEditor>();
			getdt=service.getAlletaFiles();
			responseEntity=new ResponseEntity<List<QPEditor>>(getdt,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception exception){
			
				String errorMessage = environment.getProperty(exception.getMessage());
				Login fb = new Login();
				fb.setMessage(errorMessage);
				responseEntity = new ResponseEntity<List<QPEditor>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
}
