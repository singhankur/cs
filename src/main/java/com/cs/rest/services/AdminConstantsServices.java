package com.cs.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.mongo.model.AdminConstants;
import com.cs.mongo.repository.AdminConstantsRepository;

@Service
public class AdminConstantsServices {
	
	@Autowired
	AdminConstantsRepository adminConstantsRepository;

	public AdminConstants getAdminConstant(String year) {
		return adminConstantsRepository.findByYear(year);
	}

	public String setAdminConstant(AdminConstants adminConstants) {
		adminConstantsRepository.save(adminConstants);
		return "Admin Constant Successfully Saved";
	}

}
