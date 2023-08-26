package com.innoqa.prices.service;

import com.innoqa.prices.model.Brand;
import com.innoqa.prices.model.Price;
import com.innoqa.prices.repository.BrandRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @SneakyThrows
    public Optional<Price> getResult(Long brandID, Long productID, String applyDate) {

        Optional<Brand> brand = brandRepository.findById(brandID);
        if(brand.isEmpty())
            return Optional.empty();

        Date paramDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse(applyDate);

        return brand.get().getPrices().stream()
                .filter(price -> (
                                price.getProductId().equals(productID) &&
                                price.getStartDate().before(paramDate) && price.getEndDate().after(paramDate)))
                .max(Comparator.comparing(Price::getPriority));

    }
}
