package com.cs.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cs.mongo.model.AdminUser;

@Repository
public interface UserRepository  extends MongoRepository<AdminUser, String>{

	AdminUser findByUserName(String userName);
	Long deleteAdminUserByUserName(String userName); 
}
