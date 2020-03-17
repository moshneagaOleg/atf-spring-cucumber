package io.tpd.springbootcucumber.core.page;

public interface Page {

    String getUrl();

    String getName();

    void open();

    String getTitle();

    boolean isCurrentUrl();

    boolean isReady();

    default void validatePageTitle() {

    }

}
