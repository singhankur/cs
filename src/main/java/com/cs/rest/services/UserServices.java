package com.cs.rest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.mongo.model.AdminUser;
import com.cs.mongo.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepo;

	public String addAdminUser(AdminUser adminUser) {
		if(userRepo.findByUserName(adminUser.getUserName()) != null)
		return  "User Already Exists";
		
		userRepo.save(adminUser);
		return  "User Created Successfully";
	}

	public List<String> getAllUser(String session_id) {
		
		List<String> names = new ArrayList<String>();
		List<AdminUser> allUser = userRepo.findAll();
		
		for(AdminUser au : allUser){
			names.add(au.getUserName());
		}
		return names;
	}

	public String deleteUser(String userName, String session_id) {
		 userRepo.deleteAdminUserByUserName(userName);
		 return "User Deleted Successfully";
	}
	

}
