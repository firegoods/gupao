package com.lss.demo.rmi.rpc.zk.loadbalance;

import java.util.List;
import java.util.Random;

public class RandomLoanBalance extends AbstractLoadBalance {


    protected String doSelect(List<String> resps) {
        int len = resps.size();
        Random random = new Random();
        return resps.get(random.nextInt(len));
    }
}
