package core.factory;

//@Component
public class PageScanner {
//    private static String PAGE_PACKAGE;
//
//    @Autowired
//    @Qualifier("pageObjectsPackage")
//    public void setPagesPackage(String pagesPackage) {
//        PAGE_PACKAGE = pagesPackage;
//    }
//
//    private static Predicate<Class<?>> isPageWithName(String name) {
//        return clazz -> clazz.getDeclaredAnnotation(PageAccessor.class).name().equals(name);
//    }
//
//    private static Predicate<Class<?>> isPageWithUrl(final String baseUrl, final String url) {
//        return clazz -> url.startsWith(baseUrl + StringUtil.addSlash(clazz.getDeclaredAnnotation(PageAccessor.class).url()));
//    }
//
//    private static Predicate<Field> isElementWithName(String name) {
//        return field -> field.getAnnotation(Locator.class).name().equals(name);
//    }
//
//    private static Supplier pageWithNameNotFound(String name) {
//        return () -> new Exception("Could not find page with name [" + name + "]");
//    }
//
//    private static Supplier pageWithUrlNotFound(String url) {
//        return () -> new Exception("Could not find page with url [" + url + "]");
//    }
//
//    public static Class<? extends AbstractPage> getPageByName(final String name) {
//        ConfigurationBuilder builder = new ConfigurationBuilder()
//                .setUrls(ClasspathHelper.forPackage(PAGE_PACKAGE))
//                .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
//        Reflections reflections = new Reflections(builder);
//
//        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(PageAccessor.class);
//        Optional<Class<?>> first = typesAnnotatedWith.stream().filter(isPageWithName(name)).findFirst();
//        return (Class<? extends AbstractPage>) first.orElseThrow(pageWithNameNotFound(name));
//    }
//
//    public static Class<? extends AbstractPage> getPageByUrl(final String baseUrl, final String url) {
//        ConfigurationBuilder builder = new ConfigurationBuilder()
//                .setUrls(ClasspathHelper.forPackage(PAGE_PACKAGE))
//                .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
//        Reflections reflections = new Reflections(builder);
//
//        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(PageAccessor.class);
//        Optional<Class<?>> first = typesAnnotatedWith.stream().filter(isPageWithUrl(baseUrl, url)).findFirst();
//        return (Class<? extends AbstractPage>) first.orElseThrow(pageWithUrlNotFound(url));
//    }
//
//    public static <T extends AbstractComponent> T getPageElementByName(String name, Page page) throws Exception {
//        try {
//            T t = null;
//            List<Field> fields = extractFieldsByPredicate(page.getClass(), isElement());
//
//            for (Field field : fields) {
//                field.setAccessible(true);
//                Locator locator = field.getDeclaredAnnotation(Locator.class);
//
//                if (locator.name().equals(name)) {
//                    t = (T) field.get(page);
//                    break;
//                }
//
//                if (Arrays.asList(field.getType().getInterfaces()).contains(Module.class)) {
//                    Module module = (Module) field.get(page);
//                    t = getModuleElementByName(name, module);
//                    if (t != null) break;
//                }
//            }
//
//            return t;
//        } catch (IllegalAccessException e) {
//            throw new Exception("Failed to get element with name [" + name + "] from page [" + page.name() + "]", e);
//        }
//    }
//
//    public static <T extends AbstractComponent> T getModuleElementByName(String name, Module module) throws Exception {
//        T t = null;
//        try {
//            List<Field> controls = extractFieldsByPredicate(module.getClass(), isElement());
//            for (Field field : controls) {
//                field.setAccessible(true);
//                Locator locator = field.getDeclaredAnnotation(Locator.class);
//                if (locator.name().equals(name)) {
//                    t = (T) field.get(module);
//                    break;
//                } else if (Arrays.asList(field.getType().getInterfaces()).contains(Module.class)) {
//                    Module m = (Module) field.get(module);
//                    t = getModuleElementByName(name, m);
//                    if (t != null) break;
//                }
//            }
//            return t;
//        } catch (IllegalAccessException e) {
//            throw new Exception("Failed to retreive component with name [" + name + "] from module [" + module.getName() + "]", e);
//        }
//    }
}
