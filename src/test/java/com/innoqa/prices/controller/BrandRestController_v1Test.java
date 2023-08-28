package com.innoqa.prices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.innoqa.prices.repository.BrandRepository_v1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@TestPropertySource(locations = "classpath:tech-test-db-v1.properties")
//@Sql("/BrandRestController_v1Test.sql")
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class BrandRestController_v1Test {

    private static final Logger logger = LoggerFactory.getLogger(BrandRestController_v1Test.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @Autowired
    BrandRepository_v1 brandRepository_v1;

    @TestConfiguration
    static class TestConfigurationApp {
        @Bean
        ObjectMapper objectMapperPrettyPrinting() {
            return JsonMapper.builder()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .addModule(new JavaTimeModule())
                    .build();
        }
    }

    /**
     * Parameters:
     * 1. Long      - Brand ID.
     * 2. Long      - Product ID.
     * 3. String    - Apply Date.
     * 4. Float     - Expected price
     */
    private static Stream<Arguments> generatorOfParams() {
        return Stream.of(
                Arguments.of(1L, 35455L, "2020-06-14-10:00:00", 35.5),
                Arguments.of(1L, 35455L, "2020-06-14-16:00:00", 35.5),
                Arguments.of(1L, 35455L, "2020-06-14-21:00:00", 25.45),
                Arguments.of(1L, 35455L, "2020-06-15-10:00:00", 30.5),
                Arguments.of(1L, 35455L, "2020-06-16-21:00:00", 38.95)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "generatorOfParams")
    @DisplayName(value = "INNOQA TECH-TEST Controller v1")
    void testinnoqaTechTest_v1(Long brandID, Long productID, String applyDate, Object expectedPrice) throws Exception {

        MultiValueMap currentParams = new LinkedMultiValueMap();
        currentParams.add("brandID", brandID.toString());
        currentParams.add("productID", productID.toString());
        currentParams.add("applyDate", applyDate);


        mockMvc.perform(get(BrandController_v1.INNOQA_TECH_TEST_v1_RESOURCE + "/techtest").queryParams(currentParams))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(expectedPrice)))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}