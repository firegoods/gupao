package prototype.deep;

import java.util.Date;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class Monkey {
    GlodBar glodBar = new GlodBar();

    private int height;
    private int weight;
    private Date birthday;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
