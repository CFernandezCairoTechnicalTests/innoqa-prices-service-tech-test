package com.innoqa.prices.service.impl;

import com.innoqa.prices.exception.ResourceNotFoundException;
import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.repository.PriceRepository_v2;
import com.innoqa.prices.service.PriceService_v2;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService_v2Impl implements PriceService_v2 {
    @Autowired
    private PriceRepository_v2 priceRepository_v2;

    /**
     * @param price_v2
     * @return
     */
    @Override
    public Price_v2 save(Price_v2 price_v2) {
        Optional<Price_v2> price_v2Saved = priceRepository_v2.findById(price_v2.getId());
        if(price_v2Saved.isPresent()){
            throw new ResourceNotFoundException("The Price_v2 Exists : " + price_v2.getId());
        }
        Price_v2 result = priceRepository_v2.save(price_v2);
        return result;
    }

    /**
     * @return
     */
    @Override
    public List<Price_v2> findAll() {
        return priceRepository_v2.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Price_v2> findById(Long id) {
        return priceRepository_v2.findById(id);
    }

    /**
     * @param price_v2Updated
     * @return
     */
    @Override
    public Price_v2 update(Price_v2 price_v2Updated) {
        return priceRepository_v2.save(price_v2Updated);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        priceRepository_v2.deleteById(id);
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        priceRepository_v2.deleteAll();
    }

    @SneakyThrows
    @Override
    public Optional<Price_v2> innoqaTechTest_v2(Long brandID, Long productID, String applyDate) {

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
