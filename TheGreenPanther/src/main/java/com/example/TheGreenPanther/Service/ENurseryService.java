package com.example.TheGreenPanther.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.TheGreenPanther.Entity.ENurseryEntity;
import com.example.TheGreenPanther.Projection.ProjectionEN;
import com.example.TheGreenPanther.Repository.ENurseryRepo;

@Service
public class ENurseryService {
	
	@Autowired
	private ENurseryRepo repository;
	
	
    public ENurseryEntity addItem(ENurseryEntity item) {
    	
        return repository.save(item);
    }

    // Save multiple items
    public List<ENurseryEntity> addItems(List<ENurseryEntity> items) {
        return repository.saveAll(items);
    }

    
    //get
    
    public ENurseryEntity findById(String id)
    {
    	return repository.findById(id).orElse(null);
    }
    
    public ENurseryEntity findByCustomId(String customid)
    {
    	return repository.findByCustomId(customid);
    }
    
    public List<ENurseryEntity> getBySearchWord(String word)
    {
    	return repository.findBySearchWord(word);
    }
    
    public List<ENurseryEntity> getByCategory(String category)
    {
    	return repository.findByCategory(category);
    }
    
    
    public List<ENurseryEntity> getNByCategory(int n, String category)
    {
    	return repository.findNByCategory(category, PageRequest.of(0, n));
    }
    
    public List<ENurseryEntity> getNAllCategory(int n, int page)
    {
    	List<ENurseryEntity> g1=new ArrayList<ENurseryEntity>();
    	List<ENurseryEntity> g2=new ArrayList<ENurseryEntity>();
    	List<ENurseryEntity> g3=new ArrayList<ENurseryEntity>();
    	List<ENurseryEntity> g4=new ArrayList<ENurseryEntity>();
    	List<ENurseryEntity> g5=new ArrayList<ENurseryEntity>();
    	List<ENurseryEntity> g6=new ArrayList<ENurseryEntity>();
    	g1.addAll(repository.findNByCategory("seed", PageRequest.of(page, n)));
    	g2.addAll(repository.findNByCategory("plant", PageRequest.of(page, n)));
    	g3.addAll(repository.findNByCategory("tool", PageRequest.of(page, n)));
    	g4.addAll(repository.findNByCategory("soil", PageRequest.of(page, n)));
    	g5.addAll(repository.findNByCategory("fertilizers", PageRequest.of(page, n)));
    	g6.addAll(repository.findNByCategory("accessories", PageRequest.of(page, n)));
    	List<ENurseryEntity> r=new ArrayList<ENurseryEntity>();
    	for(int i=0;i<n;i++)
    	{
    		if(i<g1.size())r.add(g1.get(i));
    		if(i<g2.size())r.add(g2.get(i));
    		if(i<g3.size())r.add(g3.get(i));
    		if(i<g4.size())r.add(g4.get(i));
    		if(i<g5.size())r.add(g5.get(i));
    		if(i<g6.size())r.add(g6.get(i));
    	}
    	return r;
    }
    
    public List<ENurseryEntity> getRandomItems(int n) {
        return repository.findRandom(n);
    }

    
    
  //return only five attributes
  		//id
  		//customId
  		//name
  		//doYouKnow
  		//coverImage
    
    public List<ProjectionEN> getCustomBySearchWord(String word)
    {
    	return repository.findCustomBySearchWord(word);
    }
    
    public List<ProjectionEN> getCustomByCategory(String category)
    {
    	return repository.findCustomByCategory(category);
    }
    
    
    public List<ProjectionEN> getCustomNByCategory(int n, String category)
    {
    	return repository.findCustomNByCategory(category, PageRequest.of(0, n));
    }
    
    public List<ProjectionEN> getCustomNAllCategory(int n, int page)
    {
    	List<ProjectionEN> g1=new ArrayList<ProjectionEN>();
    	List<ProjectionEN> g2=new ArrayList<ProjectionEN>();
    	List<ProjectionEN> g3=new ArrayList<ProjectionEN>();
    	List<ProjectionEN> g4=new ArrayList<ProjectionEN>();
    	List<ProjectionEN> g5=new ArrayList<ProjectionEN>();
    	List<ProjectionEN> g6=new ArrayList<ProjectionEN>();
    	g1.addAll(repository.findCustomNByCategory("seed", PageRequest.of(page, n)));
    	g2.addAll(repository.findCustomNByCategory("plant", PageRequest.of(page, n)));
    	g3.addAll(repository.findCustomNByCategory("tool", PageRequest.of(page, n)));
    	g4.addAll(repository.findCustomNByCategory("soil", PageRequest.of(page, n)));
    	g5.addAll(repository.findCustomNByCategory("fertilizer", PageRequest.of(page, n)));
    	g6.addAll(repository.findCustomNByCategory("accessories", PageRequest.of(page, n)));
    	List<ProjectionEN> r=new ArrayList<ProjectionEN>();
    	for(int i=0;i<n;i++)
    	{
    		if(i<g1.size())r.add(g1.get(i));
    		if(i<g2.size())r.add(g2.get(i));
    		if(i<g3.size())r.add(g3.get(i));
    		if(i<g4.size())r.add(g4.get(i));
    		if(i<g5.size())r.add(g5.get(i));
    		if(i<g6.size())r.add(g6.get(i));
    	}
    	return r;
    }
    
    public List<ProjectionEN> getCustomRandomItems(int n) {
    	long total = repository.count();
        Random random = new Random();
        List<ProjectionEN> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int skip = random.nextInt((int) total);
            List<ProjectionEN> page = repository.findCustomRandom(PageRequest.of(skip, 1));
            if (!page.isEmpty()) {
                results.add(page.get(0));
            }
        }

        return results;
    }
    
    
    public ENurseryEntity updateItem(String id, ENurseryEntity updatedItem) {
        return repository.findById(id)
            .map(existingItem -> {
                existingItem.setCustomId(updatedItem.getCustomId());
                existingItem.setName(updatedItem.getName());
                
                existingItem.setOriginalPrice(updatedItem.getOriginalPrice());
                existingItem.setDiscount(updatedItem.getDiscount());
                existingItem.setFinalPrice(updatedItem.getFinalPrice());
                
                existingItem.setDescription(updatedItem.getDescription());
                existingItem.setShippingDetails(updatedItem.getShippingDetails());

                existingItem.setStock(updatedItem.getStock());
                existingItem.setWeight(updatedItem.getWeight());

                existingItem.setImages(updatedItem.getImages());

                existingItem.setCategory(updatedItem.getCategory());
                existingItem.setSearchWords(updatedItem.getSearchWords());

                return repository.save(existingItem);
            })
            .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }
    
  //delete
  	public boolean deleteById(String id) {
  		 if (repository.existsById(id)) {
  	            repository.deleteById(id);
  	            return true;
  	        }
  		return false;
  	}
}
