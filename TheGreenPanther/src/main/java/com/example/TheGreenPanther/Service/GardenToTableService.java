package com.example.TheGreenPanther.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.TheGreenPanther.Entity.GardenToTableEntity;
import com.example.TheGreenPanther.Repository.GardenToTableRepo;

@Service
public class GardenToTableService {
	
	
	@Autowired
	public GardenToTableRepo repo;
	
	
	public GardenToTableEntity addItem(GardenToTableEntity item)
	{
		return repo.save(item);
	}
    
	public List<GardenToTableEntity> addItems(List<GardenToTableEntity> items)
	{
		return repo.saveAll(items);
	}
	
	
	//get
    
    public GardenToTableEntity findById(String id)
    {
    	return repo.findById(id).orElse(null);
    }
    
    public GardenToTableEntity findByCustomId(String customid)
    {
    	return repo.findByCustomId(customid);
    }
    
    public List<GardenToTableEntity> getBySearchWord(String word)
    {
    	return repo.findBySearchWord(word);
    }
    
    public List<GardenToTableEntity> getByCategory(String category)
    {
    	String categoryLetter;

    	switch (category.toLowerCase()) {
    	    case "cooking":
    	        categoryLetter = "C";
    	        break;
    	    case "resin":
    	        categoryLetter = "R";
    	        break;
    	    case "lantern":
    	        categoryLetter = "L";
    	        break;
    	    case "masala":
    	        categoryLetter = "M";
    	        break;
    	    case "bouquet":
    	        categoryLetter = "B";
    	        break;
    	    case "pressed":
    	        categoryLetter = "P";
    	        break;
    	    case "preserving":
    	        categoryLetter = "V"; 
    	        break;
    	    default:
    	        categoryLetter = ""; // No match
    	        break;
    	}

    	return repo.findByCategory(categoryLetter);
    }
    
    
    public List<GardenToTableEntity> getNByCategory(int n, String category)
    {
    	String categoryLetter;

    	switch (category.toLowerCase()) {
    	    case "cooking":
    	        categoryLetter = "C";
    	        break;
    	    case "resin":
    	        categoryLetter = "R";
    	        break;
    	    case "lantern":
    	        categoryLetter = "L";
    	        break;
    	    case "masala":
    	        categoryLetter = "M";
    	        break;
    	    case "bouquet":
    	        categoryLetter = "B";
    	        break;
    	    case "pressed":
    	        categoryLetter = "P";
    	        break;
    	    case "preserving":
    	        categoryLetter = "V"; 
    	        break;
    	    default:
    	        categoryLetter = ""; // No match
    	        break;
    	}

    	return repo.findNByCategory(categoryLetter, PageRequest.of(0, n));
    }
    
    public List<GardenToTableEntity> getNAllCategory(int n, int page)
    {
    	List<GardenToTableEntity> g1=new ArrayList<GardenToTableEntity>();
    	List<GardenToTableEntity> g2=new ArrayList<GardenToTableEntity>();
    	List<GardenToTableEntity> g3=new ArrayList<GardenToTableEntity>();
    	List<GardenToTableEntity> g4=new ArrayList<GardenToTableEntity>();
    	List<GardenToTableEntity> g5=new ArrayList<GardenToTableEntity>();
    	List<GardenToTableEntity> g6=new ArrayList<GardenToTableEntity>();
    	List<GardenToTableEntity> g7=new ArrayList<GardenToTableEntity>();
    	g1.addAll(repo.findNByCategory("C", PageRequest.of(page, n)));
    	g2.addAll(repo.findNByCategory("R", PageRequest.of(page, n)));
    	g3.addAll(repo.findNByCategory("L", PageRequest.of(page, n)));
    	g4.addAll(repo.findNByCategory("M", PageRequest.of(page, n)));
    	g5.addAll(repo.findNByCategory("B", PageRequest.of(page, n)));
    	g6.addAll(repo.findNByCategory("P", PageRequest.of(page, n)));
    	g7.addAll(repo.findNByCategory("V", PageRequest.of(page, n)));
    	List<GardenToTableEntity> r=new ArrayList<GardenToTableEntity>();
    	for(int i=0;i<n;i++)
    	{
    		if(i<g1.size())r.add(g1.get(i));
    		if(i<g2.size())r.add(g2.get(i));
    		if(i<g3.size())r.add(g3.get(i));
    		if(i<g4.size())r.add(g4.get(i));
    		if(i<g5.size())r.add(g5.get(i));
    		if(i<g6.size())r.add(g6.get(i));
    		if(i<g7.size())r.add(g7.get(i));
    	}
    	return r;
    }
    
    public List<GardenToTableEntity> getRandomItems(int n) {
        return repo.findRandom(n);
    }
    
    
    public GardenToTableEntity updateItem(String id, GardenToTableEntity updatedItem) {
        return repo.findById(id)
            .map(existingItem -> {
                existingItem.setCustomId(updatedItem.getCustomId());
                existingItem.setName(updatedItem.getName());
                existingItem.setVideo(updatedItem.getVideo());
                
                existingItem.setDescription(updatedItem.getDescription());
                existingItem.setCategory(updatedItem.getCategory());
                existingItem.setHashtags(updatedItem.getHashtags());

                return repo.save(existingItem);
            })
            .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }
    
  //delete
  	public boolean deleteById(String id) {
  		 if (repo.existsById(id)) {
  	            repo.deleteById(id);
  	            return true;
  	        }
  		return false;
  	}

}
