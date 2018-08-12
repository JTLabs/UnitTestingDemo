package org.jtlabs.demo.unittesting.prepare;

import java.lang.management.ManagementFactory;

public class Deadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread1 acquired lock 1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("Thread1 acquired lock 2");
                }
            }
        });

        thread1.start();

        final Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread2 acquired lock 2");

                synchronized (lock1) {
                    System.out.println("Thread2 acquired lock 1");
                }
            }
        });
        thread2.start();

        final DeadlockDetector detector = new DeadlockDetector(new DeadlockConsoleHandler(), 1, ManagementFactory.getThreadMXBean());
        detector.start();
    }

}
