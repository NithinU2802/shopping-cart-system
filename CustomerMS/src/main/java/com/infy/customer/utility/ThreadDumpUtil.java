package com.infy.customer.utility;
import java.util.Map;

public class ThreadDumpUtil {

    public static void printThreadDump() {
        Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
        threadMap.forEach((thread, stackTrace) -> {
            System.out.println("Thread: " + thread.getName());
            for (StackTraceElement element : stackTrace) {
                System.out.println("\t" + element);
            }
        });
    }
}
