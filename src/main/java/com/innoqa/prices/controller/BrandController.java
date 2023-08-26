package com.innoqa.prices.controller;

import com.innoqa.prices.model.Price;
import com.innoqa.prices.model.PriceDTO;
import com.innoqa.prices.service.BrandService;
import com.innoqa.prices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/techtest")
    public ResponseEntity<PriceDTO> getResult(@RequestParam(required=true) Long brandID,
                                              @RequestParam(required=true) Long productID,
                                              @RequestParam(required=true) String applyDate) {

        Optional<Price> priceToApply = brandService.getResult(brandID, productID, applyDate);
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
