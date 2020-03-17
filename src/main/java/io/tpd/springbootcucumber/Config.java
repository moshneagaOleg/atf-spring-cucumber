package io.tpd.springbootcucumber;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component(value = "configuration")
@Getter
@Configuration
public class Config {

    public static final int SMALL_WAIT_TIMEOUT = 5;
    public static final int DEFAULT_WAIT_TIMEOUT = 10;
    public static final int BIG_WAIT_TIMEOUT = 30;

    @Value("${datasource.name}")
    private String name;

    @Value("${datasource.url}")
    private String baseUrl;

    @Value("${browser}")
    private String browser;

    @Value("${elementWait}")
    private int elementWait;

    @Value("${pageLoadWait}")
    private int pageLoadWait;

    @Value("${jsScriptWait}")
    private int jsScriptWait;

}
