package com.mercadolibre.wedenconverso.repository;

import com.mercadolibre.wedenconverso.Entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface conversionDataBaseRepository extends JpaRepository <Conversion, Long> {

    List<Conversion> findTop5ByOrderByIdDesc();

    Optional<Conversion> findByCurrencyFromAndCurrencyToAndAmmount(String from, String to, double ammount);

}
