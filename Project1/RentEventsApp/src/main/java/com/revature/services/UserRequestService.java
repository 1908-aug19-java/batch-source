package com.revature.services;

import java.util.List;

import com.revature.daos.UserRequestDAO;
import com.revature.daos.methods.UserRequestDAOMethods;
import com.revature.model.UserRequest;

public class UserRequestService {
	
	UserRequestDAO srdao = new UserRequestDAOMethods();
	
	public List<UserRequest> getAllRequests() {
		return srdao.getUserRequests();
		
	}
	
	public int createUserRequest(UserRequest sr) {
		return srdao.createUserRequest(sr);
		
		}
	
	public int deleteUserRequestById(int id) {
		return srdao.deleteUserRequestById(id);
		
	}
	
	public List<UserRequest> getAllRequestsByUserId(int userId){
		return srdao.getUserResquestsByUserId(userId);
	}
	
	public UserRequest getUserRequestById(int id ) {
		return srdao.getUserRequestById(id);
	}
	
	public int updateUserRequest(UserRequest sr) {
		return srdao.updateUserRequest(sr);
		
	}
	

}
