package com.appium.generalStore.utility;

import org.testng.ITestResult;

import com.appium.generalStore.annotation.Feature;

import java.lang.reflect.Method;

public class AnnotationReader {
    public static String getFeatureInTestMethod(ITestResult iTestResult) {
        String featureName = null;
        Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
        Feature feature = method.getAnnotation(Feature.class);

        if (feature != null) {
            featureName = feature.value();
        }

        return featureName;
    }
}
