package com.mercadolibre.wedenconverso.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class SetRateDto {
    private Double rate;
}
