package factory.func;

import factory.Mengniu;
import factory.Milk;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class MengniuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Mengniu();
    }
}
