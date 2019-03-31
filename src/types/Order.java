package types;

public class Order {

    public static void main(String[] args) {
        method3(method1(), method2());
    }

    public static Integer method1() {
        System.out.println("2");
        return 2;
    }

    public static Integer method2() {
        System.out.println("1");
        return 1;
    }

    public static void method3(Integer x, Integer y) {
        System.out.println(x + y);
    }

}
