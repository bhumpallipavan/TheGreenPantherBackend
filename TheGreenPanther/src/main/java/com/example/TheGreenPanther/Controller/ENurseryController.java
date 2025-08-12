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

import com.example.TheGreenPanther.Entity.ENurseryEntity;
import com.example.TheGreenPanther.Projection.ProjectionEN;
import com.example.TheGreenPanther.Service.ENurseryService;


@RestController
@RequestMapping(value="/enursery")
public class ENurseryController {
	
	@Autowired
	private ENurseryService service;

	    @PostMapping("/item")
	    public ResponseEntity<ENurseryEntity> addItem(@RequestBody ENurseryEntity item) {
	    	
	        return ResponseEntity.ok(service.addItem(item));
	    }

	    @PostMapping("/items")
	    public ResponseEntity<List<ENurseryEntity>> addItems(@RequestBody List<ENurseryEntity> items) {
	        return ResponseEntity.ok(service.addItems(items));
	    }
	    
	    //get
	    
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<ENurseryEntity> getById(@PathVariable String id)
	    {
	    	return ResponseEntity.ok(service.findById(id));
	    }
	    
	    @GetMapping("/customid/{customid}")
	    public ResponseEntity<ENurseryEntity> getByCustomId(@PathVariable String customid)
	    {
	    	return ResponseEntity.ok(service.findByCustomId(customid.toUpperCase()));
	    }
	    
	    
	    @GetMapping("/search")
	    public ResponseEntity<List<ENurseryEntity>> getBySearchWord(@RequestParam String query)
	    {
	    	return ResponseEntity.ok(service.getBySearchWord(query));
	    }
	    
	    @GetMapping("/category/{category}")
	    public ResponseEntity<List<ENurseryEntity>> getByCategory(@PathVariable String category)
	    {
	    	return ResponseEntity.ok(service.getByCategory(category));
	    }
	    
	    @GetMapping("/featured")
	    public ResponseEntity<List<ENurseryEntity>> getNByCategory(@RequestParam int n, @RequestParam String category)
	    {
	    	return ResponseEntity.ok(service.getNByCategory(n, category));
	    }
	    
	    @GetMapping("/featuredall")
	    public ResponseEntity<List<ENurseryEntity>> getNALLCategory(@RequestParam int n)
	    {
	    	return ResponseEntity.ok(service.getNAllCategory(n,0));
	    }
	    @GetMapping("/pagination")
	    public ResponseEntity<List<ENurseryEntity>> getNALLCategory(@RequestParam int size, @RequestParam int page)
	    {
	    	return ResponseEntity.ok(service.getNAllCategory(size,page));
	    }

	    
	    @GetMapping("/random")
	    public ResponseEntity<List<ENurseryEntity>> getRandomItems(@RequestParam int n) {
	        return ResponseEntity.ok(service.getRandomItems(n));
	    }

	    
	    //return only five attributes
		//id
		//customId
		//name
		//doYouKnow
		//coverImage
	    @GetMapping("/searchcustom")
	    public ResponseEntity<List<ProjectionEN>> getCustomBySearchWord(@RequestParam String query)
	    {
	    	return ResponseEntity.ok(service.getCustomBySearchWord(query));
	    }
	    
	    @GetMapping("/categorycustom/{category}")
	    public ResponseEntity<List<ProjectionEN>> getCustomByCategory(@PathVariable String category)
	    {
	    	return ResponseEntity.ok(service.getCustomByCategory(category));
	    }
	    
	    @GetMapping("/featuredcustom")
	    public ResponseEntity<List<ProjectionEN>> getCustomNByCategory(@RequestParam int n, @RequestParam String category)
	    {
	    	return ResponseEntity.ok(service.getCustomNByCategory(n, category));
	    }
	    
	    @GetMapping("/featuredallcustom")
	    public ResponseEntity<List<ProjectionEN>> getCustomNALLCategory(@RequestParam int n)
	    {
	    	return ResponseEntity.ok(service.getCustomNAllCategory(n,0));
	    }
	    @GetMapping("/paginationcustom")
	    public ResponseEntity<List<ProjectionEN>> getCustomNALLCategory(@RequestParam int size, @RequestParam int page)
	    {
	    	return ResponseEntity.ok(service.getCustomNAllCategory(size,page));
	    }

	    
	    @GetMapping("/randomcustom")
	    public ResponseEntity<List<ProjectionEN>> getCustomRandomItems(@RequestParam int n) {
	        return ResponseEntity.ok(service.getCustomRandomItems(n));
	    }
	    
	    //put
	    @PutMapping("/update/{id}")
	    public ResponseEntity<ENurseryEntity> updateDate(@PathVariable String id, @RequestBody ENurseryEntity updatedItem)
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
