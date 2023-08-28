package com.innoqa.prices.service;

import com.innoqa.prices.model.Price_v1;
import com.innoqa.prices.repository.PriceRepository_v1;
import com.innoqa.prices.service.impl.BrandService_v1Impl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"})
@Sql("/BrandService_v1TechTest.sql")
@AutoConfigureTestDatabase
class BrandService_v1TechTest {

    @Autowired
    BrandService_v1Impl brandService_v1;

    @Autowired
    PriceRepository_v1 priceRepository_v1;

    /**
     * Parameters:
     * 1. Long      - Brand ID.
     * 2. Long      - Product ID.
     * 3. String    - Apply Date.
     * 4. Float     - Expected price
     */
    private static Stream<Arguments> generatorOfParams() {
        return Stream.of(
                Arguments.of(11L, 35455L, "2020-06-14-10:00:00", 35.5F),
                Arguments.of(11L, 35455L, "2020-06-14-16:00:00", 35.5F),
                Arguments.of(11L, 35455L, "2020-06-14-21:00:00", 25.45F),
                Arguments.of(11L, 35455L, "2020-06-15-10:00:00", 30.5F),
                Arguments.of(11L, 35455L, "2020-06-16-21:00:00", 38.95F)
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource(value = "generatorOfParams")
    @DisplayName(value = "INNOQA TECH-TEST v1")
    void testinnoqaTechTest_v1(Long brandID, Long productID, String applyDate, Float expectedPrice) {

        Optional<Price_v1> result = brandService_v1.innoqaTechTest_v1(brandID, productID, applyDate);

        assertTrue(!result.isEmpty());
        assertTrue(result.get().getPrice().equals(expectedPrice));

        System.out.println(result.get());
    }

    @AfterEach
    void tearDown() {
        priceRepository_v1.deleteAll();
        brandService_v1.deleteAll();
    }

}