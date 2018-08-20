package org.jtlabs.demo.unittesting.learning.deadlockdetection;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;

public class DeadlockDetector {

    private final DeadlockHandler deadlockHandler;
    private final long rerunPeriodMinutes;
    private final ThreadMXBean threadMXBean;

    private final Runnable deadlockChecker = new Runnable() {
        @Override
        public void run() {
            long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();

            if (deadlockedThreadIds != null) {
                ThreadInfo[] deadlockedThreads = threadMXBean.getThreadInfo(deadlockedThreadIds);
                deadlockHandler.handleDeadlock(deadlockedThreads);
            }
        }
    };
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    DeadlockDetector(final DeadlockHandler deadlockHandler, final long rerunPeriodMinutes, final ThreadMXBean threadMXBean) {
        this.deadlockHandler = deadlockHandler;
        this.rerunPeriodMinutes = rerunPeriodMinutes;
        this.threadMXBean = threadMXBean;
    }

    void start() {
        executorService.scheduleWithFixedDelay(deadlockChecker, 0, rerunPeriodMinutes, MINUTES);
    }
}
