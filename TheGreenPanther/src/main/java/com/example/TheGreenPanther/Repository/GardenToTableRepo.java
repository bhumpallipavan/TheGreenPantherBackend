package com.example.TheGreenPanther.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TheGreenPanther.Entity.GardenToTableEntity;


@Repository
public interface GardenToTableRepo extends MongoRepository<GardenToTableEntity, String> {
	
GardenToTableEntity findByCustomId(String customid);
	
	@Query("{ 'hashtags': { $regex: ?0, $options: 'i' } }")
	List<GardenToTableEntity> findBySearchWord(String word);
	
	@Query("{ 'customId': { $regex: ?0, $options: 'i' } }")
	List<GardenToTableEntity> findByCategory(String letter);
	
	@Query("{ 'customId': { $regex: ?0, $options: 'i' } }")
	List<GardenToTableEntity> findNByCategory(String letter, Pageable pageable);
	
	@Aggregation(pipeline = {
		    "{ $sample: { size: ?0 } }"
		})
	List<GardenToTableEntity> findRandom(int size);


}
