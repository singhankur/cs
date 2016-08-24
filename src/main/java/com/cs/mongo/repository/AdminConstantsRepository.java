package com.cs.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cs.mongo.model.AdminConstants;

public interface AdminConstantsRepository  extends MongoRepository<AdminConstants ,String>{
	
	AdminConstants findByYear(String year);

}
