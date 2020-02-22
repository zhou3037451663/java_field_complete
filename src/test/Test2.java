package test;

import java.lang.reflect.Array;

/**
 * @Author: Mr.Zhou
 * @Date 2020/2/22
 * @Explain:
 */
public class Test2 {
    public static void main(String[] args) {
        int[] item={1,2,3};
        Object o = Array.get(item, 1);
        System.out.println(o);
    }
}
