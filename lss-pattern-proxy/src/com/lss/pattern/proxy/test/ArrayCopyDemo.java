package com.lss.pattern.proxy.test;

import java.util.Arrays;

/**
 * Created by liushaoshuai on 2018/3/30.
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        JDKUtilCopyArray();
    }

    private static void JDKUtilCopyArray(){
        char[] source = {'d','e','c','a','f','f','e','i','n','a','t','e','d'};
        char[] dest = Arrays.copyOfRange(source,1,6);
        System.out.println(new String(dest));
    }


    private void systemCopyArray(){
        char[] source = {'d','e','c','a','f','f','e','i','n','a','t','e','d'};
        char[] dest = new char[7];

        System.arraycopy(source,2,dest,1,7);
        System.out.println(new String(dest));
    }
}
