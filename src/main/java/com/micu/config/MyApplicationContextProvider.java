package com.micu.config;


import org.springframework.context.ApplicationContext;


/**
 * Aayush Chaudhary
 */
public class MyApplicationContextProvider {
    public static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    /**
     * Returns the desired bean from spring application context.
     *
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            throw new RuntimeException("App context is not initialized");
        }
        return context.getBean(clazz);
    }

}

