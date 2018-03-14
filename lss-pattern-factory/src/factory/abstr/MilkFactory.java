package factory.abstr;

import factory.Mengniu;
import factory.Milk;
import factory.Yili;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class MilkFactory extends AbatractFactory {

    @Override
    public Milk getYili() {
        return new Yili();
    }

    @Override
    public Milk getMengniu() {
        return new Mengniu();
    }
}
