package com.innoqa.prices.service;

import com.innoqa.prices.model.Price_v2;

import java.util.List;
import java.util.Optional;

public interface PriceService_v2 {
    Price_v2 save(Price_v2 price_v2);

    List<Price_v2> findAll();

    Optional<Price_v2> findById(Long id);

    Price_v2 update(Price_v2 price_v2Updated);

    void deleteById(Long id);

    public void deleteAll();

    public Optional<Price_v2> innoqaTechTest_v2(Long brandID, Long productID, String applyDate);
}
