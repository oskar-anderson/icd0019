package types;

public class Overload {

    public static void main(String[] args) {
        add(1L, 2L);
        add(1, 2);
        add("1", "2");
    }

    public static Long add(Long x, Long y) {
        System.out.println("Adding longs");
        return x + y;
    }

    public static Integer add(Integer x, Integer y) {
        System.out.println("Adding integers");
        return x + y;
    }

    public static Long add(String x, String y) {
        System.out.println("Adding numbers from strings");
        return Long.parseLong(x) + Long.parseLong(y);
    }

}
