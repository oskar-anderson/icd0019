package exceptions.price;

public class MyFileResource {

    private static Integer dataInMemory;
    private static boolean throwOnRead;

    public static Integer read() {
        if (throwOnRead) {
            throw new RuntimeException("can't read file");
        }

        return dataInMemory;
    }

    public static void setData(Integer data) {
        throwOnRead = false;
        dataInMemory = data;
    }

    public static void makeItThrowOnRead() {
        throwOnRead = true;
    }

}
