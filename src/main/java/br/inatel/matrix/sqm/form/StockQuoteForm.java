package br.inatel.matrix.sqm.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import br.inatel.matrix.sqm.model.Quote;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@NotEmpty
public class StockQuoteForm {

	private String id;
	private Map<String, String> quotes;

	public StockQuoteForm(String id, Map<String, String> quotes) {
		this.id = id;
		this.quotes = quotes;
	}

	public List<Quote> toListQuote() {
		List<Quote> quoteList = new ArrayList<Quote>();
		for (String key : quotes.keySet()) {
			Quote quote = new Quote(LocalDate.parse(key), new BigDecimal(quotes.get(key)), id);
			quoteList.add(quote);
		}
		return quoteList;
	}

	public String getId() {
		return id;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}
	
	

}
