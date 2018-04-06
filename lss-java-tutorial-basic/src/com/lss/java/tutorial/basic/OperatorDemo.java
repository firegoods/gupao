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
            //:?,&&,||
            conditionalOperator();
            //instance of
            new OperatorDemo().instanceOfOperator();
            new OperatorDemo().bitOperator();
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
     * ++result 先得到一个加1值再执行语句
     * result++ 得到一个原始值，再执行语句，然后在加1
     */
    public static void ProPostOperator(){
        System.out.println("====ProPostOperator===");
        int result = 1;
        ++result;
        //print 2
        System.out.println(result);
        result++;
        //print 3
        System.out.println(result);

        //print 4
        System.out.println(++result);
        //print 4
        System.out.println(result++);
        //print 5
        System.out.println(result);
    }

    /**
     * 等于、关系和条件运算符
     */
    public static void conditionalOperator(){
        System.out.println("====conditionalOperator====");
        int value1 = 1;
        int value2 = 2;
        if ((value1 == 1) && (value2==2)) {
            System.out.println("(value1 == 1) && (value2==2)");
        }
        if ((value1 == 1) || (value2==2)){
            System.out.println("(value1 == 1) || (value2==2)");
        }

        System.out.println("====ternaryOperator====");

        boolean condition = true;
        int result;
        result = condition ? value1 : value2;
        System.out.println(" result = condition ? value1 : value2: "+result);


    }


    /**
     * 类型比较符
     *keep in mind that null is not an instance of anything.
     */
    public  void instanceOfOperator(){

        System.out.println("========instanceOfOperator=========");
        Parent obj1 = new Parent();
        Parent obj2 = new Child();
        //true
        System.out.println(obj1 instanceof Parent);
        //false
        System.out.println(obj1 instanceof MyInterface);
        //false
        System.out.println(obj1 instanceof Child);

        //true
        System.out.println(obj2 instanceof Parent);
        //true
        System.out.println(obj2 instanceof MyInterface);
        //true
        System.out.println(obj2 instanceof Child);

        System.out.println(null instanceof Object);
    }

    class Parent{}
    interface MyInterface{}
    class Child extends Parent implements MyInterface{}


    /**
     *
     *
     *（1）机器都是使用补码，运算也是使用补码运算。
     *（2）正数的原码补码反码都一样。
     *（3）补码与原码相互转换，其运算过程是相同。
     * [ +5 ] = 原码[ 0000 0101 ] = 反码[ 0000 0101 ] = 补码[ 0000 0101 ]
     * [ -5 ] = 原码[ 1000 0101 ] = 反码[ 1111 1010 ] = 补码[ 1111 1011 ]
     * bitwise bit shift
     * ~,
     * <<,>>,>>>
     * &,^,|
     *
     */
    public void bitOperator(){
        int value1 = 0b0;
        int value2 = 0xF;//0b01111

        int value3 = -0xF;//-0b01111

        int bitMask = 0x000F;
        int value4 = 0x2222;

        System.out.println("value1,value2,value3,value4: "+value1
                +","+value2
                +","+value3
                +","+value4);

        //-0b1
        System.out.println("~0b0: " + ~value1);
        //-0b10000
        System.out.println("~0xF: " + ~value2);

        //-15的原码：【1000 0000 0000 0000 0000 0000 0000 1111】
        //-15的反码：【1111 1111 1111 1111 1111 1111 1111 0000】
        //-15的补码：【1111 1111 1111 1111 1111 1111 1111 0001】

        //-15不带符号的右移一位：【0111 1111 1111 1111 1111 1111 1111 1000】  因为最高位是正数所以补码、反码、原码一样

        //-15带符号的右移一位：【1111 1111 1111 1111 1111 1111 1111 0000】
        //-15带符号的右移一位补码转原码  先转反码【0000 0000 0000 0000 0000 0000 0000 1111】 反码+1【1000 0000 0000 0000 0000 0000 0001 0000】
        System.out.println("-0xF>>1: " + (value3>>1));
        //0
        System.out.println("0b0<<1: " + (value1<<1));

        System.out.println("-0xF>>1: " + (value3>>1));

        System.out.println("-0xF<<1: " + (value3<<1));

        System.out.println("-0xF>>>1: " + (value3>>>1));
        System.out.println(0b01111111111111111111111111111000);

        //undo
        System.out.println("0x2222 & 0x000F = "+ (value4 & bitMask));










    }
}
