package com.innoqa.prices.controller;

import com.innoqa.prices.model.PriceDTO;
import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.service.impl.PriceService_v2Impl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PriceController_v2.INNOQA_TECH_TEST_v2_RESOURCE)
public class PriceController_v2 {

    public static final String INNOQA_TECH_TEST_v2_RESOURCE = "/api/v2";

    @Autowired
    private PriceService_v2Impl priceServiceV2;

    @Operation(summary = "Get Price to apply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the price",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Not found price",
                    content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/techtest")
    public ResponseEntity<PriceDTO> innoqaTechTest_v2(@Parameter(description = "Brand ID", required = true)   @RequestParam(required = true) Long brandID,
                                                      @Parameter(description = "Product ID", required = true) @RequestParam(required = true) Long productID,
                                                      @Parameter(description = "Apply Date", required = true) @RequestParam(required = true) String applyDate) {

        try {
            Optional<Price_v2> priceToApply = priceServiceV2.innoqaTechTest_v2(brandID, productID, applyDate);
            if (priceToApply.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(PriceDTO.builder()
                    .brandId(brandID)
                    .startDate(priceToApply.get().getStartDate())
                    .endDate(priceToApply.get().getEndDate())
                    .priceList(priceToApply.get().getPriceList())
                    .productId(priceToApply.get().getProductId())
                    .price(priceToApply.get().getPrice())
                    .build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
