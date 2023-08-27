package com.innoqa.prices.repository;

import com.innoqa.prices.model.Brand_v2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository_v2 extends JpaRepository<Brand_v2, Long>{

}
