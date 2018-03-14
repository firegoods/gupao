package prototype.simple;

import java.util.ArrayList;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class ConcretePrototype implements Cloneable{

    private int age;

    private String name;

    public ArrayList<String> list = new ArrayList<String>();

    protected Object clone() throws CloneNotSupportedException {
        ConcretePrototype prototype = null;
        try{
            prototype = (ConcretePrototype)super.clone();
            prototype.list = (ArrayList)list.clone();

            //��¡�����ֽ����
            //�÷��䣬����ѭ��
        }catch(Exception e){

        }

        return prototype;
    }


    //������100������

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}

