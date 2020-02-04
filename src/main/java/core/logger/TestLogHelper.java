package core.logger;

import org.slf4j.MDC;

public class TestLogHelper {

    // FIXME: 2/4/2020 or remove or use

    private static final String TEST_NAME = "testname";

    public static void startTestLogging(String name) {
        MDC.put(TEST_NAME, name);
    }

    public static void stopTestLogging() {
        MDC.remove(TEST_NAME);
    }

    public static String getCurrentLogName() {
        return MDC.get(TEST_NAME) == null ? "test" : MDC.get(TEST_NAME);
    }

}
