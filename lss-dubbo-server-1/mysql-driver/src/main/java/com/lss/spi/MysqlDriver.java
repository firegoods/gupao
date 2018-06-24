package com.lss.spi;

public class MysqlDriver implements DataBaseDriver {
    public String connect(String host) {
        return "mysql driver host: "+ host;
    }
}
