package com.myproj.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproj.usermanagement.entity.User;
import com.myproj.usermanagement.model.DepartmentWiseUserCount;

public interface UserManagementRepository extends JpaRepository<User, Integer> {
	
	@Query(value="select u.department,count(*) as count from user u group by u.department", nativeQuery = true)
	public List<DepartmentWiseUserCount> findUserCountByDepartment();

}
	