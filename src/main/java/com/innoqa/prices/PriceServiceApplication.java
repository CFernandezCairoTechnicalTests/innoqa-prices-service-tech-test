package com.innoqa.prices;

import com.innoqa.prices.model.Brand_v1;
import com.innoqa.prices.model.Price_v1;
import com.innoqa.prices.model.Brand_v2;
import com.innoqa.prices.model.Price_v2;
import com.innoqa.prices.repository.BrandRepository_v1;
import com.innoqa.prices.repository.PriceRepository_v1;
import com.innoqa.prices.repository.BrandRepository_v2;
import com.innoqa.prices.repository.PriceRepository_v2;
import com.innoqa.prices.service.BrandService_v1;
import com.innoqa.prices.service.PriceService_v2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableJpaAuditing //  Enabling JPA Auditing
public class PriceServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PriceServiceApplication.class, args);
	}

	@Autowired
	private BrandRepository_v1 brandRepository_v1;
	@Autowired
	private BrandRepository_v2 brandRepository_v2;

	@Autowired
	private PriceRepository_v1 priceRepository_v1;
	@Autowired
	private PriceRepository_v2 priceRepository_v2;


	@Autowired
	private PriceService_v2 priceService_v2;

	@Autowired
	private BrandService_v1 brandService_v1;

	@Override
	public void run(String...args) throws Exception {
		priceRepository_v1.deleteAll();
		brandRepository_v1.deleteAll();

		priceRepository_v2.deleteAll();
		brandRepository_v2.deleteAll();


		Brand_v2 brand_v2 = Brand_v2.builder()
				.name("ZARA")
				.build();
		brandRepository_v2.save(brand_v2);

		Price_v2 priceV22_1 = Price_v2.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(1) //1
				.productId(35455L)
				.priority(0) //0
				.price(35.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.brandId(brand_v2)
				.build();
		priceRepository_v2.save(priceV22_1);

		Price_v2 priceV22_2 = Price_v2.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-15:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-18:30:00"))
				.priceList(2)
				.productId(35455L)
				.priority(1) //1
				.price(25.45F)
				.eurCurr(Currency.getInstance("EUR"))
				.brandId(brand_v2)
				.build();
		priceRepository_v2.save(priceV22_2);

		Price_v2 priceV22_3 = Price_v2.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-11:30:00"))
				.priceList(3)
				.productId(35455L)
				.priority(1)
				.price(30.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.brandId(brand_v2)
				.build();
		priceRepository_v2.save(priceV22_3);

		Price_v2 priceV22_4 = Price_v2.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(4)
				.productId(35455L)
				.priority(1)
				.price(38.95F)
				.eurCurr(Currency.getInstance("EUR"))
				.brandId(brand_v2)
				.build();
		priceRepository_v2.save(priceV22_4);

		Price_v2 priceV221_5 = Price_v2.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(41)
				.productId(45455L)
				.priority(0)
				.price(435.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.brandId(brand_v2)
				.build();

		Price_v1 priceV11_1 = Price_v1.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(1) //1
				.productId(35455L)
				.priority(0) //0
				.price(35.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price_v1 priceV11_2 = Price_v1.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-15:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-18:30:00"))
				.priceList(2)
				.productId(35455L)
				.priority(1) //1
				.price(25.45F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price_v1 priceV11_3 = Price_v1.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-11:30:00"))
				.priceList(3)
				.productId(35455L)
				.priority(1)
				.price(30.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price_v1 priceV11_4 = Price_v1.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(4)
				.productId(35455L)
				.priority(1)
				.price(38.95F)
				.eurCurr(Currency.getInstance("EUR"))
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

		/*---------------------------------------*/
		Brand_v1 brand_v1 = Brand_v1.builder()
				.name("ZARA")
				.priceV1s(new ArrayList<>())
				.build();

		brand_v1.getPriceV1s().add(priceV11_1);
		brand_v1.getPriceV1s().add(priceV11_2);
		brand_v1.getPriceV1s().add(priceV11_3);
		brand_v1.getPriceV1s().add(priceV11_4);
		//brand.getPrices().add(price5);

		this.brandRepository_v1.save(brand_v1);
		/*---------------------------------------*/

		System.out.println("Display all BRANDS ...");
		brandRepository_v1.findAll().forEach(currentBrandV1 -> System.out.println(currentBrandV1.toString()));
		System.out.println("Display all PRICES ...");
		priceRepository_v2.findAll().forEach(currentPriceV2 -> System.out.println(currentPriceV2.toString()));

		System.out.println("Display all CUSTOM-PRICES ...");
		brandService_v1.getResult(brandRepository_v1.findAll().get(0).getId(), priceRepository_v2.findAll().get(0).getProductId(), "2020-06-14-15:30:00");

	}
}
