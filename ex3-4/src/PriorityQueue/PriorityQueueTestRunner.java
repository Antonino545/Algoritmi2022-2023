package PriorityQueue;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * PriorityQueueTestRunner is a class that run the test of PriorityQueueTest
 */
public class PriorityQueueTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PriorityQueueTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}


