package br.inatel.matrix.sqm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.matrix.sqm.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Long> {
	List<Quote> findByStockId(String stockId);
}
