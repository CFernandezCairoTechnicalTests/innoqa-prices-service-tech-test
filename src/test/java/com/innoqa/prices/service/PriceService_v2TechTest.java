package com.innoqa.prices.service;

import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.repository.BrandRepository_v2;
import com.innoqa.prices.service.impl.PriceService_v2Impl;
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
@Sql("/PriceService_v2TechTest.sql")
@AutoConfigureTestDatabase
class PriceService_v2TechTest {

    @Autowired
    BrandRepository_v2 brandRepository_v2;

    @Autowired
    PriceService_v2Impl priceService_v2;

    /**
     * Parameters:
     * 1. Long      - Brand ID.
     * 2. Long      - Product ID.
     * 3. String    - Apply Date.
     * 4. Float     - Expected price
     */
    private static Stream<Arguments> generatorOfParams() {
        return Stream.of(
                Arguments.of(1L, 35455L, "2020-06-14-10:00:00", 35.5F),
                Arguments.of(1L, 35455L, "2020-06-14-16:00:00", 35.5F),
                Arguments.of(1L, 35455L, "2020-06-14-21:00:00", 25.45F),
                Arguments.of(1L, 35455L, "2020-06-15-10:00:00", 30.5F),
                Arguments.of(1L, 35455L, "2020-06-16-21:00:00", 38.95F)
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource(value = "generatorOfParams")
    @DisplayName(value = "INNOQA TECH-TEST v2")
    void testinnoqaTechTest_v2(Long brandID, Long productID, String applyDate, Float expectedPrice) {

        Optional<Price_v2> result = priceService_v2.innoqaTechTest_v2(brandID, productID, applyDate);

        assertTrue(!result.isEmpty());
        assertTrue(result.get().getPrice().equals(expectedPrice));

        System.out.println(result.get());
    }

    @AfterEach
    void tearDown() {
        priceService_v2.deleteAll();
        brandRepository_v2.deleteAll();
    }

}