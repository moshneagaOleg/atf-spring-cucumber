package core.page;

public interface Page {

    String getUrl();

    String getName();

    void open();

    String getTitle();

    boolean isCurrentPage();

    boolean isReady();

}
