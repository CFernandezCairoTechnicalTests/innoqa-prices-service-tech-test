package com.innoqa.prices.service;

import com.innoqa.prices.exception.ResourceNotFoundException;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceService_v2ImplTest {

    @Mock
    private BrandRepository_v2 brandRepository_v2;

    @Mock
    private PriceRepository_v2 priceRepository_v2;

    @InjectMocks
    private PriceService_v2Impl priceService_v2;

    @Autowired
    private ResourceLoader resourceLoader;

    private Brand_v2 brand_v2;

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

    @SneakyThrows
    @DisplayName("SAVE ::  Price_v2 with Throw Exception")
    @Test
    void savePrice_v2WithThrowException(){
        //given
        Price_v2 price_v2ForSave1 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        given(priceRepository_v2.findById(price_v2ForSave1.getId()))
                .willReturn(Optional.of(price_v2ForSave1));

        //when
        assertThrows(ResourceNotFoundException.class,() -> {
            priceService_v2.save(price_v2ForSave1);
        });

        //then
        verify(priceRepository_v2,never()).save(price_v2ForSave1);
    }

    @SneakyThrows
    @DisplayName("GET :: List all Price_v2")
    @Test
    void testListPrice_v2(){
        //given
        Price_v2 price_v2ForList = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        given(priceService_v2.findAll()).willReturn(List.of(price_v2ForList));

        //when
        List<Price_v2> allPrices_v2 = priceService_v2.findAll();

        //then
        assertThat(allPrices_v2).isNotNull();
        assertThat(allPrices_v2.size()).isGreaterThan(0);
    }

    @SneakyThrows
    @DisplayName("GET :: List empty Price_v2")
    @Test
    void testEmptyPrice_v2(){
        //given
        Price_v2 price_v2ForList2 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        given(priceRepository_v2.findAll()).willReturn(Collections.emptyList());

        //when
        List<Price_v2> allPrices_v2 = priceService_v2.findAll();

        //then
        assertThat(allPrices_v2).isEmpty();
        assertThat(allPrices_v2.size()).isZero();
    }

    @DisplayName("GET :: Price_v2 by ID")
    @Test
    void testGetPrice_v2ByID() throws ParseException {
        //given
        Price_v2 price_v2ForGetByID = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        given(priceRepository_v2.findById(0L)).willReturn(Optional.of(price_v2ForGetByID));

        //when
        Price_v2 price_v2Saved = priceService_v2.findById(price_v2ForGetByID.getId()).get();

        //then
        assertThat(price_v2Saved).isNotNull();
    }

    @SneakyThrows
    @DisplayName("UPDATE :: Price_v2")
    @Test
    void testForUpdatePrice_v2(){
        //given
        Price_v2 price_v2ForUpdate = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(4)
                .productId(35455L)
                .priority(1)
                .price(38.95F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();
        given(priceRepository_v2.save(price_v2ForUpdate)).willReturn(price_v2ForUpdate);
        price_v2ForUpdate.setPrice(676F);

        //when
        Price_v2 updatedPrice_v2  = priceService_v2.update(price_v2ForUpdate);

        //then
        assertThat(updatedPrice_v2.getPrice()).isEqualTo(price_v2ForUpdate.getPrice());
    }

    @DisplayName("REMOVE ::  Price_v2 by ID")
    @Test
    void testRemovePrice_v2ByID(){
        //given
        Long price_v2Id = 1L;
        willDoNothing().given(priceRepository_v2).deleteById(price_v2Id);

        //when
        priceService_v2.deleteById(price_v2Id);

        //then
        verify(priceRepository_v2,times(1)).deleteById(price_v2Id);
    }

}