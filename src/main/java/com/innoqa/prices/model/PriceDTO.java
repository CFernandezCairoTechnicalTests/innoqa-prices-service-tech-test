package com.innoqa.prices.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@ToString
public class PriceDTO implements Serializable {

    private Date startDate;

    private Date endDate;

    private Integer priceList;

    private Long productId;

    private Long brandId;

    private Float price;

}
