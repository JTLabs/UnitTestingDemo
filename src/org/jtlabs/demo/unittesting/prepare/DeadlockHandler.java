package org.jtlabs.demo.unittesting.prepare;

import java.lang.management.ThreadInfo;

public interface DeadlockHandler {
    void handleDeadlock(ThreadInfo[] deadlockedThreads);
}
