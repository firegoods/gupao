package factory.func;

import factory.Milk;
import factory.Yili;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public class YiliFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Yili();
    }
}
