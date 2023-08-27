package com.innoqa.prices.repository;

import com.innoqa.prices.model.Brand_v2;
import com.innoqa.prices.model.Price_v2;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ResourceLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestEntityManager
public class PriceRepository_v2Test {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private BrandRepository_v2 brandRepository_v2;

    @Autowired
    private PriceRepository_v2 priceRepository_v2;

    private Brand_v2 brand_v2;

    @BeforeEach
    void setup() throws ParseException {

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
    @DisplayName("SAVE :: Price v2")
    @Test
    void testSavePrice_v2(){

        //given - dado o condición previa o configuración
        Price_v2 price_v2_5 = Price_v2.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(41)
                .productId(45455L)
                .priority(0)
                .price(435.50F)
                .eurCurr(Currency.getInstance("EUR"))
                .brandId(brand_v2)
                .build();

        //when - acción o el comportamiento que vamos a probar
        Price_v2 savedPrice_v2_5 = priceRepository_v2.save(price_v2_5);;

        //then - verificar la salida
        assertThat(savedPrice_v2_5).isNotNull();
        assertThat(savedPrice_v2_5.getId()).isNotNull();

    }

}