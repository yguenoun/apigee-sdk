package fr.groupbees.apigeesdk.utils;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String ERR_QP_ACTION = "Action quaryParam not present or not supported";
    public static final String ERR_QP_PROXY_NAME = "Name quaryParam not present";
    public static final String PROBLEM_BASE_URL = "https://api.group.echonet/problem";

    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/contraint-violation");
    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
    public static final URI ACTION_TYPE = URI.create(PROBLEM_BASE_URL + "/action");

    private ErrorConstants() {
    }
}
