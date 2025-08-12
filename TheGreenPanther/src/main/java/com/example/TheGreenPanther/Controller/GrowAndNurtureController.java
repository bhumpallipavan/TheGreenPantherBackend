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

import com.example.TheGreenPanther.Entity.GrowAndNurtureEntity;
import com.example.TheGreenPanther.Projection.ProjectionGN;
import com.example.TheGreenPanther.Service.GrowAndNurtureService;

@RestController
@RequestMapping("/growandnurture")
public class GrowAndNurtureController {
	
	
	@Autowired
	private GrowAndNurtureService service;

	    @PostMapping("/item")
	    public ResponseEntity<String> addItem(@RequestBody GrowAndNurtureEntity item) {
	    	service.addItem(item);
	    	System.out.println(item);
	        return ResponseEntity.ok("Item added Successfully");
	    }

	    @PostMapping("/items")
	    public ResponseEntity<List<GrowAndNurtureEntity>> addItems(@RequestBody List<GrowAndNurtureEntity> items) {
	        return ResponseEntity.ok(service.addItems(items));
	    }

	    
	    //get
	    
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<GrowAndNurtureEntity> getById(@PathVariable String id)
	    {
	    	return ResponseEntity.ok(service.findById(id));
	    }
	    
	    @GetMapping("/customid/{customid}")
	    public ResponseEntity<GrowAndNurtureEntity> getByCustomId(@PathVariable String customid)
	    {
	    	return ResponseEntity.ok(service.findByCustomId(customid.toUpperCase()));
	    }
	    
	    
	    @GetMapping("/search")
	    public ResponseEntity<List<GrowAndNurtureEntity>> getBySearchWord(@RequestParam String query)
	    {
	    	return ResponseEntity.ok(service.getBySearchWord(query));
	    }
	    
	    @GetMapping("/category/{category}")
	    public ResponseEntity<List<GrowAndNurtureEntity>> getByCategory(@PathVariable String category)
	    {
	    	return ResponseEntity.ok(service.getByCategory(category));
	    }
	    
	    @GetMapping("/featured")
	    public ResponseEntity<List<GrowAndNurtureEntity>> getNByCategory(@RequestParam int n, @RequestParam String category)
	    {
	    	return ResponseEntity.ok(service.getNByCategory(n, category));
	    }
	    
	    @GetMapping("/featuredall")
	    public ResponseEntity<List<GrowAndNurtureEntity>> getNALLCategory(@RequestParam int n)
	    {
	    	return ResponseEntity.ok(service.getNAllCategory(n,0));
	    }
	    @GetMapping("/pagination")
	    public ResponseEntity<List<GrowAndNurtureEntity>> getNALLCategory(@RequestParam int size, @RequestParam int page)
	    {
	    	return ResponseEntity.ok(service.getNAllCategory(size,page));
	    }

	    
	    @GetMapping("/random")
	    public ResponseEntity<List<GrowAndNurtureEntity>> getRandomItems(@RequestParam int n) {
	        return ResponseEntity.ok(service.getRandomItems(n));
	    }

	    
	    //return only five attributes
		//id
		//customId
		//name
		//doYouKnow
		//coverImage
	    @GetMapping("/searchcustom")
	    public ResponseEntity<List<ProjectionGN>> getCustomBySearchWord(@RequestParam String query)
	    {
	    	return ResponseEntity.ok(service.getCustomBySearchWord(query));
	    }
	    
	    @GetMapping("/categorycustom/{category}")
	    public ResponseEntity<List<ProjectionGN>> getCustomByCategory(@PathVariable String category)
	    {
	    	return ResponseEntity.ok(service.getCustomByCategory(category));
	    }
	    
	    @GetMapping("/featuredcustom")
	    public ResponseEntity<List<ProjectionGN>> getCustomNByCategory(@RequestParam int n, @RequestParam String category)
	    {
	    	return ResponseEntity.ok(service.getCustomNByCategory(n, category));
	    }
	    
	    @GetMapping("/featuredallcustom")
	    public ResponseEntity<List<ProjectionGN>> getCustomNALLCategory(@RequestParam int n)
	    {
	    	return ResponseEntity.ok(service.getCustomNAllCategory(n,0));
	    }
	    @GetMapping("/paginationcustom")
	    public ResponseEntity<List<ProjectionGN>> getCustomNALLCategory(@RequestParam int size, @RequestParam int page)
	    {
	    	return ResponseEntity.ok(service.getCustomNAllCategory(size,page));
	    }

	    
	    @GetMapping("/randomcustom")
	    public ResponseEntity<List<ProjectionGN>> getCustomRandomItems(@RequestParam int n) {
	        return ResponseEntity.ok(service.getCustomRandomItems(n));
	    }
	    
	    
	    //put
	    @PutMapping("/update/{id}")
	    public ResponseEntity<GrowAndNurtureEntity> updateDate(@PathVariable String id, @RequestBody GrowAndNurtureEntity updatedItem)
	    {
	    	return ResponseEntity.ok(service.updateItem(id, updatedItem));
	    	
	    }
	    
	 // DELETE API
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteGrowNurture(@PathVariable String id) {
	        boolean deleted = service.deleteById(id);
	        if (deleted) {
	            return ResponseEntity.ok("Grow & Nurture item deleted successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Item with ID " + id + " not found.");
	        }
	    }
	    
	    
	    
}
