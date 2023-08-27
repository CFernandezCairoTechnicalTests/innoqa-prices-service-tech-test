package com.innoqa.prices.service;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;
import com.innoqa.prices.repository.BrandRepository_v1;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Service
public class BrandService_v1 {

    @Autowired
    private BrandRepository_v1 brandRepositoryV1;

    @SneakyThrows
    public Optional<Price_v1> getResult(Long brandID, Long productID, String applyDate) {

        Optional<Brand_v1> brand_v1 = brandRepositoryV1.findById(brandID);
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
