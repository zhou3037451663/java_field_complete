package test;

import java.lang.reflect.Field;

/**
 * @Author: Mr.Zhou
 * @Date 2020/2/22
 * @Explain:
 */
public class Test1 {
    public static void main(String[] args) {
        try {
            Class c1=Class.forName("demo.Student");//获取对象
            Field field=c1.getDeclaredField("school");//调用对象中school变量
            field.setAccessible(true);//设置私有化的方法为public
            Object o = c1.newInstance();//创建一个实例化对像
            field.set(o,"张三");//调用field.set对象相当于调用student中的setSchool方法，并将实例化后的对象传入进入，并传入参数
            Object o1 = field.get(o);//调用field.get方法相当于调用student中的getSchool方法,并将实例化后的对象传入进入
            System.out.println(o1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
