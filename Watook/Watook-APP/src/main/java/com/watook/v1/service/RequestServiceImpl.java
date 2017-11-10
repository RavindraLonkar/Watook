package com.watook.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Request;
import com.watook.model.User;
import com.watook.v1.dao.RequestDao;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDao requestDao;
	
	 @Override
	  public Request saveRequest(Request Request){
		   
		 return requestDao.saveRequest(Request);
		 
	  }
	 
	 @Override
	  public List<User> list(String userid){
		   
		 return requestDao.list(userid);
		 
	  }
}
