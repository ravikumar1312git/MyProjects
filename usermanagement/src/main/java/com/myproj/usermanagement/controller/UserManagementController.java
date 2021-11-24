package com.myproj.usermanagement.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.usermanagement.entity.User;
import com.myproj.usermanagement.exception.UserNotFoundException;
import com.myproj.usermanagement.model.DepartmentWiseUserCount;
import com.myproj.usermanagement.service.UserManagementService;


@RestController
@RequestMapping("/api")
public class UserManagementController {
	
	@Autowired
	UserManagementService userService;
	
	@PostMapping(value="/adduser")
	public Integer addUser(@RequestBody User user)
	{
		return userService.addUser(user).getUserId();
	}
	
	@GetMapping(value="/finduser/{userid}")
	public User getUser(@PathVariable("userid") Integer userId)
	{
		return userService.getUser(userId);
	}
	
	@GetMapping(value="/deleteuser/{userid}")
	public String deleteUser(@PathVariable("userid") Integer userId)
	{
		try
		{
			userService.deleteUser(userId);
			return "Successfully Deleted";
		}
		catch(UserNotFoundException unfex)
		{
			return "Application Excepion : "+unfex.getMessage();
		}
		
	}
	
	@PostMapping(value="/updateuser")
	public String updateUser(@RequestBody User user)
	{
		userService.updateUser(user);
		return "Updated successfully";
	}
	
	@GetMapping(value="/findalluser")
	public List<User> getAllUser()
	{
		return userService.getAllUsers();
	}
	
	
	@GetMapping(value="/finddepartmentwisecount")
	public List<DepartmentWiseUserCount> getDepartmentWiseUserCount()
	{
		return userService.getDepartmentWiseUserCount();
	}
	
	
	@GetMapping(value="/finduserswithlogin")
	public Map<String,List<User>> getUsersWithLogin(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate)
	{
		return userService.getUsersWithLogin(fromDate, toDate);
	}
	
}
