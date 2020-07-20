package com.cts.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.stock.model.Stock;

public interface StockRepository extends  JpaRepository<Stock, String> {

}
