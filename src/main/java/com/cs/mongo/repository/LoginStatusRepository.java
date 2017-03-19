package com.cs.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.cs.mongo.model.LoginStatus;

@Repository
public interface LoginStatusRepository extends MongoRepository<LoginStatus , String>{ 

	LoginStatus findByUserName(String userName);
	Long deleteLoginStatusByUserName(String userName); 

}
