package com.innoqa.prices.service;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;

import java.util.List;
import java.util.Optional;

public interface BrandService_v1 {

    Brand_v1 save(Brand_v1 brand_v1);

    List<Brand_v1> findAll();

    Optional<Brand_v1> findById(Long id);

    Brand_v1 update(Brand_v1 brand_v1Updated);

    void deleteById(Long id);

    public void deleteAll();

    public Optional<Price_v1> innoqaTechTest_v1(Long brandID, Long productID, String applyDate);
}
