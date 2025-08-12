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
@Document(collection="e_nursery")
@NoArgsConstructor
@AllArgsConstructor
public class ENurseryEntity {
	
	@Id
	private String id;
	private String customId;
	
	private String name;
	private Integer originalPrice;
	private Integer discount;
	private Integer finalPrice;
	
	private List<String> weight;
	private String description;
	private String shippingDetails;
	private String stock;
	
	private List<String> images;
	private String category;
	private List<String> searchWords;
	
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date updatedAt;

}
