# BDD Automation Framework

Stack of technologies: Java, Maven, [Selenium](https://selenium.dev/documentation/en/), 
Junit, [Cucumber](https://cucumber.io/docs/guides/), Spring Boot, Yandex Elements, YAML

## Before start

1. Edit configuration - Templates - Cucumber java
2. Input Main class: io.cucumber.core.cli.Main
3. Glue: com.foreach.cuke io.tpd.springbootcucumber 
4. Apply

## Run mode

From console:
```bash
mvn clean test -Drun.from=ci -Dspring.profiles.active=goodreads -Denv.name=qa -Dcucumber.ansi-colors.disabled=true -Dcucumber.filter.tags="(@Parallel1 or @Parallel2 or @Parallel3) and (not @Ignore)"
```
#Examples:

[cucumber.filter.tags](https://cucumber.io/docs/cucumber/api/#tags):
```bash
--tags @Parallel1 or @Parallel2 or @Parallel3.... - for run
--tags not @ExampleIgnoreTag - for ignore
```
spring.profiles.active:
```bash
select one of the existing app: goodreads, exampleApp, exampleApp2, exampleApp3
```
env.name:
```bash
select one of the existing environment: qa, dev, stg or www for production 
for feature: -Denv.name=en-#####.feat (where ##### is number of ticket from JIRA)
```
browser:
```bash
select one of the browser: chrome, firefox, edge, safari
```
run.from:
```bash
there are two options: local, ci
