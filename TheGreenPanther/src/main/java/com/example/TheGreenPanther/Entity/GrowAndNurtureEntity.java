package com.example.TheGreenPanther.Entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection="grow_and_nurture")
@NoArgsConstructor
@AllArgsConstructor
public class GrowAndNurtureEntity {
	
	@Id
	@JsonProperty("id")
	private String id;
	
	private String customId;
	
	private String name;
	private String coverImage;
	private String video;
	
	private String doYouKnow;
	private List<String> varietiesText;
	private List<String> varietiesImages;
	
	private BestCondition bestCondition;
	
	private String whenToSow;
	private String cropCalender;
	private List<String> growingGuide;
	private List<String> growingGuideImages;
	private String harvesting;
	private String harvestingImage;
	private List<String> commonTroubles;
	private List<String> commonTroublesImages;
	private List<String> quickFacts;
	private String category;
	private List<String> searchWords;
	
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date updatedAt;
	
	
	@Data
    public static class BestCondition {
        private String sunlight;
        private String water;
        private String soil;
    }

}
