package org.jtlabs.demo.unittesting.learning;

import java.util.ArrayList;
import java.util.List;

public class TokenManager {

    private final DeskA deskA;
    private final DeskB deskB;
    private final DeskC deskC;

    private final List<Integer> pendingTokens = new ArrayList();

    private final Object lockA = new Object();
    private final Object lockB = new Object();
    private final Object lockC = new Object();

    TokenManager(final DeskA deskA, final DeskB deskB, final DeskC deskC) {
        this.deskA = deskA;
        this.deskB = deskB;
        this.deskC = deskC;
    }

    public static void main(final String[] args) {
        final TokenManager tokenManager = new TokenManager(new DeskA(), new DeskB(), new DeskC());
        tokenManager.registerToken(1);
        tokenManager.registerToken(2);
        tokenManager.registerToken(3);

        // ======================================
        // Output:
        //  Token : 1 -  please visit Desk A
        //  Completed process for 1 at desk A
        //  Token : 2 -  please visit Desk A
        //  Token : 1 -  please visit Desk B
        //  Completed process for 2 at desk A
        //  Completed process for 1 at desk B
        //  Token : 3 -  please visit Desk A
        //  Token : 2 -  please visit Desk B
        //  Token : 1 -  please visit Desk C
        //  Completed process for 3 at desk A
        //  Completed process for 2 at desk B
        //  Completed process for 1 at desk C
        //  Token : 3 -  please visit Desk B
        //  Token : 2 -  please visit Desk C
        //  Completed process for 3 at desk B
        //  Completed process for 2 at desk C
        //  Token : 3 -  please visit Desk C
        //  Completed process for 3 at desk C
        // ======================================
    }

    void registerToken(final Integer tokenNumber) {
        pendingTokens.add(tokenNumber);
        waitForCall();
    }

    private void waitForCall() {
        new Thread(() -> {
            final Integer nextToken;

            synchronized (lockA) {
                nextToken = pendingTokens.get(0);
                pendingTokens.remove(nextToken);
                deskA.visit(nextToken);
            }

            synchronized (lockB) {
                deskB.visit(nextToken);
            }

            synchronized (lockC) {
                deskC.visit(nextToken);
            }
        }).start();
    }

    private static class DeskA {

        void visit(final Integer tokenNumber) {
            System.out.println("Token : " + tokenNumber + " -  please visit Desk A");
            // process...
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed process for " + tokenNumber + " at desk A");
        }
    }

    private static class DeskB {

        void visit(final Integer tokenNumber) {
            System.out.println("Token : " + tokenNumber + " -  please visit Desk B");
            // process...
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed process for " + tokenNumber + " at desk B");
        }
    }

    private static class DeskC {

        void visit(final Integer tokenNumber) {
            System.out.println("Token : " + tokenNumber + " -  please visit Desk C");
            // process...
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed process for " + tokenNumber + " at desk C");
        }
    }
}
