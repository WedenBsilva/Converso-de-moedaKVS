package com.mercadolibre.wedenconverso.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultDto {

    private double result;
    private String from;
    private String to;
}
