package prototype.deep;

/**
 * Created by liushaoshuai on 2018/3/14.
 */

import java.io.Serializable;

/**
 * 金箍棒
 */
public class GlodBar implements Serializable{

    int hight = 100;

    int weight = 200;

    public void grow(){
        this.hight = this.hight * 2;
        this.weight = this.weight * 2;
    }

    public void shrink(){
        this.hight = hight/2;
        this.weight = weight/2;
    }

}
