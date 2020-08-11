package test;

/**
 * @author:jzz
 * @date:2020/7/22
 */
public class User {
    static int a = 10;

    public void add(){
        a ++;
        System.out.println(a);
    }

    public static void main(String[] args) {
        User u1 = new User();
        User u2 = new User();

        u1.add();
        u2.add();

    }

}
