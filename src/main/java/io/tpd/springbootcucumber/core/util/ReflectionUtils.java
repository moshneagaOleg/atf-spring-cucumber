package io.tpd.springbootcucumber.core.util;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ReflectionUtils {

    @NonNull
    public static String getXpath(@NonNull WebElement element) {
        try {
            @NonNull Object xpathValue = FieldUtils.readField(Objects.requireNonNull(getLocator(element)), "xpathExpression", true);
            return (String) xpathValue;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private static By.ByXPath getLocator(@NonNull WebElement element) {
        try {
            Object proxyOrigin = FieldUtils.readField(element, "h", true);
            Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
            @NonNull Object findBy = FieldUtils.readField(locator, "by", true);
            return (By.ByXPath) findBy;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static <T> T newInstance(Class<T> type, Object... parameters) {
        Constructor<T> constructor = type.getConstructor(WebDriver.class, String.class, String.class);
        return type.cast(constructor.newInstance(parameters));
    }

    public static List<Field> extractFieldsByPredicate(Class<?> type, Predicate<Field> predicate) {
        List<Field> declaredFields = Arrays.asList(getAllFields(type));
        return declaredFields.stream().filter(predicate).collect(Collectors.toList());
    }

    public static Field[] getAllFields(Class<?> clazz) {
        List<Class<?>> classes = getAllSuperclasses(clazz);
        classes.add(clazz);
        return getAllFields(classes);
    }

    private static Field[] getAllFields(List<Class<?>> classes) {
        Set<Field> fields = new HashSet<>();
        for (Class<?> clazz : classes) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        return fields.toArray(new Field[fields.size()]);
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> clazz) {
        List<Class<?>> classes = new ArrayList<>();

        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null) {
            classes.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return classes;
    }

}
