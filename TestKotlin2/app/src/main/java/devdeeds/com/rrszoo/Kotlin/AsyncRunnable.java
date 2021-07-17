package devdeeds.com.rrszoo.Kotlin;

import java.util.concurrent.atomic.AtomicReference;

public abstract class AsyncRunnable<T> {
    protected abstract void run(AtomicReference<T> notifier);

    protected final void finish(AtomicReference<T> notifier, T result) {
        synchronized (notifier) {
            notifier.set(result);
            notifier.notify();
        }
    }

    public static <T> T wait(AsyncRunnable<T> runnable) {
        final AtomicReference<T> notifier = new AtomicReference<>();

        // run the asynchronous code
        runnable.run(notifier);

        // wait for the asynchronous code to finish
        synchronized (notifier) {
            while (notifier.get() == null) {
                try {
                    notifier.wait();
                } catch (Exception ignore) {
                    System.out.println(ignore.toString());
                }
            }
        }

        // return the result of the asynchronous code
        return notifier.get();
    }
}