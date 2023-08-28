package com.innoqa.prices.service.impl;

import com.innoqa.prices.exception.ResourceNotFoundException;
import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;
import com.innoqa.prices.repository.BrandRepository_v1;
import com.innoqa.prices.service.BrandService_v1;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class BrandService_v1Impl implements BrandService_v1 {

    @Autowired
    private BrandRepository_v1 brandRepository_v1;

    /**
     * @param brand_v1
     * @return
     */
    @Override
    public Brand_v1 save(Brand_v1 brand_v1) {
        Optional<Brand_v1> brand_v1Saved = brandRepository_v1.findById(brand_v1.getId());
        if(brand_v1Saved.isPresent()){
            throw new ResourceNotFoundException("The Brand_v1 Exists : " + brand_v1.getId());
        }
        Brand_v1 result = brandRepository_v1.save(brand_v1);
        return result;
    }

    @Override
    public List<Brand_v1> findAll() {
        return brandRepository_v1.findAll();
    }

    @Override
    public Optional<Brand_v1> findById(Long id) {
        return brandRepository_v1.findById(id);
    }

    @Override
    public Brand_v1 update(Brand_v1 brand_v1Updated) {
        return brandRepository_v1.save(brand_v1Updated);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository_v1.deleteById(id);
    }

    @Override
    public void deleteAll() {
        brandRepository_v1.deleteAll();
    }

    @SneakyThrows
    @Override
    //@Cacheable("innoqa-tech-test-v1")
    public Optional<Price_v1> innoqaTechTest_v1(Long brandID, Long productID, String applyDate) {

        Optional<Brand_v1> brand_v1 = brandRepository_v1.findById(brandID);
        if(brand_v1.isEmpty())
            return Optional.empty();

        Date paramDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse(applyDate);

        return brand_v1.get().getPriceV1s().stream()
                .filter(price_v1 -> (
                                price_v1.getProductId().equals(productID) &&
                                price_v1.getStartDate().before(paramDate) && price_v1.getEndDate().after(paramDate)))
                .max(Comparator.comparing(Price_v1::getPriority));

    }
}
