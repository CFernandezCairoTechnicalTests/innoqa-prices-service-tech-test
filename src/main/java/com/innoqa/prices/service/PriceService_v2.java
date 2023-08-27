package com.innoqa.prices.service;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;
import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.repository.PriceRepository_v2;
import com.innoqa.prices.repository.impl.PriceCustomRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService_v2 {
    @Autowired
    private PriceRepository_v2 priceRepository_v2;

    @SneakyThrows
    public Optional<Price_v2> getResult(Long brandID, Long productID, String applyDate) {

        List<Price_v2> prices_v2 = priceRepository_v2.findAllByproductId(productID);
        if(prices_v2.isEmpty())
            return Optional.empty();

        Date paramDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse(applyDate);

        return prices_v2.stream()
                .filter(price_v2 -> (
                        (price_v2.getBrandId().getId() == brandID) &&
                        price_v2.getStartDate().before(paramDate) && price_v2.getEndDate().after(paramDate)))
                .max(Comparator.comparing(Price_v2::getPriority));

    }
}
