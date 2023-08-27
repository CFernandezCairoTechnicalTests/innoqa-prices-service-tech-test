package com.innoqa.prices.service;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Brand_v2;
import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.repository.BrandRepository_v2;
import com.innoqa.prices.repository.PriceRepository_v2;
import com.innoqa.prices.service.impl.PriceService_v2Impl;
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
import java.util.Currency;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PriceService_v2ImplTest {

    @Mock
    private BrandRepository_v2 brandRepository_v2;

    @Mock
    private PriceRepository_v2 priceRepository_v2;

    @Autowired
    private ResourceLoader resourceLoader;

    @InjectMocks
    private PriceService_v2Impl priceService_v2;

    private Brand_v2 brand_v2;

    private Price_v2 price_v2;

    @SneakyThrows
    @BeforeEach
    void setup(){
        priceRepository_v2.deleteAll();
        brandRepository_v2.deleteAll();

        brand_v2 = Brand_v2.builder()
                .name("ZARA")
                .build();
        brandRepository_v2.save(brand_v2);

        Price_v2 price_v2_1 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(1) //1
                .productId(35455L)
                .priority(0) //0
                .price(35.50F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        priceRepository_v2.save(price_v2_1);

        Price_v2 price_v2_2 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-15:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-18:30:00"))
                .priceList(2)
                .productId(35455L)
                .priority(1) //1
                .price(25.45F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        priceRepository_v2.save(price_v2_2);

        Price_v2 price_v2_3 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-00:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-11:30:00"))
                .priceList(3)
                .productId(35455L)
                .priority(1)
                .price(30.50F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        priceRepository_v2.save(price_v2_3);

        Price_v2 price_v2_4 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        priceRepository_v2.save(price_v2_4);
    }

    @SneakyThrows
    @DisplayName("SAVE ::  Price_v2")
    @Test
    void testSavePrice_v2(){
        //given
        Price_v2 price_v2ForSave = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        given(priceRepository_v2.findById(price_v2ForSave.getId()))
                .willReturn(Optional.empty());
        given(priceRepository_v2.save(price_v2ForSave)).willReturn(price_v2ForSave);

        //when
        Price_v2 price_v2Saved = priceService_v2.save(price_v2ForSave);

        //then
        assertThat(price_v2Saved).isNotNull();
    }

    //@Test
    void getResult() {
    }
}