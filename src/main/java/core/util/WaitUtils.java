package core.util;

import core.element.YandexElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Thread.sleep;

public abstract class WaitUtils {

    private static long DEFAULT_RETRY_INTERVAL_MS = 1000;
    private static Logger logger = LoggerFactory.getLogger(WaitUtils.class.getSimpleName());

    public static Boolean waitUntilCondition(Supplier<Boolean> function, boolean condition, int secondsTimeInterval) {
        boolean result = !condition;
        long start = System.currentTimeMillis();

        while ((System.currentTimeMillis() - start) < TimeUnit.SECONDS.toMillis(secondsTimeInterval) && result != condition) {
            result = function.get();
            waitForRetry(DEFAULT_RETRY_INTERVAL_MS);
        }

        return result;
    }

    public static Boolean waitUntilCondition(Function<YandexElement, Boolean> function, YandexElement webElement, boolean condition, int secondsTimeInterval) {
        long start = System.currentTimeMillis();
        do {
            try {
                if (function.apply(webElement) == condition) return condition;
            } catch (Exception e) {
                logger.info("exception caught throughout 'waitUntilCondition' method execution: {}", e.getMessage());
            }
            waitForRetry(DEFAULT_RETRY_INTERVAL_MS);
        } while ((System.currentTimeMillis() - start) < TimeUnit.SECONDS.toMillis(secondsTimeInterval));
        throw new RuntimeException("condition wasn't met within indicated time range");
    }

    public static void waitForRetry(long retryDelayMs) {
        try {
            sleep(retryDelayMs);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep in the retry interval was interrupted", e);
        }
    }

}
