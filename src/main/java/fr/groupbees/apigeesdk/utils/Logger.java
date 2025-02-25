package fr.groupbees.apigeesdk.utils;

import java.time.Duration;
import java.time.Instant;

public class Logger {

    public static void warn(final String message) {
        System.err.println("[WARN] - " + message);
    }

    public static void error(final String message) {
        System.err.println("[ERROR] - " + message);
    }

    public static void info(final String message) {
        System.out.println("[INFO] - " + message);
    }

    public static void raw(final String message) {
        System.out.println(message);
    }

    public static void failedExecution(final String message, final Instant start) {
        error("Failed " + message + " in " + calcDuration(start, Instant.now()));
    }

    public static void successExecution(final String message, final Instant start) {
        info("Successfully " + message + " in " + calcDuration(start, Instant.now()));
    }

    public static void finishedExecution(final Instant start) {
        info("Finished in " + calcDuration(start, Instant.now()));
    }

    public static String calcDuration(Instant start, Instant end) {
        Duration duration = Duration.between(start, end);
        return duration.toString();
    }

}
