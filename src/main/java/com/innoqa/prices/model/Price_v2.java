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

@Entity
@ToString
@Table(name = "prices_2")
public class Price_v2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @Column(name = "CURR", nullable = false)
    private Currency eurCurr;

    @ManyToOne(optional = false, /*cascade = CascadeType.ALL, */fetch = FetchType.EAGER)
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "id", nullable = false)
    private Brand_v2 brandId;

}