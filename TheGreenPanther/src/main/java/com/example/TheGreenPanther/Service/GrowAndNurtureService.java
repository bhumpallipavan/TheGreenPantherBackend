package com.example.TheGreenPanther.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.TheGreenPanther.Entity.GrowAndNurtureEntity;
import com.example.TheGreenPanther.Projection.ProjectionGN;
import com.example.TheGreenPanther.Repository.GrowAndNurtureRepo;


@Service
public class GrowAndNurtureService {

	@Autowired
	private GrowAndNurtureRepo repository;
	
	
    public GrowAndNurtureEntity addItem(GrowAndNurtureEntity item) {
    	
        return repository.save(item);
    }

    // Save multiple items
    public List<GrowAndNurtureEntity> addItems(List<GrowAndNurtureEntity> items) {
        return repository.saveAll(items);
    }
    
    
    //get 
    
    
    public GrowAndNurtureEntity findById(String id)
    {
    	return repository.findById(id).orElse(null);
    }
    
    public GrowAndNurtureEntity findByCustomId(String customid)
    {
    	return repository.findByCustomId(customid);
    }
    
    public List<GrowAndNurtureEntity> getBySearchWord(String word)
    {
    	return repository.findBySearchWord(word);
    }
    
    public List<GrowAndNurtureEntity> getByCategory(String category)
    {
    	return repository.findByCategory(category);
    }
    
    
    public List<GrowAndNurtureEntity> getNByCategory(int n, String category)
    {
    	return repository.findNByCategory(category, PageRequest.of(0, n));
    }
    
    public List<GrowAndNurtureEntity> getNAllCategory(int n, int page)
    {
    	List<GrowAndNurtureEntity> g1=new ArrayList<GrowAndNurtureEntity>();
    	List<GrowAndNurtureEntity> g2=new ArrayList<GrowAndNurtureEntity>();
    	List<GrowAndNurtureEntity> g3=new ArrayList<GrowAndNurtureEntity>();
    	g1.addAll(repository.findNByCategory("vegetable", PageRequest.of(page, n)));
    	g2.addAll(repository.findNByCategory("fruit", PageRequest.of(page, n)));
    	g3.addAll(repository.findNByCategory("leafy green", PageRequest.of(page, n)));
    	List<GrowAndNurtureEntity> r=new ArrayList<GrowAndNurtureEntity>();
    	for(int i=0;i<n;i++)
    	{
    		if(i<g1.size())r.add(g1.get(i));
    		if(i<g2.size())r.add(g2.get(i));
    		if(i<g3.size())r.add(g3.get(i));
    	}
    	return r;
    }
    
    public List<GrowAndNurtureEntity> getRandomItems(int n) {
        return repository.findRandom(n);
    }

    
    
  //return only five attributes
  		//id
  		//customId
  		//name
  		//doYouKnow
  		//coverImage
    
    public List<ProjectionGN> getCustomBySearchWord(String word)
    {
    	return repository.findCustomBySearchWord(word);
    }
    
    public List<ProjectionGN> getCustomByCategory(String category)
    {
    	return repository.findCustomByCategory(category);
    }
    
    
    public List<ProjectionGN> getCustomNByCategory(int n, String category)
    {
    	return repository.findCustomNByCategory(category, PageRequest.of(0, n));
    }
    
    public List<ProjectionGN> getCustomNAllCategory(int n, int page)
    {
    	List<ProjectionGN> g1=new ArrayList<ProjectionGN>();
    	List<ProjectionGN> g2=new ArrayList<ProjectionGN>();
    	List<ProjectionGN> g3=new ArrayList<ProjectionGN>();
    	g1.addAll(repository.findCustomNByCategory("vegetable", PageRequest.of(page, n)));
    	g2.addAll(repository.findCustomNByCategory("fruit", PageRequest.of(page, n)));
    	g3.addAll(repository.findCustomNByCategory("leafy green", PageRequest.of(page, n)));
    	List<ProjectionGN> r=new ArrayList<ProjectionGN>();
    	for(int i=0;i<n;i++)
    	{
    		if(i<g1.size())r.add(g1.get(i));
    		if(i<g2.size())r.add(g2.get(i));
    		if(i<g3.size())r.add(g3.get(i));
    	}
    	return r;
    }
    
    public List<ProjectionGN> getCustomRandomItems(int n) {
    	long total = repository.count();
        Random random = new Random();
        List<ProjectionGN> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int skip = random.nextInt((int) total);
            List<ProjectionGN> page = repository.findCustomRandom(PageRequest.of(skip, 1));
            if (!page.isEmpty()) {
                results.add(page.get(0));
            }
        }

        return results;
    }
    
    
    
    //put
    
    public GrowAndNurtureEntity updateItem(String id, GrowAndNurtureEntity updatedItem) {
        return repository.findById(id)
            .map(existingItem -> {
                existingItem.setCustomId(updatedItem.getCustomId());
                existingItem.setName(updatedItem.getName());
                existingItem.setCoverImage(updatedItem.getCoverImage());
                existingItem.setVideo(updatedItem.getVideo());
                existingItem.setDoYouKnow(updatedItem.getDoYouKnow());
                
                existingItem.setVarietiesText(updatedItem.getVarietiesText());
                existingItem.setVarietiesImages(updatedItem.getVarietiesImages());

                existingItem.setBestCondition(updatedItem.getBestCondition()); // Assuming BestCondition is an embedded object
                
                existingItem.setWhenToSow(updatedItem.getWhenToSow());

                existingItem.setGrowingGuide(updatedItem.getGrowingGuide());
                existingItem.setGrowingGuideImages(updatedItem.getGrowingGuideImages());

                existingItem.setHarvesting(updatedItem.getHarvesting());
                existingItem.setHarvestingImage(updatedItem.getHarvestingImage());

                existingItem.setCommonTroubles(updatedItem.getCommonTroubles());
                existingItem.setCommonTroublesImages(updatedItem.getCommonTroublesImages());

                existingItem.setQuickFacts(updatedItem.getQuickFacts());

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
