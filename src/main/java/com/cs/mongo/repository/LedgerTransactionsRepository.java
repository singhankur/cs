package com.cs.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.LedgerTransactions;

@Repository
public interface LedgerTransactionsRepository extends MongoRepository<LedgerTransactions, String>{ 

	List<LedgerTransactions> findByLedgerid(String ledgerid);

}
