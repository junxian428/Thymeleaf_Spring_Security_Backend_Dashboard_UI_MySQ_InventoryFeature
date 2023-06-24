package com.example.erp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.erp.entity.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {}