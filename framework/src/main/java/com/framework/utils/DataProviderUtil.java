package com.framework.utils;

import com.framework.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class DataProviderUtil {
    private static List<Map<String, String>> list =	new ArrayList<>();
    @DataProvider(parallel=false)
    public static Object[] getData(Method m) {
        Class<?> testClass = m.getDeclaringClass();
        String className = testClass.getSimpleName();
        String testMethod = m.getName();
        if(list.isEmpty()) {
            list = ExcelUtil.getTestDetails(FrameworkConstants.getTestDataPath(className),"Test Data");
            System.out.println(list);
        }
        List<Map<String, String>> smalllist = new ArrayList<>(list);

        Predicate<Map<String,String>> isTestNameNotMatching = map ->!map.get("Test Method").equalsIgnoreCase(testMethod);
        Predicate<Map<String,String>> isExecuteColumnNo = map -> map.get("Execute").equalsIgnoreCase("no");

        smalllist.removeIf(isTestNameNotMatching.or(isExecuteColumnNo));
        return smalllist.toArray();

    }
}