package timeProcessor;

public class TimeCheck {
    public long before;
    public long now;

    public void begin() {
        before = System.currentTimeMillis();
    }

    public void end() {
        now = System.currentTimeMillis();
    }

    public void duration() {
        long du = now - before;
        System.out.println("cost time:" + du + " mili seconds");
    }
}