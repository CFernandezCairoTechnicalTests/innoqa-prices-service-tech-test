package com.innoqa.prices.repository;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ResourceLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestEntityManager
public class BrandRepository_v1Test {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private BrandRepository_v1 brandRepository_v1;

    @Autowired
    private PriceRepository_v1 priceRepository_v1;

    private Brand_v1 brand_v1;

    @BeforeEach
    void setup() throws ParseException {

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

    @SneakyThrows
    @DisplayName("SAVE :: Brand v1")
    @Test
    void testSaveBrand_v1(){

        //given - dado o condición previa o configuración
        Brand_v1 brand_v1_1 = Brand_v1.builder()
                .name("ZARA1")
                .priceV1s(new ArrayList<>())
                .build();
        Price_v1 priceV11_5 = Price_v1.builder()
                .startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
                .endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
                .priceList(41)
                .productId(45455L)
                .priority(0)
                .price(435.50F)
                .eurCurr(Currency.getInstance("EUR"))
                .build();
        brand_v1_1.getPriceV1s().add(priceV11_5);

        //when - acción o el comportamiento que vamos a probar
        Brand_v1 savedBrandv1_1 = brandRepository_v1.save(brand_v1_1);

        //then - verificar la salida
        assertThat(savedBrandv1_1).isNotNull();
        assertThat(savedBrandv1_1.getId()).isNotNull();

    }

    @DisplayName("GET :: Brand v1 by ID")
    @Test
    void testGetBrandV1ByID(){
        //given - dado o condición previa o configuración
        // in forEach was saved brand_v1

        //when - comportamiento o accion que vamos a probar
        Brand_v1 localBrand_v1 = brandRepository_v1.findById(brand_v1.getId()).get();

        //then
        assertThat(localBrand_v1).isNotNull();
    }

    @DisplayName("UPDATE :: Brand_v1")
    @Test
    void testForUpdateBrand_v1(){
        //given - dado o condición previa o configuración
        Brand_v1 brand_v1_2ForUpdate = Brand_v1.builder()
                .name("ZARA1_2")
                .priceV1s(new ArrayList<>())
                .build();
        brandRepository_v1.save(brand_v1_2ForUpdate);

        //when
        Brand_v1 brand_v1_2Updated = brandRepository_v1.findById(brand_v1_2ForUpdate.getId()).get();
        brand_v1_2Updated.setName("ZARA1_2UPDATED");
        Brand_v1 brand_v1_2Result = brandRepository_v1.save(brand_v1_2Updated);

        //then
        assertThat(brand_v1_2Result.getName()).isEqualTo(brand_v1_2Updated.getName());
    }

    @DisplayName("GET :: All Brand_v1")
    @Test
    void testListBrand_v1(){
        //given
        Brand_v1 brand_v1_3ForList_1 = Brand_v1.builder()
                .name("ZARA1_3_ForList_1")
                .priceV1s(new ArrayList<>())
                .build();
        brandRepository_v1.save(brand_v1_3ForList_1);

        //when
        List<Brand_v1> brandV1List = brandRepository_v1.findAll();

        //then
        assertThat(brandV1List).isNotNull();
        assertThat(brandV1List.size()).isGreaterThan(1);
    }

    @DisplayName("REMOVE :: Brand_v1 by ID")
    @Test
    void testRemoveBrand_v1ByID(){
        //given - dado o condición previa o configuración
        Brand_v1 brand_v1_4ForRemove_1 = Brand_v1.builder()
                .name("ZARA1_3_ForRemove_1")
                .priceV1s(new ArrayList<>())
                .build();
        brand_v1_4ForRemove_1 = brandRepository_v1.save(brand_v1_4ForRemove_1);

        //when
        brandRepository_v1.deleteById(brand_v1_4ForRemove_1.getId());
        Optional<Brand_v1> chargingStationOptional = brandRepository_v1.findById(brand_v1_4ForRemove_1.getId());

        //then
        assertThat(chargingStationOptional).isEmpty();
    }
}