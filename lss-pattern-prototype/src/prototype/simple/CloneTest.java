package prototype.simple;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class CloneTest {



    public static void main(String[] args) {

        ConcretePrototype cp = new ConcretePrototype();
        cp.setAge(18);
        cp.setName("Tom");

        //cp.list.add("Tom");

        try {
            ConcretePrototype copy = (ConcretePrototype)cp.clone();

            System.out.println(copy.list  == cp.list);
            System.out.println(copy.getAge() + "," + copy.getName() + copy.list.size());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //����һ���ֳɵĶ�����������������Ѿ����úõ�ֵ
        //����Ҫ�½�һ�����󣬲���Ҫ���½��Ķ���ֵ�����Ҹ�ֵ����Ҫ��֮ǰ��һģһ��


        //ConcretePrototype cp = new ConcretePrototype();
        //cp.setAge(18);

        //ConcretePrototype copy = new ConcretePrototype();
        //copy.setAge(cp.getAge());
        //copy.setName(cp.getName());
        //��ѭ�����÷��䣬ȷʵ���Եģ��������ܲ����ߣ�
        //�ֽ��븴��newInstance()

        //ConcretePrototype copy = cp;
        //ORM��ʱ�򾭳��õ���


        //�ܹ�ֱ�ӿ�����ʵ�����ݵ���������/ֻ֧��9��,�˴������������+String ǳ����
        //���
    }

}

