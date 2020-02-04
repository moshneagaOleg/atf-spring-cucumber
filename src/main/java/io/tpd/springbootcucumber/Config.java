package io.tpd.springbootcucumber;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component(value = "configuration")
@Getter
@Configuration
public class Config {

    public static String SCENARIO_NAME = null;

    public static String TENANT = null;

    @Autowired
    private static Environment environment;

    @Value("${datasource.name}")
    private String name;

    @Value("${datasource.url}")
    private String baseUrl;

    @Value("${browser}")
    private String browser;

    @Value("${basicUser}")
    private String basicUser;

    @Value("${commonPassword}")
    private String commonPassword;

    @Value("${elementWait}")
    private int elementWait;

    @Value("${pageLoadWait}")
    private int pageLoadWait;

    @Value("${jsScriptWait}")
    private int jsScriptWait;

    /**
     * @return ex: wgu, csu, ftk ...
     */
    public static String getActiveApplication() {
        return Arrays.toString(environment.getActiveProfiles()).toLowerCase();
    }

}
