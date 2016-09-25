package com.cs.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.Token;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {

}
