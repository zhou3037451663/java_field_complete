package test;

import demo.Person;
import demo.Student;

import java.lang.reflect.*;


/**
 * @Author: Mr.Zhou
 * @Date 2020/2/22
 * @Explain:
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            Class<Student> s1=(Class<Student>) Class.forName("demo.Student");
            System.out.println("获取本类完整路径:"+s1.getName());
            System.out.println("获取父类完整路径:"+s1.getSuperclass().getName());
            Class[] inter =s1.getInterfaces();
            for (int i = 0; i < inter.length; i++) {
                System.out.println("获取接口完整路径:"+inter[i].getName());
            }
            Constructor[] cons = s1.getConstructors();//返回该类所有公共构造函数
            for (int i = 0; i <cons.length ;i++) {
                Constructor con=cons[i];
                int modify=con.getModifiers();
                System.out.print(Modifier.toString(modify)+" ");//获取本类中所有公共构造函数修饰符
                String name = con.getName();
                System.out.print(name); //获取类的完整路径
                System.out.print("(");
                Class[] parameters = con.getParameterTypes();//获取有参构造列表
                if (parameters!=null&&parameters.length>0){
                    for (int j = 0; j <parameters.length ; j++) {
                        if (j<parameters.length-1){
                            System.out.print(parameters[j].getName()+" arg"+j+",");
                        }else{
                            System.out.print(parameters[j].getName()+" arg"+j);
                        }
                    }
                }
                System.out.println("){}");
            }
            System.out.println("--------------------------------------------");
            Method[] methods = s1.getMethods();//获取父类和子类方法
            for (int i = 0; i <methods.length ; i++) {
                Method method =methods[i];
                int modifiers = method.getModifiers();//获取访问权限
                System.out.print(Modifier.toString(modifiers)+" ");
                Class returnClass=method.getReturnType(); //获取方法的返回类型
                System.out.print(returnClass+" ");
                System.out.print(method.getName()+"(");//获取参数名
                Class[] parameters = method.getParameterTypes();//获取对象方法的参数类型
                if (parameters!=null&&parameters.length>0){
                    for (int j = 0; j <parameters.length ; j++) {
                        if (j<parameters.length-1){
                            System.out.print(parameters[j].getName()+" arg"+j+",");
                        }else{
                            System.out.print(parameters[j].getName()+" arg"+j);
                        }
                    }
                }
                System.out.print(")");
                Class[] exceptions = method.getExceptionTypes();//获取对象抛出的异常
                    if (exceptions!=null&&exceptions.length>0){
                        System.out.print("throws ");
                        for (int j = 0; j <exceptions.length ; j++) {
                            if (j<exceptions.length-1){
                                System.out.print(exceptions[j].getName()+",");
                            }else{
                                System.out.print(exceptions[j].getName());
                            }
                        }
                    }
                System.out.println("{}");
            }

            System.out.println("--------------------------------------------");
           // Field[] fields = s1.getFields();//获取方法的属性
            Field[] fields=s1.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field=fields[i];
                int modify=field.getModifiers();
                System.out.print(Modifier.toString(modify)+" ");
                Class type = field.getType();
                System.out.print(type.getName()+" ");
                String name = field.getName();
                System.out.println(name+";");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            Class c1=Class.forName("demo.Student");//获取类完整路径
            Method m1 = c1.getMethod("sayHello", String.class, int.class);//指定调用类中的哪一个方法，并输入该方法的的参数数据类型
            Object invoke = m1.invoke(c1.newInstance(), "张三", 19);//使用c1.newInstance()方法创建一个对象，并输入需要传入的参数
            System.out.println(invoke); //返回方法返回值 如果没有返回值则返回null
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        Class stu=Class.forName("demo.Student");
        Object objs=stu.newInstance();
        set(objs,"name",String.class,"李四");
        Object obj=get(objs,"name");
        System.out.println(obj);
    }

    /**
     *
     * @param obj 数据对象
     * @param name  传入的方法名
     * @param parameterType 传入的数据类型
     * @param parametr 传入的数据
     */
    public static void set(Object obj, String name, Class parameterType, Object parametr) {
        Class c = obj.getClass();
        try {
            Method method = c.getMethod("set"+changeName(name), parameterType);
            method.invoke(obj, parametr);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param obj 传入的数据对象
     * @param name 传入的方法名
     * @return
     */
    public static Object get(Object obj, String name) {
        Object object = null;
        Class c = obj.getClass();
        try {
            Method m = c.getMethod("get"+changeName(name), null);
            object = m.invoke(obj, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
    public static String changeName(String name){
        String n1=name.substring(0,1);//截取字符串第一个字符
        String n2=name.substring(1);
        return n1.toUpperCase()+n2.toLowerCase();
    }
}
