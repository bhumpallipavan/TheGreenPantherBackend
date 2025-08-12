package com.example.TheGreenPanther.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TheGreenPanther.Entity.ArticlesEntity;
import com.example.TheGreenPanther.Service.ArticlesService;

@RestController
@RequestMapping(value="/articles")
public class ArticlesController {
	
	@Autowired
	public ArticlesService service;
	
	@PostMapping(value="/item")
	public ResponseEntity<ArticlesEntity> addItem(@RequestBody ArticlesEntity item)
	{
		return ResponseEntity.ok(service.addItem(item));
	}
	
	@PostMapping(value="/items")
	public ResponseEntity<List<ArticlesEntity>> addItem(@RequestBody List<ArticlesEntity> items)
	{
		return ResponseEntity.ok(service.addItems(items));
	}
	
	//get
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ArticlesEntity> getById(@PathVariable String id)
    {
    	return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping("/customid/{customid}")
    public ResponseEntity<ArticlesEntity> getByCustomId(@PathVariable String customid)
    {
    	return ResponseEntity.ok(service.findByCustomId(customid.toUpperCase()));
    }
    
    
    @GetMapping("/search")
    public ResponseEntity<List<ArticlesEntity>> getBySearchWord(@RequestParam String query)
    {
    	return ResponseEntity.ok(service.getBySearchWord(query));
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ArticlesEntity>> getByCategory(@PathVariable String category)
    {
    	return ResponseEntity.ok(service.getByCategory(category));
    }
    
    @GetMapping("/featured")
    public ResponseEntity<List<ArticlesEntity>> getNByCategory(@RequestParam int n, @RequestParam String category)
    {
    	return ResponseEntity.ok(service.getNByCategory(n, category));
    }
    
    @GetMapping("/featuredall")
    public ResponseEntity<List<ArticlesEntity>> getNALLCategory(@RequestParam int n)
    {
    	return ResponseEntity.ok(service.getNAllCategory(n,0));
    }
    @GetMapping("/pagination")
    public ResponseEntity<List<ArticlesEntity>> getNALLCategory(@RequestParam int size, @RequestParam int page)
    {
    	return ResponseEntity.ok(service.getNAllCategory(size,page));
    }

    
    @GetMapping("/random")
    public ResponseEntity<List<ArticlesEntity>> getRandomItems(@RequestParam int n) {
        return ResponseEntity.ok(service.getRandomItems(n));
    }

    
    //put
    @PutMapping("/update/{id}")
    public ResponseEntity<ArticlesEntity> updateDate(@PathVariable String id, @RequestBody ArticlesEntity updatedItem)
    {
    	return ResponseEntity.ok(service.updateItem(id, updatedItem));
    	
    }
    
    // DELETE API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok(" item deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Item with ID " + id + " not found.");
        }
    }
    


}
