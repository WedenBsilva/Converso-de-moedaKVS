package com.mercadolibre.wedenconverso.router;

import com.mercadolibre.wedenconverso.DTO.ObjectValueDto;
import com.mercadolibre.wedenconverso.DTO.ResultDto;
import com.mercadolibre.wedenconverso.Entity.Conversion;
import com.mercadolibre.wedenconverso.usecases.ConversionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
//ok
public class ConversionRouter {

    final ConversionUseCase conversionUseCase;

    @Autowired
    public ConversionRouter(ConversionUseCase conversionUseCase) {
        this.conversionUseCase = conversionUseCase;
    }


    @PostMapping("/rate/{currency}/{rate}")
    public void setRate(@PathVariable String currency,@PathVariable double rate) throws Exception {

        ObjectValueDto ObjectValueDto =  new ObjectValueDto();
        ObjectValueDto.setValue(rate);

        conversionUseCase.setConversionParameters(currency, ObjectValueDto);
    }

    @GetMapping ("/convert/{ammount}/{from}/{to}")
    public ResultDto convert(@PathVariable double ammount, @PathVariable String from, @PathVariable String to) throws Exception {

        return conversionUseCase.convert(ammount, from, to);

    }

    @GetMapping("/find/last")
    public List<Conversion> findLastFiveConversions() {
        return conversionUseCase.getLastFiveConversionFromDataBase();
    }

}
