package com.watook.v1.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.watook.model.Request;

@Repository
public class RequestDaoImpl implements RequestDao {

	@Autowired
	DataSource dataSource;
	
	 @Override
	  public void saveRequest(Request request){
		   
		  
	  }
}
