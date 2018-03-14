package test;

import seriable.Seriable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class SeriableTest {
    public static void main(String[] args) {

        Seriable s1 = null;
        Seriable s2 = Seriable.getInstance();

        try {
            FileOutputStream fos = new FileOutputStream("Seriable.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.close();
            fos.close();

            FileInputStream fis = new FileInputStream("Seriable.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (Seriable)ois.readObject();
            ois.close();;
            fis.close();
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1==s2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
