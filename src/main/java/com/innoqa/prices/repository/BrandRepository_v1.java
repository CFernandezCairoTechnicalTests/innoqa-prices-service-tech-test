package com.innoqa.prices.repository;

import com.innoqa.prices.model.Brand_v1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository_v1 extends JpaRepository<Brand_v1, Long>{

}
