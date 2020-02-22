package demo;

/**
 * @Author: Mr.Zhou
 * @Date 2020/2/22
 * @Explain:
 */
public class Student extends Person implements Info {
    private String school;

    public Student() {

    }

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    private Student(String name,int age) {
        super(name,age);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    public String sayHello(String name,int age){
      return "hello,my name is:"+name+",age:"+age;
    }
}
