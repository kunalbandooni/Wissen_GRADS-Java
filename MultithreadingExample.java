import java.lang.management.MemoryUsage;

class Mythread {
    boolean f = false;
    int i = 1;

    // f = false - odd
    // f = true - even
    public synchronized void callOdd() {
        try {
            if (f)
                wait();
            System.out.println("value is (odd) : " + i);
            i++;
            f = true;
            notify();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public synchronized void callEven() {
        try {
            if (!f)
                wait();
            System.out.println("value is (even) : " + i);
            i++;
            f = false;
            notify();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Odd extends Thread {
    Mythread t;

    Odd(Mythread t) {
        this.t = t;
    }

    public void run() {
        int cnt = 1;
        while (cnt <= 10) {
            t.callOdd();
            cnt++;
        }
    }
}

class Even extends Thread {
    Mythread t;

    Even(Mythread t) {
        this.t = t;
    }

    public void run() {
        int cnt = 1;
        while (cnt <= 10) {
            t.callEven();
            cnt++;
        }
        // t.callEven();
    }
}

public class MultithreadingExample {
    public static void main(String[] args) {
        Mythread t = new Mythread();

        Odd o = new Odd(t);
        Even e = new Even(t);

        o.start(); // odd thread gets executed
        e.start(); // even thread gets executed
    }
}