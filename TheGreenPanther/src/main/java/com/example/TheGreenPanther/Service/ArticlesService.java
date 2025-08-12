package com.example.TheGreenPanther.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.TheGreenPanther.Entity.ArticlesEntity;
import com.example.TheGreenPanther.Repository.ArticlesRepo;

@Service
public class ArticlesService {
	
	@Autowired
	public ArticlesRepo repo;
	
	public ArticlesEntity addItem(ArticlesEntity item)
	{
		return repo.save(item);
	}

	
	public List<ArticlesEntity> addItems(List<ArticlesEntity> items)
	{
		return repo.saveAll(items);
	}
	
	
	//get
    
    public ArticlesEntity findById(String id)
    {
    	return repo.findById(id).orElse(null);
    }
    
    public ArticlesEntity findByCustomId(String customid)
    {
    	return repo.findByCustomId(customid);
    }
    
    public List<ArticlesEntity> getBySearchWord(String word)
    {
    	return repo.findBySearchWord(word);
    }
    
    public List<ArticlesEntity> getByCategory(String category)
    {
    	String categoryLetter;

    	switch (category.toLowerCase()) {
    	    case "person":
    	        categoryLetter = "PN";
    	        break;
    	    case "plant":
    	        categoryLetter = "PL";
    	        break;
    	    default:
    	        categoryLetter = ""; // No match
    	        break;
    	}

    	return repo.findByCategory(categoryLetter);
    }
    
    
    public List<ArticlesEntity> getNByCategory(int n, String category)
    {
    	String categoryLetter;

    	switch (category.toLowerCase()) {
	    case "person":
	        categoryLetter = "PN";
	        break;
	    case "plant":
	        categoryLetter = "PL";
	        break;
	    default:
	        categoryLetter = ""; // No match
	        break;
	}


    	return repo.findNByCategory(categoryLetter, PageRequest.of(0, n));
    }
    
    public List<ArticlesEntity> getNAllCategory(int n, int page)
    {
    	List<ArticlesEntity> g1=new ArrayList<ArticlesEntity>();
    	List<ArticlesEntity> g2=new ArrayList<ArticlesEntity>();
    	g1.addAll(repo.findNByCategory("PN", PageRequest.of(page, n)));
    	g2.addAll(repo.findNByCategory("PL", PageRequest.of(page, n)));
    	List<ArticlesEntity> r=new ArrayList<ArticlesEntity>();
    	for(int i=0;i<n;i++)
    	{
    		if(i<g1.size())r.add(g1.get(i));
    		if(i<g2.size())r.add(g2.get(i));
    	}
    	return r;
    }
    
    public List<ArticlesEntity> getRandomItems(int n) {
        return repo.findRandom(n);
    }
    
    public ArticlesEntity updateItem(String id, ArticlesEntity updatedItem) {
        return repo.findById(id)
            .map(existingItem -> {
                existingItem.setCustomId(updatedItem.getCustomId());
                existingItem.setName(updatedItem.getName());
                existingItem.setMatter(updatedItem.getMatter());
                existingItem.setHeading(updatedItem.getHeading());
                existingItem.setCoverDescription(updatedItem.getCoverDescription());
                existingItem.setSocialmediaHandles(updatedItem.getSocialmediaHandles());
                existingItem.setImages(updatedItem.getImages());
                existingItem.setSearchWords(updatedItem.getSearchWords());

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
