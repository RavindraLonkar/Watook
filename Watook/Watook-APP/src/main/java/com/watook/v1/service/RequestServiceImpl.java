package com.watook.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Request;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDao requestDao;
	
	 @Override
	  public Request saveRequest(Request Request){
		   
		 return requestDao.saveRequest(Request);
		 
	  }
	 
}
