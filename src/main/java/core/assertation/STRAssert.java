package core.assertation;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Only jUnit and Hamcrest assertion
 */
public class STRAssert {

    private static final String MESSAGE_EXPECTED_ACTUAL = "{}{} - expected: {}{} - actual: {}";
    private static final String ASSERT_THAT_STR = "Assert that ";
    private static Logger logger = LoggerFactory.getLogger(STRAssert.class);

    public static void assertTrue(String message, boolean condition) {
        String fullMessage = ASSERT_THAT_STR + message;
        logger.info(
                MESSAGE_EXPECTED_ACTUAL,
                fullMessage,
                System.lineSeparator(),
                true,
                System.lineSeparator(),
                condition);

        Assert.assertTrue(message, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        String fullMessage = ASSERT_THAT_STR + message;
        logger.info(
                MESSAGE_EXPECTED_ACTUAL,
                fullMessage,
                System.lineSeparator(),
                false,
                System.lineSeparator(),
                condition);

        Assert.assertFalse(condition);
    }

    public static <T> void assertThat(String message, T actual, Matcher<? super T> matcher) {
        assertThat(message, "", actual, matcher);
    }

    public static <T> void assertThat(
            String message, List<T> param, String failureMessage, T actual, Matcher<? super T> matcher) {
        logger.info(message, param);
        assertThat(message, failureMessage, actual, matcher);
    }

    public static <T> void assertThat(
            String longMessage, String failureMessage, T actual, Matcher<? super T> matcher) {
        String fullMessage = ASSERT_THAT_STR + longMessage;
        logger.info(
                MESSAGE_EXPECTED_ACTUAL,
                fullMessage,
                System.lineSeparator(),
                matcher.toString(),
                System.lineSeparator(),
                actual);
        if (StringUtils.isEmpty(failureMessage)) {
            Assert.assertThat(actual, matcher);
        } else {
            Assert.assertThat(failureMessage, actual, matcher);
        }
    }

    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        String message = "the actual value matches the expected value";
        assertThat(message, actual, matcher);
    }

    /**
     * Assert if
     *
     * @param actualValue   Ex: On the page 4 elements
     * @param expectedValue Ex: User expect 5 elements on the page
     * @param actionMsg
     * @param <T>
     */
    public <T> void assertEquals(T actualValue, T expectedValue, String actionMsg) {
        logger.info(
                "Assert equals: {}\n Compared values:\n actual  : '{}'\n expected: '{}'",
                actionMsg == null ? "" : actionMsg,
                actualValue,
                expectedValue);
        Assert.assertEquals(expectedValue, actualValue);
    }

    public void assertContains(String actualValue, String expectedValue, String msg) {
        actualValue = StringUtils.normalizeSpace(actualValue).replaceAll("\\n", " ");
        expectedValue = StringUtils.normalizeSpace(expectedValue).replaceAll("\\n", " ");
        String assertMsg =
                String.format(
                        "Assert contains: '%s'\nCompared values:\nactual  : '%s'\nexpected: '%s'",
                        msg, actualValue, expectedValue);
        assertTrue(assertMsg, StringUtils.contains(actualValue, expectedValue));
    }
}
