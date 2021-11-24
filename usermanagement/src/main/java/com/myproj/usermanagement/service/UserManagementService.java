package com.myproj.usermanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.myproj.usermanagement.entity.User;
import com.myproj.usermanagement.exception.UserNotFoundException;
import com.myproj.usermanagement.model.DepartmentWiseUserCount;

public interface UserManagementService {

	public User addUser(User user);
	
	public User getUser(Integer userId);
	
	public void deleteUser(Integer userId) throws UserNotFoundException;
	
	public User updateUser(User user);
	
	public List<User> getAllUsers();
	
	public List<DepartmentWiseUserCount> getDepartmentWiseUserCount();
	
	public Map<String,List<User>> getUsersWithLogin(Date fromDate, Date toDate);
}
