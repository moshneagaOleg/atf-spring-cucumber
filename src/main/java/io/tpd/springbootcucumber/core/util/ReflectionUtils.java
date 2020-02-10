package io.tpd.springbootcucumber.core.util;

import io.tpd.springbootcucumber.core.app.abstractApps.AbstractStudentPortal;
import lombok.NonNull;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static <T> T newInstance(Class<T> type, Object... parameters) throws
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = type.getDeclaredConstructors()[1];
        Object[] objects = Arrays.stream(parameters).map(o -> {
            if (o instanceof Class && ((Class) o).getSimpleName().equals("Component")) return null;
            else return o;
        }).collect(Collectors.toList()).toArray();
        return type.cast(constructor.newInstance(objects));
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

    /**
     * Find in studentPortal (ex: WGU.class) the method with name 'init' and like params put WebDriver.
     * Run init method (first param the object the underlying method is invoked from) and (second is needed param in
     * init method).
     *
     * @param driver WebDriver
     * @param studentPortal WGU.class, CSU.class,
     * @param <T> AbstractStudentPortal
     * @return AbstractStudentPortal
     */
    public static <T extends AbstractStudentPortal> T initStudentPortal(@NonNull WebDriver driver, Class<T> studentPortal) {
        try {
            Method init = studentPortal.getMethod("init", WebDriver.class);
            init.setAccessible(true);

            return (T) init.invoke(studentPortal, driver);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
