package com.common.demo.entity;

import com.common.es.anno.Document;
import com.common.es.entity.EEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(indexName = "es_name")
public class EsBean extends EEntity {


    String id;


    @Override
    public String unique() {
        return id;
    }
}
