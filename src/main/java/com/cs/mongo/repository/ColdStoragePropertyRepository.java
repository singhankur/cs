package com.cs.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cs.mongo.model.ColdStorageProperty;



@Repository
public interface ColdStoragePropertyRepository extends MongoRepository<ColdStorageProperty ,String>{
	
	ColdStorageProperty findByYearorDate(String yearOrDate);
	
	List<ColdStorageProperty> findByYearorDateStartsWith(String date);

}
