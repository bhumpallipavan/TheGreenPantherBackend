package com.example.TheGreenPanther.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TheGreenPanther.Entity.ENurseryEntity;
import com.example.TheGreenPanther.Projection.ProjectionEN;

@Repository
public interface ENurseryRepo extends MongoRepository<ENurseryEntity, String> {

	
	ENurseryEntity findByCustomId(String customid);
	
	@Query("{ 'searchWords': { $regex: ?0, $options: 'i' } }")
	List<ENurseryEntity> findBySearchWord(String word);
	
	@Query("{'category':{ $regex:?0, $options:'i'}}")
	List<ENurseryEntity> findByCategory(String category);
	
	@Query("{ 'category': { $regex: ?0, $options: 'i' } }")
	List<ENurseryEntity> findNByCategory(String category, Pageable pageable);
	
	@Aggregation(pipeline = {
		    "{ $sample: { size: ?0 } }"
		})
	List<ENurseryEntity> findRandom(int size);
	
	
	// return only five attributes
	//id
	//customId
	//name
	//doYouKnow
	//coverImage
	
	@Query(value="{ 'searchWords': { $regex: ?0, $options: 'i' } }", fields="{'_id':1,'customId':1,'name':1,'description':1,'finalPrice':1}")
	List<ProjectionEN> findCustomBySearchWord(String word);
	
	@Query(value="{ 'category': { $regex: ?0, $options: 'i' } }", fields="{'_id':1,'customId':1,'name':1,'description':1,'finalPrice':1}")
	List<ProjectionEN> findCustomByCategory(String category);
	
	@Query(value="{ 'category': { $regex: ?0, $options: 'i' } }", fields="{'_id':1,'customId':1,'name':1,'description':1,'finalPrice':1}")
	List<ProjectionEN> findCustomNByCategory(String category, Pageable pageable);
	
	@Query(value = "{}",fields="{'_id':1,'customId':1,'name':1,'description':1,'finalPrice':1}")
		List<ProjectionEN> findCustomRandom( Pageable pageable);

}
