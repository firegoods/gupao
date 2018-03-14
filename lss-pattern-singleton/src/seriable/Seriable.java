package seriable;

import java.io.Serializable;

/**
 * Created by liushaoshuai on 2018/3/12.
 */
//反序列化会导致单列被破坏
public class Seriable implements Serializable {

    //序列化就是把内存中的状态转换成字节码的形式
    //从而转换成一个IO流，写入到其他地方，比如：磁盘或者网络IO
    //内存中的状态给永久的保存了

    //反序列化就是把持久化的字节码内存转化成 IO流
    //通过IO流的读取，进而将读取的内容转化成java对象
    //在转换的过程中重新创建对象 new
    public final static Seriable INSTANCE = new Seriable();

    private Seriable(){

    }

    public static Seriable getInstance(){
        return INSTANCE;
    }

    private Object readResolve(){
        return INSTANCE;
    }



}
