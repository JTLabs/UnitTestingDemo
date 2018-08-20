package org.jtlabs.demo.unittesting.learning.deadlockdetection;

import java.lang.management.ThreadInfo;

public interface DeadlockHandler {
    void handleDeadlock(ThreadInfo[] deadlockedThreads);
}
