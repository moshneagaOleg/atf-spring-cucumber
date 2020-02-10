package io.tpd.springbootcucumber.core.assertation;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.IsEqual.equalTo;

public class VTFAssert extends MatcherAssert {

    private final static String MESSAGE_EXPECTED_ACTUAL = "{}{} - expected: {}{} - actual: {}";
    private static Logger logger = LoggerFactory.getLogger(VTFAssert.class);

    public static void assertContains(String actualValue, String expectedValue, String msg) {
        actualValue = StringUtils.normalizeSpace(actualValue).replaceAll("\\n", " ");
        expectedValue = StringUtils.normalizeSpace(expectedValue).replaceAll("\\n", " ");
        String assertMsg =
                String.format(
                        "Assert contains: '%s'\nCompared values:\nactual  : '%s'\nexpected: '%s'",
                        msg, actualValue, expectedValue);
        assertThat(assertMsg, StringUtils.contains(actualValue, expectedValue));
    }

    /**
     * Assert that <b>assertion</b> is true. Logs result in both cases of passed or failed assertion.
     * <br>
     * <br>
     * Example: <code>assertThat("Table with users should be visible", usersTable.isVisible())</code>.
     *
     * @param reason    description of positive assertion.
     * @param assertion any boolean expression or variable
     */
    public static void assertThat(String reason, boolean assertion) {
        logger.info(MESSAGE_EXPECTED_ACTUAL, reason, System.lineSeparator(), true, System.lineSeparator(), assertion);
        assertThat(reason, assertion, equalTo(true));
    }

    /**
     * Assert that <b>actual</b> matches an expected condition. Logs result in both cases of passed or failed assertion.
     *
     * @param actual  a value to be asserted (usually a value from your test environment)
     * @param matcher an instance of {@link org.hamcrest.Matcher} describing exact rule your <b>actual</b> value should match.
     */
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        String message = "the actual value matches the expected value";
        logger.info(MESSAGE_EXPECTED_ACTUAL, message, System.lineSeparator(), actual, System.lineSeparator(), matcher);
        assertThat(message, actual, matcher);
    }

    /**
     * Assert that <b>actual</b> matches an expected condition. Logs result in both cases of passed or failed assertion.
     *
     * @param reason  description of assertion.
     * @param actual  a value to be asserted (usually a value from your test environment)
     * @param matcher an instance of {@link org.hamcrest.Matcher} describing exact rule your <b>actual</b> value should match.
     * @param <T>
     */
    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        logger.info(MESSAGE_EXPECTED_ACTUAL, reason, System.lineSeparator(), actual, System.lineSeparator(), matcher);
        MatcherAssert.assertThat(reason, actual, matcher);
    }

}