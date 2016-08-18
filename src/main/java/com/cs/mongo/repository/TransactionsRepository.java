package com.cs.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.Transactions;

@Repository
public interface TransactionsRepository extends MongoRepository<Transactions, String> {

	List<Transactions> findBySlipNumber(String slipNumber);
	List<Transactions> findByTransactionID(String id);
					   
}
