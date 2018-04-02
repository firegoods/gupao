package com.lss.java.tutorial.basic;

/**
 * this is class that operator demo
 * Created by liushaoshuai on 2018/4/2.
 */
public class OperatorDemo implements Demo{

    public static void main(String[] args) {
        try {
            new OperatorDemo().test();
            simpleAssign();
            arithmetic();
            unaryOperator();
        }catch (Exception e){

        }


    }

    /**
     * 赋值操作符 "="
     */
    public static void simpleAssign(){
        int speed = 0;
        System.out.println("speed: " + speed);
    }

    /**
     * 算数运算符
     * +,-,*,/,%
     */
    public static void arithmetic(){

        int result = 1 + 2;
        //now result is 3
        System.out.println("1 + 3 = "+result);
        int originalResult = result;

        result = result - 1;
        //now result is 2
        System.out.println(originalResult + " - 1 =" + result);
        originalResult = result;

        result = result * 2;
        //now result is 4
        System.out.println(originalResult + " * 2 = " + result);
        originalResult = result;

        result = result / 2;
        // now result is 2
        System.out.println(originalResult + " / 2 = " + result);
        originalResult = result;

        result = result % 7;
        //now result is 2
        System.out.println(originalResult + " % 7 = " + result);
        originalResult = result;

        result +=1;
        // now result is 3  result +=1 equal result = result + 1
        System.out.println(originalResult + " +=1, result: " + result);
        originalResult = result;

        result -=1;
        // now result is 2  result -=1 equal result = result - 1
        System.out.println(originalResult + " -=1, result: " + result);
        originalResult = result;

        String firstResult = "this is ";
        String secondResult = " concatenated String";
        String thirdResult = firstResult + secondResult;
        System.out.println(thirdResult);



    }

    /**
     * 一元运算符
     * +,-,++,--,!
     */
    public static void unaryOperator(){
        int result = +1;
        // now result is 1，positive value
        System.out.println(result);

        result--;
        //now result is 0
        System.out.println(result);

        result++;
        //now result is 1
        System.out.println(result);

        result = -result;
        // now result is -1
        System.out.println(result);

        boolean success = false;
        // false
        System.out.println(success);
        //true
        System.out.println(!success);

    }
}
