package io.tpd.springbootcucumber.core.element;

import io.tpd.springbootcucumber.core.assertation.VTFAssert;
import io.tpd.springbootcucumber.core.util.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class WebTable extends WebTypifiedElement {

    private List<Map<String, WebElement>> rowMappedToHeadings;

    public WebTable(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /////////////////////////////////// ~ HEADINGS ~ ///////////////////////////////////

    public static WebTypifiedElement getCellInnerWebElement(Map<String, WebElement> row, String headingValue,
                                                            String innerElementRelativeXPath) {
        return new WebTypifiedElement(row.get(headingValue).findElement(By.xpath(innerElementRelativeXPath)),
                headingValue);
    }

    public List<WebElement> getHeadings() {
        return this.getWrappedElement().findElements(By.xpath(".//th/parent::tr/parent::thead/parent::"
                + "table/parent::*[not(contains(@class,'calendar'))]/table/thead/tr[1]/th"));
    }

    /////////////////////////////////// ~ ROWS ~ ///////////////////////////////////

    public List<String> getHeadingsAsString() {
        return this.getHeadings().stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("\n\\d{1,2}", StringUtils.EMPTY))
                .collect(Collectors.toList());
    }

    public List<List<WebElement>> getRows() {
        return this.getWrappedElement().findElements(By.xpath(".//tr/parent::tbody/parent::table/parent::"
                + "*[not(contains(@class,'calendar'))]/table/tbody/tr"))
                .stream()
                .map((rowElement) -> rowElement.findElements(By.xpath(".//td")))
                .filter((row) -> row.size() > 0).collect(Collectors.toList());
    }

    public List<List<String>> getRowsAsString() {
        return this.getRows()
                .stream()
                .map((row) -> row
                        .stream().map(WebElement::getText).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public List<Map<String, WebElement>> getRowsMappedToHeadings() {
        List<String> headingsAsString = this.getHeadingsAsString();
        return rowMappedToHeadings = this.getRows()
                .stream()
                .map((row) -> row
                        .stream().collect(
                                Collectors.toMap((e) -> headingsAsString.get(row.indexOf(e)), Function.identity())))
                .collect(Collectors.toList());
    }

    public List<Map<String, WebElement>> getRowsMappedToHeadings(List<String> headings) {
        return rowMappedToHeadings = this.getRowsMappedToHeadings()
                .stream()
                .map((e) -> e.entrySet()
                        .stream().filter((m) -> headings.contains(m.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getRowsAsStringMappedToHeadings() {
        rowMappedToHeadings = (rowMappedToHeadings != null && rowMappedToHeadings.size() > 0) ? rowMappedToHeadings
                : this.getRowsMappedToHeadings();
        return rowMappedToHeadings
                .stream()
                .map((m) -> m.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, (e) -> (e.getValue()).getText())))
                .collect(Collectors.toList());

    }

    public List<Map<String, String>> getRowsAsStringMappedToHeadings(List<String> headings) {
        rowMappedToHeadings = (rowMappedToHeadings != null && rowMappedToHeadings.size() > 0) ? rowMappedToHeadings
                : this.getRowsMappedToHeadings(headings);
        return rowMappedToHeadings
                .stream()
                .map((m) -> m.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, (e) -> (e.getValue()).getText())))
                .collect(Collectors.toList());
    }

    public int getRowIndexByColumnFirstMatch(String columnName, String columnValue) {
        List<Map<String, String>> rowsMap = this.getRowsAsStringMappedToHeadings();
        if (rowsMap.size() == 0) {
            throw new RuntimeException(String.format("ERROR: No rows found in '%s' table", this.getName()));
        }
        for (int rowIndex = 0; rowIndex < rowsMap.size(); rowIndex++) {
            if (rowsMap.get(rowIndex).get(columnName).equals(columnValue)) return rowIndex;
        }
        throw new RuntimeException(String.format("ERROR:'%s' value not found under column '%s' for table '%s'",
                columnValue, columnName, this.getName()));
    }

    /////////////////////////////////// ~ COLUMNS ~ ///////////////////////////////////

    public List<Integer> getRowIndexByColumnAllMatches(String columnName, String columnValue) {
        List<Map<String, String>> rowsMap = this.getRowsAsStringMappedToHeadings();
        if (rowsMap.size() == 0) {
//            throw log.throwing(new RuntimeException(String.format("ERROR: No rows found in '%s' table",
//            this.getName())));
        }
        List<Integer> resultList = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < rowsMap.size(); rowIndex++) {
            if (rowsMap.get(rowIndex).get(columnName).equals(columnValue)) resultList.add(rowIndex);
        }
        return resultList;
    }

    public List<List<WebElement>> getColumns() {
        List<List<WebElement>> columns = new ArrayList<>();
        List<List<WebElement>> rows = this.getRows();
        if (rows.isEmpty()) {
            return columns;
        } else {
            int columnCount = (rows.get(0)).size();

            for (int i = 0; i < columnCount; ++i) {
                List<WebElement> column = new ArrayList<>();

                for (List<WebElement> row : rows) {
                    column.add(row.get(i));
                }
                columns.add(column);
            }

            return columns;
        }
    }

    public List<WebElement> getColumnValuesByIndex(int index) {
        return this.getWrappedElement().findElements(By.cssSelector(String.format("tr > td:nth-of-type(%d)", index)));
    }

    public int getColumnIndexByName(String columnName) {
        String xPath = String.format("./thead/tr[1]//*[contains(text(),'%s')]/preceding::th", columnName);
        return this.getWrappedElement().findElements(By.xpath(xPath)).size();
    }

    /////////////////////////////////// ~ CELLS ~ ///////////////////////////////////

    public List<List<String>> getColumnsAsString() {
        return this.getColumns()
                .stream()
                .map((row) -> row
                        .stream().map(WebElement::getText).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public WebElement getCellAt(int i, int j) {
        return (WebElement) ((List) this.getRows().get(i)).get(j);
    }

    /////////////////////////////////// ~ TABLE ~ ///////////////////////////////////

    public int getTableSize() {
        return this.getWrappedElement().findElements(By.xpath("./tbody/tr")).size();
    }

    public WebTable verifyTableSize(Matcher<Integer> matcher, int timeoutSeconds) {
        Supplier<Boolean> supplier = () -> matcher.matches(getTableSize());
        VTFAssert.assertThat(String.format("table size %s", matcher.toString()), WaitUtils.waitUntilCondition(supplier,
                true, timeoutSeconds));
        return this;
    }
}
