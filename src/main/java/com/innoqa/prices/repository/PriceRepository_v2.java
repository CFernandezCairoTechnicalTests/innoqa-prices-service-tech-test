package com.innoqa.prices.repository;

import com.innoqa.prices.model.Price_v2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriceRepository_v2 extends JpaRepository<Price_v2, Long> {
    List<Price_v2> findAllByproductId(Long productId);
}
