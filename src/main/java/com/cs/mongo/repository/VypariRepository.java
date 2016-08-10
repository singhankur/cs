package com.cs.mongo.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cs.mongo.model.Vyapari;

@Repository
public interface VypariRepository  extends MongoRepository<Vyapari, String>{
	
 
	List<Vyapari> findBySlipNumberLikeOrNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike(String slipNumber, String name , String fatherName, String mobile, String address);
	List<Vyapari> findBySlipNumberLike(String slipNumber);
	List<Vyapari> findByNameLike(String name);
	List<Vyapari> findByFatherNameLike(String fatherName);
	List<Vyapari> findByMobileLike(String mobile);
	List<Vyapari> findByAddressLike(String address);
	
	List<Vyapari> findBySlipNumber(String slipNumber);
}
