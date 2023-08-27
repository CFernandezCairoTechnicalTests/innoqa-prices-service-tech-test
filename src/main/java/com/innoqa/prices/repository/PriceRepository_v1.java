package com.innoqa.prices.repository;

import com.innoqa.prices.model.Price_v1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository_v1 extends JpaRepository<Price_v1, Long> {
}
