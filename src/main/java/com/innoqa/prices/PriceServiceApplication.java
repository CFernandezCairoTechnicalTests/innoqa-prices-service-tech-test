package com.innoqa.prices;

import com.innoqa.prices.model.Brand;
import com.innoqa.prices.model.Price;
import com.innoqa.prices.repository.BrandRepository;
import com.innoqa.prices.repository.PriceRepository;
import com.innoqa.prices.service.BrandService;
import com.innoqa.prices.service.PriceService;
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
	private BrandRepository brandRepository;

	@Autowired
	private PriceRepository priceRepository;

	//@Autowired
	//PriceCustomRepository priceCustomRepository;

	@Autowired
	private PriceService statisticService;

	@Autowired
	private BrandService brandService;

	@Override
	public void run(String...args) throws Exception {
		priceRepository.deleteAll();
		brandRepository.deleteAll();

		Brand brand = Brand.builder()
				.name("ZARA")
				.prices(new ArrayList<>())
				.build();

		Price price1 = Price.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(1) //1
				.productId(35455L)
				.priority(0) //0
				.price(35.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price price2 = Price.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-15:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-18:30:00"))
				.priceList(2)
				.productId(35455L)
				.priority(1) //1
				.price(25.45F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price price3 = Price.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-11:30:00"))
				.priceList(3)
				.productId(35455L)
				.priority(1)
				.price(30.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price price4 = Price.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-15-16:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(4)
				.productId(35455L)
				.priority(1)
				.price(38.95F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		Price price5 = Price.builder()
				.startDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-06-14-00:00:00"))
				.endDate(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").parse("2020-12-31-23:59:59"))
				.priceList(41)
				.productId(45455L)
				.priority(0)
				.price(435.50F)
				.eurCurr(Currency.getInstance("EUR"))
				.build();

		brand.getPrices().add(price1);
		brand.getPrices().add(price2);
		brand.getPrices().add(price3);
		brand.getPrices().add(price4);
		//brand.getPrices().add(price5);

		this.brandRepository.save(brand);

		System.out.println("Display all BRANDS ...");
		brandRepository.findAll().forEach(currentBrand -> System.out.println(currentBrand.toString()));
		System.out.println("Display all PRICES ...");
		priceRepository.findAll().forEach(currentPrice -> System.out.println(currentPrice.toString()));

		System.out.println("Display all CUSTOM-PRICES ...");
		brandService.getResult(brandRepository.findAll().get(0).getId(), priceRepository.findAll().get(0).getProductId(), "2020-06-14-15:30:00");

	}
}
