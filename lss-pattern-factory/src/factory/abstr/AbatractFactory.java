package factory.abstr;

import factory.Milk;

/**
 * Created by liushaoshuai on 2018/3/13.
 */
public abstract class AbatractFactory {

    //公共的逻辑
    //方便统一管理

    public abstract Milk getYili();

    public abstract Milk getMengniu();

    /*public abstract Milk getXXX();*/
}
