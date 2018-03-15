package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by liushaoshuai on 2018/3/15.
 */
public class ArrayListRemoveTest {

    public static void main(String[] args) {
        //1、删除arraylist 会引起索引越界异常，原因：arraylist是动态数组，当我们删除一个i元素时候，会把i后面的元素向前移动一个单位，导致list长度变小
        //2、还会可能会引起并发问题
        ArrayList<String> list = new ArrayList<String>();
        list.add("beijing");
        list.add("shanghai");
        list.add("shanghai");
        list.add("guangzhou");
        list.add("shenzhen");
        list.add("hangzhou");
        remove32(list, "shanghai");
    }

    private static void print(List<String> list){
        for (String item : list) {
            System.out.println("元素值：" + item);
        }
    }

    /*
    * 正确
    */
    public static void remove14(List<String> list, String target){
        for(int i = list.size() - 1; i >= 0; i--){
            String item = list.get(i);
            if(target.equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }

    /*
     * 正确
     */
    public static void remove22(ArrayList<String> list, String target) {
        final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);
        for (String item : cowList) {
            if (item.equals(target)) {
                cowList.remove(item);
            }
        }
        print(cowList);
    }

    /*
   * 正确
   * 建议使用iterator，删除效率比较高
   * 将arraylist转化成一个link
   * 1、安全
   * 2、高效
   */
    public static void remove32(List<String> list, String target){
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                iter.remove();
            }
        }
        print(list);
    }


    /*
    * 错误
    * 索引越界
    */
    public static void remove11(List<String> list, String target){
        int size = list.size();
        for(int i = 0; i < size; i++){
            String item = list.get(i);
            if(target.equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }
    /*
     * 错误
     * 索引越界
     */
    public static void remove12(List<String> list, String target){
        for(int i = 0; i < list.size(); i++){
            String item = list.get(i);
            if(target.equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }
    /*
     * 错误
     *
     */
    public static void remove13(List<String> list, String target){
        int size = list.size();
        for(int i = size - 1; i >= 0; i--){
            String item = list.get(i);
            if(target.equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }


    /*
     * 错误
     */
    public static void remove21(List<String> list, String target){
        for(String item : list){
            if(target.equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }



    /*
     * 错误
     */
    public static void remove31(List<String> list, String target){
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                list.remove(item);
            }
        }
        print(list);
    }


}