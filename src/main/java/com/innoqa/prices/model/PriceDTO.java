package com.innoqa.prices.model;

import lombok.*;

import javax.persistence.*;
import java.util.Currency;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@ToString
public class PriceDTO {

    private Date startDate;

    private Date endDate;

    private Integer priceList;

    private Long productId;

    private Long brandId;

    private Float price;

}
