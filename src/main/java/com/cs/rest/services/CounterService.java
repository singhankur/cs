package com.cs.rest.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cs.mongo.model.Counter;
 
@Service
public class CounterService {
  @Autowired private MongoOperations mongo;
   
  public int getNextSequence(String collectionName) {
    Counter counter = mongo.findAndModify(
      query(where("_id").is(collectionName)), 
      new Update().inc("seq", 1),
      options().returnNew(true),
      Counter.class);
       
    return counter.getSeq();
  }
  
  
	  
  
   /* For Increase of Counters 
    * 
    * db.counters.findAndModify({
    	query: { _id: "transactions" },
    	update: { $inc: { seq: 0 } },
    	new: true
		})
		
		db.counters.findAndModify({
    	query: { _id: "ledgerTransaction" },
    	update: { $inc: { seq: 0 } },
    	new: true
		})
		
		
		db.counters.findAndModify({
    	query: { _id: "ledgerAccount" },
    	update: { $inc: { seq: 0 } },
    	new: true
		})
		
		db.counters.findAndModify({
    	query: { _id: "VypariRandomSlipNumber" },
    	update: { $inc: { seq: 0 } },
    	new: true
		})
		
		
		
		
    * 
    */
  
  /*For Creation of Counters Default Values is 100
   * 
   * db.counters.insert({
  	_id: "transactions",
  	seq: 100
	})
	
	db.counters.insert({
  	_id: "ledgerAccount",
  	seq: 100
	})
	
	db.counters.insert({
  	_id: "ledgerTransaction",
  	seq: 100
	})
	
	 db.counters.insert({
  	_id: "VypariRandomSlipNumber",
  	seq: 100
	})
	
	db.counters.insert({
  	_id: "timeOutInMinutes",
  	seq: 100
	})
	
   */
}
