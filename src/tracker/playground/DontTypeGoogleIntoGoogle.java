package tracker.playground;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class DontTypeGoogleIntoGoogle {

    public static void main1(String[] args) throws InterruptedException {
        createRunnable("no_future").run();
        Thread.sleep(5000);
    }

    public static Runnable createRunnable(String text) {
        return new Runnable() {

            static final AtomicInteger counter = new AtomicInteger();

            @Override
            public void run() {
                counter.getAndAdd(1);
                CompletableFuture.runAsync(this);
                System.out.println(Thread.currentThread().getName() + "---" + text + "---" + counter);
            }
        };
    }
}
