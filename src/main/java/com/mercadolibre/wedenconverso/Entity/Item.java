package com.mercadolibre.wedenconverso.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item extends com.mercadolibre.kvsclient.item.Item {

    private String key;
    private Object value;

}
