package com.demo.es;

import com.common.demo.entity.EsBean;
import com.common.es.client.EsClient;
import com.demo.TestBase;
import org.junit.Test;

public class EsTest extends TestBase {

    @Test
    public void test(){
        EsBean esBean=new EsBean();
        esBean.setId("1");
        EsClient.save(esBean);

    }
}
