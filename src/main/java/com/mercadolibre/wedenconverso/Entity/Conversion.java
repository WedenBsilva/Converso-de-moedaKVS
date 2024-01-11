package com.mercadolibre.wedenconverso.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "tb_conversions")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conversion {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double ammount;

    private String currencyFrom;

    private String currencyTo;

    private double convertedAmmount;

}
