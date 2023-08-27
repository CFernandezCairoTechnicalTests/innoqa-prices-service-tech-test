package com.innoqa.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableJpaAuditing //  Enabling JPA Auditing
public class PriceServiceApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(PriceServiceApplication.class, args);
	}

	/*@Autowired
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


		System.out.println("Display all BRANDS ...");
		brandRepository_v1.findAll().forEach(currentBrandV1 -> System.out.println(currentBrandV1.toString()));
		System.out.println("Display all PRICES ...");
		priceRepository_v2.findAll().forEach(currentPriceV2 -> System.out.println(currentPriceV2.toString()));

		System.out.println("Display all CUSTOM-PRICES ...");
		brandService_v1.getResult(brandRepository_v1.findAll().get(0).getId(), priceRepository_v2.findAll().get(0).getProductId(), "2020-06-14-15:30:00");

	}*/
}
