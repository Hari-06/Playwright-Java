package com.framework.listeners;

import com.framework.utils.DataProviderUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {
  @Override
  public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
                        Method testMethod) {
    annotation.setDataProvider("getData");
    annotation.setDataProviderClass(DataProviderUtil.class);
    //annotation.setRetryAnalyzer(RetryFailedTests.class);
  }

}
