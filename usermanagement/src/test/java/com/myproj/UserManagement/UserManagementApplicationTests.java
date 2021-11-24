package com.myproj.UserManagement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserManagementApplicationTests {

	@Autowired
	AbstractApplicationContext appContext;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		assertNotNull(this.appContext);
	}
	
	@Test
	void testGetAllUser() throws Exception
	{
		this.mockMvc.perform(get("/api/findalluser")).andExpect(status().isOk());
	}
	
	@Test
	void testGetDepartmentWiseUserCount() throws Exception
	{
		this.mockMvc.perform(get("/api/finddepartmentwisecount")).andExpect(status().isOk());
	}
	
	@Test
	void testDeleteUserWithNonExistingUser() throws Exception
	{
		this.mockMvc.perform(get("/api/deleteuser/{userid}",99999999)).andExpect(result->assertTrue(result.getResponse().getContentAsString().contains("User not found with given id!")));
	}

}
