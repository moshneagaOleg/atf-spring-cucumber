package io.tpd.springbootcucumber;

import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component(value = "context")
public class ScenarioContext {

    private static final Map<Keys, Object> data = new HashMap<>();

    public void save(Keys keys, Object value) {
        data.put(keys, value);
    }

    public Object getData(Keys keys) {
        return data.get(keys);
    }
}
