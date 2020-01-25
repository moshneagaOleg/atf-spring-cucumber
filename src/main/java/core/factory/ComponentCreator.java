package core.factory;

import core.annotations.Locator;
import core.element.AbstractComponent;
import core.element.Component;
import core.element.GroupComponent;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static core.util.ReflectionUtils.extractFieldsByPredicate;
import static core.util.ReflectionUtils.newInstance;

public class ComponentCreator {

    private ComponentCreator() {
    }

    public static Predicate<Field> isElement() {
        return field -> AbstractComponent.class.isAssignableFrom(field.getType());
    }

    public static AbstractComponent createComponent(WebDriver browser, Field field, Component parent) throws Exception {
        Locator annotation = field.getAnnotation(Locator.class);

        Class<? extends AbstractComponent> elementType = (Class<? extends AbstractComponent>) field.getType();
        Object parentObject = parent != null ? parent : Component.class;
        AbstractComponent component = createInstance(elementType, field, browser, annotation.name(), parentObject, annotation.xpath());

//        if (Arrays.asList(field.getType().getInterfaces()).contains(Module.class)) {
//            createContent(browser, component, component);
//        }
        return component;
    }
//
    public static <T> T createContent(WebDriver browser, T parent, Component parentComponent) throws Exception {
        try {
            List<Field> controls = extractFieldsByPredicate(parent.getClass(), isElement());
            for (Field field : controls) {
                field.setAccessible(true);

                AbstractComponent abstractComponent = createComponent(browser, field, parentComponent);
                field.set(parent, abstractComponent);
            }
            return parent;
        } catch (IllegalAccessException e) {
            throw new Exception("Failed to create content for entry [" + parent.getClass().getSimpleName() + "]", e);
        }

//        todo remove after
//        return null;
    }

    public static <T extends AbstractComponent> T createInstance(Class<T> type, Field field, Object... parameters) throws Exception {
        try {
            Object[] array = parameters;
            if (type.equals(GroupComponent.class)) {
                Class<?> clazz = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                List<Object> objects = new ArrayList<>(Arrays.asList(parameters));
                objects.add(clazz);
                array = objects.toArray();
            }
            return newInstance(type, array);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new Exception("Failed to create instance of type [" + type.getName() + "] for field [" + field.getName() + "]", e);
        }
    }

}
