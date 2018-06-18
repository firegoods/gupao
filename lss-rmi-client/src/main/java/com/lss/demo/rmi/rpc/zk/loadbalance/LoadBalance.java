package com.lss.demo.rmi.rpc.zk.loadbalance;

import java.util.List;

public interface LoadBalance  {

    public String selectHost(List<String> resps);

}
