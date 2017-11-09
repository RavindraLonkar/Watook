package com.watook.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watook.model.Request;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDao requestDao;
	
	 @Override
	  public void saveRequest(Request Request){
		   
		 requestDao.saveRequest(Request);
	  }
	 
}
