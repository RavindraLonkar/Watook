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
	  public List<User> list(String userid,String requestStatus){
		   
		 return requestDao.list(userid,requestStatus);
		 
	  }

	@Override
	public Integer requestRating(String ratingId, String ratingTo) {
		return requestDao.requestRating(ratingId, ratingTo);
	}
}
