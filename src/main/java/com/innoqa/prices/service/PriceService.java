package com.innoqa.prices.service;

import com.innoqa.prices.repository.impl.PriceCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceCustomRepository priceCustomRepository;

    public List<Object> getApplyPrices() {
        return priceCustomRepository.getApplyPrice();
    }
}
