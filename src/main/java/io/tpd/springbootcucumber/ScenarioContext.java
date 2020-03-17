package io.tpd.springbootcucumber;

import io.tpd.springbootcucumber.core.page.Page;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component(value = "context")
public class ScenarioContext {

    private final Map<PageKeys, Object> data = new HashMap<>();

    @Getter
    private Page currentPage;

    public void save(PageKeys keys, Object value) {
        data.put(keys, value);
    }

    public Object getData(PageKeys keys) {
        return data.get(keys);
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
        log.info(String.format("Switched to [%s] page", currentPage.getName()));
    }
}
