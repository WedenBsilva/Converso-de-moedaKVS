package com.mercadolibre.wedenconverso.usecases;

import com.mercadolibre.wedenconverso.DTO.ObjectValueDto;
import com.mercadolibre.wedenconverso.DTO.ResultDto;
import com.mercadolibre.wedenconverso.Entity.Conversion;
import com.mercadolibre.wedenconverso.repository.Conversoesrepository;
import com.mercadolibre.json.exception.JsonException;
import com.mercadolibre.kvsclient.exceptions.KvsException;
import com.mercadolibre.wedenconverso.repository.conversionDataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversionUseCase {


    conversionDataBaseRepository conversionDataBaseRepository;
    final Conversoesrepository kvsRepository;

    @Autowired
    public ConversionUseCase(Conversoesrepository kvsRepository, conversionDataBaseRepository conversionDataBaseRepository) {
        this.kvsRepository = kvsRepository;
        this.conversionDataBaseRepository = conversionDataBaseRepository;
    }

    public void setConversionParameters(String key, ObjectValueDto obj) throws Exception {
        kvsRepository.save(key, obj);
    }

    public double getConversion(String currency) throws KvsException, JsonException {
        return kvsRepository.retrieve(currency).getValue();


    }

    public ResultDto convert(double ammount, String from, String to) throws JsonException, KvsException {
        double originCurrencyValue = getConversion(from);
        double currencyValue = getConversion(to);

        double dollarAmmount = (ammount / originCurrencyValue);


        double ammoutConverter = (dollarAmmount * currencyValue);

        Conversion conversion = Conversion.builder()
                .currencyFrom(from)
                .currencyTo(to)
                .ammount(ammount)
                .convertedAmmount(ammoutConverter)
                .build();

        Optional<Conversion> conversionOptional = conversionDataBaseRepository.findByCurrencyFromAndCurrencyToAndAmmount(from, to, ammount);

        if (!conversionOptional.isPresent()) {
            conversionDataBaseRepository.save(conversion);
        }

        return ResultDto.builder()
                .result(ammoutConverter)
                .from(from)
                .to(to)
                .build();

    }

    public List<Conversion> getLastFiveConversionFromDataBase() {
        return conversionDataBaseRepository.findTop5ByOrderByIdDesc();
    }

}