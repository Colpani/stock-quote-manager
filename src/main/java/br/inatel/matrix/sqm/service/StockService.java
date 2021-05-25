package br.inatel.matrix.sqm.service;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.matrix.sqm.dto.StockDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockService {
	private RestTemplate restTemplate = new RestTemplate();

	@Cacheable(value="stocks")
	public List<StockDto> getAllStocks() {
		log.info("Buscando todas as stocks");
		StockDto[] stocks = restTemplate.getForObject("http://localhost:8080/stock/", StockDto[].class);
		return Arrays.asList(stocks);
	}
	
	@Cacheable(value="stock")
	public StockDto getStock(String stockId) {
		log.info("Buscando uma stock pelo id");
		String url = "http://localhost:8080/stock/" + stockId;
		return restTemplate.getForObject(url, StockDto.class);
	}

	public void register() {
		log.info("Registrando para receber notificação da API externa");
		JSONObject json = new JSONObject();
		json.put("host", "localhost");
		json.put("port", "8081");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
		restTemplate.postForObject("http://localhost:8080/notification/", entity, String.class);
	}
}
