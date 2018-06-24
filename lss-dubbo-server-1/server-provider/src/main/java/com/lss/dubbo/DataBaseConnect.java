package com.lss.dubbo;

import com.lss.spi.DataBaseDriver;

import java.util.ServiceLoader;

public class DataBaseConnect {

    public static void main(String[] args) {
        ServiceLoader<DataBaseDriver> serviceLoader = ServiceLoader.load(DataBaseDriver.class);
        for (DataBaseDriver dataBaseDriver:serviceLoader){
            System.out.println(dataBaseDriver.connect("localhost"));
        }
    }
}
