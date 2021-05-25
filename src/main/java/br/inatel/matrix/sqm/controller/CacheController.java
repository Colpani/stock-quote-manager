package br.inatel.matrix.sqm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.matrix.sqm.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/stockcache")
public class CacheController {
	
	@Autowired
	public CacheController(StockService stockService) {
		stockService.register();
	}
	
	@DeleteMapping
	@Transactional
	@Caching(evict = { @CacheEvict(value = "stocks", allEntries = true), @CacheEvict(value = "stock", allEntries = true) })
	public ResponseEntity<?> cleanCache() {
		log.info("Limpando a cache!");
		return ResponseEntity.status(204).build();		
	}
	
	
	
}
