package io.tpd.springbootcucumber;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component(value = "context")
public class ScenarioContext {

    private static final Map<PageKeys, Object> data = new HashMap<>();

    public void save(PageKeys keys, Object value) {
        data.put(keys, value);
    }

    public Object getData(PageKeys keys) {
        return data.get(keys);
    }
}
