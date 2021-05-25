package br.inatel.matrix.sqm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.inatel.matrix.sqm.model.Quote;
import br.inatel.matrix.sqm.repository.QuotesRepository;

public class StockQuotesDto {
	private String id;
	private Map<String, String> quotes = new HashMap<>();
	
	public StockQuotesDto(String stockId, List<Quote> quotes) {
		this.id = stockId;
		this.quotes = convertQuotesToMap(quotes);
	}	

	public String getId() {
		return id;
	}
	
	public Map<String, String> getQuotes() {
		return quotes;
	}

	public static Map<String, String> convertQuotesToMap(List<Quote> quoteList) {
		Map<String,String> quotesMap = new HashMap<String, String>();
		quoteList.forEach(quote -> {
			LocalDate date = quote.getDate();
			BigDecimal value = quote.getValue();
			quotesMap.put(date.toString(), value.toString());
		});
		return quotesMap;
	}
	
	public static List<StockQuotesDto> convertToList(List<StockDto> stocks, QuotesRepository quoteRepository) {
		List<StockQuotesDto> stockQuotesDto = new ArrayList<StockQuotesDto>();		
		stocks.forEach(stock -> {
			List<Quote> quotes = quoteRepository.findByStockId(stock.getId());			
			stockQuotesDto.add(new StockQuotesDto(stock.getId(), quotes));
		});
		return stockQuotesDto;
	}
}
