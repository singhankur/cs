package com.cs.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.AdminConstants;

@Repository
public interface AdminConstantsRepository  extends MongoRepository<AdminConstants ,String>{
	
	AdminConstants findByYear(String year);
	

}
