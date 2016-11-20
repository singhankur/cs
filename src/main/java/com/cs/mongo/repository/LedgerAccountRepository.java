package com.cs.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.cs.mongo.model.LedgerAccount;

@Repository
public interface LedgerAccountRepository extends MongoRepository<LedgerAccount, String>{
	
	//Ignore Case Search 
	//IgnoreCasefathersName
		List<LedgerAccount> findByFathersNameLikeIgnoreCase(String fatherName);
		List<LedgerAccount> findByNameLikeIgnoreCase(String name);
		List<LedgerAccount> findByAddressLikeIgnoreCase(String address);
		
		List<LedgerAccount> findByLedgeridLike(String ledger_id);
		List<LedgerAccount> findByMobileLike(String mobile);
		
		
		LedgerAccount findByLedgerid(String ledger_id);

}
