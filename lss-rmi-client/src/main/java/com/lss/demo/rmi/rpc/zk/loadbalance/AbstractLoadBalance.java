package com.lss.demo.rmi.rpc.zk.loadbalance;

import java.util.List;

public abstract class AbstractLoadBalance implements LoadBalance{

        public  String selectHost(List<String> resps){
            if (null==resps||resps.size()==0){
                return  null;
            }
            if (resps.size()==1){
                return resps.get(0);
            }
            return doSelect(resps);
        }

    protected abstract String doSelect(List<String> resps);
}
