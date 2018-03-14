package prototype.deep;

import java.io.*;
import java.util.Date;

/**
 * Created by liushaoshuai on 2018/3/14.
 */
public class GreatestSage extends Monkey implements Cloneable,Serializable {

    private GlodBar glodBar= null;

    public GreatestSage(){
        this.glodBar = new GlodBar();
        this.setHeight(120);
        this.setWeight(80);
        this.setBirthday(new Date());
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {

        GreatestSage greatestSage = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis =null;
        ObjectInputStream ois = null;


        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            greatestSage = (GreatestSage)ois.readObject();
            greatestSage.setBirthday(new Date());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null!=bos ){
                    bos.close();
                }
                if (null!=oos){
                    oos.close();
                }
                if (null!=bis){
                    bis.close();
                }
                if (null!=ois){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return greatestSage;
    }

    //变变变
    public void change(){
        try {
            GreatestSage copy = (GreatestSage)clone();
            copy.setBirthday(new Date());
            System.out.println("齐天大圣真身时间：" + this.getBirthday().getTime());
            System.out.println("齐天大圣分身时间："+copy.getBirthday().getTime());
            System.out.println("两个齐天大圣的是否一样" + (this==copy));
            System.out.println("两个齐天大圣的金箍棒是否一样" + (this.glodBar == copy.glodBar));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public GlodBar getGlodBar() {
        return glodBar;
    }

    public void setGlodBar(GlodBar glodBar) {
        this.glodBar = glodBar;
    }
}
