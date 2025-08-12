package com.example.TheGreenPanther.Entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection="articles")
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesEntity {
	
	@Id
	private String id;
	private String customId;
	private String heading;
	
	private String name;
	private String coverDescription;
	private List<String> matter;
	private List<String> socialmediaHandles;
	private List<String> images;
	private List<String> searchWords;
	
	
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date updatedAt;
	

}
