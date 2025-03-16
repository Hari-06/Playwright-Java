package com.framework.constants;

import lombok.Getter;
import lombok.Setter;

public class FrameworkConstants {
    private FrameworkConstants() {
    }

    public static final String HOME_PAGE_TITLE = "OrangeHRM";
    public static final String LOGIN_PAGE_TITLE = "OrangeHRM";
    @Getter
    private static final String TEST_MANAGER_PATH = "src/test/resources/testManager/";
    @Getter
    @Setter
    public static String resultsFolder;

    public static String getRunManagerPath() {
        return TEST_MANAGER_PATH + "Run Manager.xlsx";
    }

    public static String getTestDataPath(String testClassName) {
        System.out.println(testClassName);
        return TEST_MANAGER_PATH + "testData/" + testClassName + ".xlsx";
    }

}
