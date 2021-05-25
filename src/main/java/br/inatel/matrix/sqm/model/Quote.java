package br.inatel.matrix.sqm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "quote")
		
public class Quote {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stockId;
	private LocalDate date;
	private BigDecimal value;	
	
	public Quote() {}
	
	public Quote(LocalDate date, BigDecimal value, String stockId) {
		this.date = date;
		this.value = value;
		this.stockId = stockId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}