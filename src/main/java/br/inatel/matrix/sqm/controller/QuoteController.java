package br.inatel.matrix.sqm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.matrix.sqm.dto.StockDto;
import br.inatel.matrix.sqm.dto.StockQuotesDto;
import br.inatel.matrix.sqm.form.StockQuoteForm;
import br.inatel.matrix.sqm.model.Quote;
import br.inatel.matrix.sqm.repository.QuotesRepository;
import br.inatel.matrix.sqm.service.StockService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/quote")
@Slf4j
public class QuoteController {

	private QuotesRepository quotesRepository;
	private StockService stockService;
	
	@Autowired
	public QuoteController(QuotesRepository quotesRepository, StockService stockService) {
		this.quotesRepository = quotesRepository;
		this.stockService = stockService;
	}
	
	@GetMapping
	public ResponseEntity<?> listAllQuotes() {
		log.info("Procurando por todas as quotes");
		List<StockDto> stocks = stockService.getAllStocks();
		if(!stocks.isEmpty()) {	
			return ResponseEntity.ok(StockQuotesDto.convertToList(stocks, quotesRepository));
		}
		return ResponseEntity.status(404).body("There's no stocks on the database.");
	}
	
	@GetMapping("/{stockId}")
	public ResponseEntity<?> listQuotesOfAStock(@PathVariable String stockId) {
		log.info("Procurando a stock");
		StockDto stockDto = stockService.getStock(stockId);
		if(stockDto == null) {
			return ResponseEntity.status(404).body("Unable to find stock "+stockId);
		}
		List<Quote> quotes = quotesRepository.findByStockId(stockId);
		if(!quotes.isEmpty()) {		
			return ResponseEntity.ok(new StockQuotesDto(stockId, quotes));
		}
		return ResponseEntity.status(404).body("There's no quotes in the stock "+stockId);
	}
	
	@PostMapping
	public ResponseEntity<?> addStockQuote(@RequestBody StockQuoteForm form) {
		log.info("Criando uma quote para uma stock");
		StockDto stockDto = stockService.getStock(form.getId());
		if(stockDto == null) {
			return ResponseEntity.status(404).body("Unable to find stock" + form.getId());
		}
		List<Quote> quotes = form.toListQuote();
		quotesRepository.saveAll(quotes);
		return ResponseEntity.created(null).body(new StockQuotesDto(form.getId(), quotes));
	}
	
}