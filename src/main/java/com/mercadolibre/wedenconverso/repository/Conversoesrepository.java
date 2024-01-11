package com.mercadolibre.wedenconverso.repository;
import com.mercadolibre.wedenconverso.DTO.ObjectValueDto;
import com.mercadolibre.wedenconverso.Entity.Item;
import com.mercadolibre.json.exception.JsonException;
import com.mercadolibre.kvsclient.ContainerKvsLowLevelClient;
import com.mercadolibre.kvsclient.exceptions.KvsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class Conversoesrepository {

    @Autowired
    private ContainerKvsLowLevelClient kvs;


    public void save(String key, Object obj) {
        Item item = new Item();
        item.setKey(key);
        item.setValue(obj);
        try {
            kvs.save(item);
        } catch (KvsException e) {
            throw new RuntimeException("Erro ao tentar salvar os dados no KVS", e);
        }
    }

    public ObjectValueDto retrieve(String key) throws KvsException, JsonException {

        com.mercadolibre.kvsclient.item.Item itemKvs = kvs.get(key);

        if (!Optional.ofNullable(itemKvs).isPresent() || itemKvs.hasError()) {

            throw new RuntimeException("Falha ao tentar obter um valor no objeto informado");
        }

        return itemKvs.getValueAsObjectType(ObjectValueDto.class);


    }


}


