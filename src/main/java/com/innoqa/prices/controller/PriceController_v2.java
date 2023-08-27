package com.innoqa.prices.controller;

import com.innoqa.prices.model.PriceDTO;
import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.service.impl.PriceService_v2Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
public class PriceController_v2 {
    @Autowired
    private PriceService_v2Impl priceServiceV2;

    @GetMapping("/techtest")
    public ResponseEntity<PriceDTO> getResult(@RequestParam(required=true) Long brandID,
                                              @RequestParam(required=true) Long productID,
                                              @RequestParam(required=true) String applyDate) {

        Optional<Price_v2> priceToApply = priceServiceV2.getResult(brandID, productID, applyDate);
        if (priceToApply.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PriceDTO.builder()
                .brandId(brandID)
                .startDate(priceToApply.get().getStartDate())
                .endDate(priceToApply.get().getEndDate())
                .priceList(priceToApply.get().getPriceList())
                .productId(priceToApply.get().getProductId())
                .price(priceToApply.get().getPrice())
                .build());
    }
}
