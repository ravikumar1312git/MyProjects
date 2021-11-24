package com.myproj.usermanagement.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproj.usermanagement.entity.User;
import com.myproj.usermanagement.exception.UserNotFoundException;
import com.myproj.usermanagement.model.DepartmentWiseUserCount;
import com.myproj.usermanagement.repository.UserManagementRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	UserManagementRepository userRepo;
	
	public User addUser(User user) {
		return userRepo.save(user);
	}		
	
	public User getUser(Integer userId) {
		return userRepo.findById(userId).get();
	}
	
	public void deleteUser(Integer userId) throws UserNotFoundException {
		if(!userRepo.existsById(userId))
			throw new UserNotFoundException();
		userRepo.deleteById(userId);
	}
	
	public User updateUser(User user) {
		var usr=userRepo.findById(user.getUserId()).get();
		usr.setUserName(user.getUserName());
		usr.setDepartment(user.getDepartment());
		usr.setRole(user.getRole());
		usr.setLastLogin(user.getLastLogin());
		return userRepo.save(usr);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public List<DepartmentWiseUserCount> getDepartmentWiseUserCount()
	{
		return userRepo.findUserCountByDepartment();
	}
	
	public Map<String,List<User>> getUsersWithLogin(Date fromDate, Date toDate)
	{
		//this operation can be done with query as well, for learning purpose done used stream
		return userRepo.findAll().stream().filter(user->user.getLastLogin().after(fromDate) && user.getLastLogin().before(toDate))
		.sorted((user1,user2)->user1.getLastLogin().compareTo(user2.getLastLogin())).collect(Collectors.groupingBy(obj->obj.getRole()));
	}
}
