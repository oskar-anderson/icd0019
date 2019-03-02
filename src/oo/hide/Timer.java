package oo.hide;

public class Timer {

    private long start = System.currentTimeMillis();

    public String getPassedTime() {

        double difference = System.currentTimeMillis() - start;

        return String.format("%s sek",difference / 1000);
    }
}
