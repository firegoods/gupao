package factory.func;

import factory.Milk;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public interface Factory {
    //提供一个统一的生产牛奶的标准，供各个生产线来生产产品
    Milk getMilk();
}
