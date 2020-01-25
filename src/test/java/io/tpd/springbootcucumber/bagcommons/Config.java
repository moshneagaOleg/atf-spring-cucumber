package io.tpd.springbootcucumber.bagcommons;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
@Configuration
public class Config {

    public static String TENANT = null;

    @Value("${datasource.name}")
    private String name;

    @Value("${datasource.url}")
    private String url;

    @Value("${basicUser}")
    private String basicUser;

    @Value("${commonPassword}")
    private String commonPassword;

}
