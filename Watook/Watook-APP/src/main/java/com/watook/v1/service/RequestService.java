package com.watook.v1.service;

import java.util.List;

import com.watook.model.Request;
import com.watook.model.User;

public interface RequestService {

	Request saveRequest(Request Request);

	List<User> list(String userid);

}
