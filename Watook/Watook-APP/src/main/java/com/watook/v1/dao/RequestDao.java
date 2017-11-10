package com.watook.v1.dao;

import java.util.List;

import com.watook.model.Request;
import com.watook.model.User;

public interface RequestDao {


	Request saveRequest(Request request);

	List<User> list(String userId);
         
}
