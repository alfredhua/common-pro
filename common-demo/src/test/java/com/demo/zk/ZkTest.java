package com.demo.zk;

import com.common.zk.client.ZkClient;
import com.demo.TestBase;
import org.junit.Test;

public class ZkTest extends TestBase {

    @Test
    public void zk(){
        ZkClient.create("/aaa","/aaa");
    }
}
