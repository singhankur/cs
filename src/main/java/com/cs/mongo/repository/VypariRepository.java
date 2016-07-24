package com.cs.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.Vyapari;

@Repository
public interface VypariRepository  extends MongoRepository<Vyapari, String>{
	List<Vyapari> findBySlipNumberLikeOrVypariNameLikeOrFatherNameLikeOrMobileLikeOrAddressLike(String slipNumber, String vypariName , String fatherName, String mobile, String address);
	List<Vyapari> findBySlipNumber(String slipNumber);
}
