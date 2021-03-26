package com.vantty.treemap.util;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CamelCaseNameGenerator implements DisplayNameGenerator {
    
    @Override public String generateDisplayNameForClass(Class<?> testClass) {
        return testClass.getSimpleName().split("Test")[0];
    }
    
    @Override public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return nestedClass.getSimpleName().replaceAll("([A-Z][a-z]+)", " $1").toLowerCase();
    }
    
    @Override public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return "f(x) -> " + testMethod.getName().replaceAll("([A-Z])", " $1").toLowerCase();
    }
    
}
