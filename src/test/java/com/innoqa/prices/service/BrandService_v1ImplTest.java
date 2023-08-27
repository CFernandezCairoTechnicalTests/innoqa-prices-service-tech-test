package com.innoqa.prices.service;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;
import com.innoqa.prices.repository.BrandRepository_v1;
import com.innoqa.prices.repository.PriceRepository_v1;
import com.innoqa.prices.service.impl.BrandService_v1Impl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BrandService_v1ImplTest {

    @Mock
    private BrandRepository_v1 brandRepository_v1;

    @Mock
    private PriceRepository_v1 priceRepository_v1;

    @Autowired
    private ResourceLoader resourceLoader;

    @InjectMocks
    private BrandService_v1Impl brandService_v1;

    private Brand_v1 brand_v1;

    private Price_v1 price_v1;

    @SneakyThrows
    @BeforeEach
    void setup(){
        priceRepository_v1.deleteAll();
        brandRepository_v1.deleteAll();

        Price_v1 pricev1_1 = Price_v1.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(1) //1
                .productId(35455L)
                .priority(0) //0
                .price(35.50F)
                .eurCurr(Currency.getInstance("EUR"))
                .build();

        Price_v1 pricev1_2 = Price_v1.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-15:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-18:30:00"))
                .priceList(2)
                .productId(35455L)
                .priority(1) //1
                .price(25.45F)
                .eurCurr(Currency.getInstance("EUR"))
                .build();

        Price_v1 pricev1_3 = Price_v1.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-00:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-11:30:00"))
                .priceList(3)
                .productId(35455L)
                .priority(1)
                .price(30.50F)
                .eurCurr(Currency.getInstance("EUR"))
                .build();

        Price_v1 pricev1_4 = Price_v1.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .build();

        //---------------------------------------/
        brand_v1 = Brand_v1.builder()
                .name("ZARA")
                .priceV1s(new ArrayList<>())
                .build();

        brand_v1.getPriceV1s().add(pricev1_1);
        brand_v1.getPriceV1s().add(pricev1_2);
        brand_v1.getPriceV1s().add(pricev1_3);
        brand_v1.getPriceV1s().add(pricev1_4);

        this.brandRepository_v1.save(brand_v1);
        //---------------------------------------/
    }

    @DisplayName("SAVE ::  Brand_v1")
    @Test
    void testSaveBrand_v1(){
        //given
        given(brandRepository_v1.findById(brand_v1.getId()))
                .willReturn(Optional.empty());
        given(brandRepository_v1.save(brand_v1)).willReturn(brand_v1);

        //when
        Brand_v1 brand_v1Saved = brandService_v1.save(brand_v1);

        //then
        assertThat(brand_v1Saved).isNotNull();
    }

    //@Test
    void getResult() {
    }
}