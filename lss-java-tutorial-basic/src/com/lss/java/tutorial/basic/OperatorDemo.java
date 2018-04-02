package com.lss.java.tutorial.basic;

/**
 * this is class that operator demo
 * Created by liushaoshuai on 2018/4/2.
 */
public class OperatorDemo implements Demo{

    public static void main(String[] args) {
        try {
            //jdk 1.8之后接口里面可以写 public default 和 public static 有方法体方法
            new OperatorDemo().test();
            simpleAssign();
            arithmetic();
            //一元操作符
            unaryOperator();
            //++result result++
            ProPostOperator();
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

    /**
     *
     * 如果只是做简单的自增或者自减，两者没有区别
     * 如果是在表达式中，还是有区别的
     * 摘自官网：
     * The only difference is that the prefix version (++result) evaluates to the incremented value,
     * whereas the postfix version (result++) evaluates to the original value.
     *
     * ++result 得到的是一个自增值
     * result++ 得到一个原始值
     */
    public static void ProPostOperator(){
        System.out.println("====ProPostOperator===");
        int result = 1;
        result = ++result;
        //print 2
        System.out.println(result);
        result = result++;
        //print 3
        System.out.println(result);
    }
}
