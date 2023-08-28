package com.innoqa.prices.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Tag(name = "", description = "Responce price object")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@ToString
public class PriceDTO implements Serializable {

    @Schema(name = "startDate",
            description = "fecha de inicio del rango de fechas en el que aplica el precio tarifa indicado", defaultValue = "", example = "2020-06-14-00.00.00 ")
    private Date startDate;

    @Schema(name = "endDate",
            description = "fecha final del rango de fechas en el que aplica el precio tarifa indicado", defaultValue = "", example = "2020-12-31-23.59.59")
    private Date endDate;

    @Schema(name = "priceList",
            description = "Identificador de la tarifa de precios aplicable", defaultValue = "", example = "3")
    private Integer priceList;

    @Schema(name = "productId",
            description = "Identificador código de producto", defaultValue = "", example = "35455")
    private Long productId;

    @Schema(name = "brandId",
            description = "foreign key de la cadena del grupo (1 = ZARA)", defaultValue = "", example = "1")
    private Long brandId;

    @Schema(name = "price",
            description = "precio final de venta", defaultValue = "", example = "38.95")
    private Float price;

}
