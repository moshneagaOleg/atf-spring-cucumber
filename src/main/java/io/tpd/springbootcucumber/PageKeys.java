package io.tpd.springbootcucumber;

import lombok.Getter;

@Getter
public enum PageKeys implements Keys {
    OPEN_DRIVER("Open Driver"),
    CURRENT_PAGE("Saving current page Object"),
    TEST_EMAIL("Saving test email of new user"),
    ORDER_NUMBER("Saving order number"),
    LAST_CLICKED("Last clicked element"),
    PRICE_FROM_ELEMENT("Last clicked element"),
    UNIVERSITY_NAME("University name"),
    PRODUCT_NAME_FROM_ELEMENT("Last saved product name"),
    LIST_RANDOM_VALUES("(A list of random values selected durring the test"),
    SCENARIO_NAME("Name of executed scenario"),
    GREEN_MAIL_SERVER("GreenEmail server"),
    WGU_INIT("Init WGU"),
    CSU_INIT("Init CSU"),
    FTK_INIT("Init FTK"),
    HRZ_INIT("Init HRZ"),
    STUDENT_PORTAL_INIT("Init Student Portal");

    private String description;

    PageKeys(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
