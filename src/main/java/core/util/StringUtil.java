package core.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    /**
     * Will add slash into first position of string in case if it is not found
     *
     * @param str String
     * @return String
     */
    public static String addSlash(String str) {
        return (StringUtils.isNotBlank(str) && str.charAt(0) != '/' ? "/" : "") + str;
    }

    public static String deleteSlash(String str) {
        return (StringUtils.isNotBlank(str) && str.charAt(0) == '/' ? str.replaceFirst("/", "") : "");
    }

}