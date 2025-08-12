package com.example.TheGreenPanther.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TheGreenPanther.Entity.ArticlesEntity;


@Repository
public interface ArticlesRepo extends MongoRepository<ArticlesEntity, String> {
ArticlesEntity findByCustomId(String customid);
	
	@Query("{ 'searchWords': { $regex: ?0, $options: 'i' } }")
	List<ArticlesEntity> findBySearchWord(String word);
	
	@Query("{'customId':{ $regex:?0, $options:'i'}}")
	List<ArticlesEntity> findByCategory(String category);
	
	@Query("{ 'customId': { $regex: ?0, $options: 'i' } }")
	List<ArticlesEntity> findNByCategory(String category, Pageable pageable);
	
	@Aggregation(pipeline = {
		    "{ $sample: { size: ?0 } }"
		})
		List<ArticlesEntity> findRandom(int size);

}
