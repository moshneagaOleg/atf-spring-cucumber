package io.tpd.springbootcucumber;

import lombok.Getter;

@Getter
public enum PageKeys implements Keys {
    OPEN_DRIVER("Open Driver"),
    CURRENT_PAGE("Saving current page Object"),
    GOODREADS_INIT("Init Goodreads");

    private String description;

    PageKeys(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
