package prototype.simple;

import java.util.ArrayList;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class CloneTarget2 implements Cloneable {

    public String name;
    public ArrayList<String> list = new ArrayList<String>();


    @Override
    public Object clone() throws CloneNotSupportedException {
        CloneTarget2 cloneTarget2 = (CloneTarget2)super.clone();
        try {
            cloneTarget2.list = (ArrayList<String>) list.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneTarget2;

    }
}
