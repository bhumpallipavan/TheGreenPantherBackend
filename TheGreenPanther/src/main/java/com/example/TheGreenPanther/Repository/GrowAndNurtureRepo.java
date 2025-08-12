package com.example.TheGreenPanther.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TheGreenPanther.Entity.GrowAndNurtureEntity;
import com.example.TheGreenPanther.Projection.ProjectionGN;

@Repository
public interface GrowAndNurtureRepo extends MongoRepository<GrowAndNurtureEntity, String> {
	
	GrowAndNurtureEntity findByCustomId(String customid);
	
	@Query("{ 'searchWords': { $regex: ?0, $options: 'i' } }")
	List<GrowAndNurtureEntity> findBySearchWord(String word);
	
	@Query("{'category':{ $regex:?0, $options:'i'}}")
	List<GrowAndNurtureEntity> findByCategory(String category);
	
	@Query("{ 'category': { $regex: ?0, $options: 'i' } }")
	List<GrowAndNurtureEntity> findNByCategory(String category, Pageable pageable);
	
	@Aggregation(pipeline = {
		    "{ $sample: { size: ?0 } }"
		})
		List<GrowAndNurtureEntity> findRandom(int size);
	
	
	// return only five attributes
	//id
	//customId
	//name
	//doYouKnow
	//coverImage
	
	@Query(value="{ 'searchWords': { $regex: ?0, $options: 'i' } }", fields="{'_id':1,'customId':1,'name':1,'doYouKnow':1,'coverImage':1}")
	List<ProjectionGN> findCustomBySearchWord(String word);
	
	@Query(value="{ 'category': { $regex: ?0, $options: 'i' } }", fields="{'_id':1,'customId':1,'name':1,'doYouKnow':1,'coverImage':1}")
	List<ProjectionGN> findCustomByCategory(String category);
	
	@Query(value="{ 'category': { $regex: ?0, $options: 'i' } }", fields="{'_id':1,'customId':1,'name':1,'doYouKnow':1,'coverImage':1}")
	List<ProjectionGN> findCustomNByCategory(String category, Pageable pageable);
	
	@Query(value = "{}",fields="{'_id':1,'customId':1,'name':1,'doYouKnow':1,'coverImage':1}")
		List<ProjectionGN> findCustomRandom( Pageable pageable);


}
