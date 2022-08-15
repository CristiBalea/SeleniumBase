package TestUtils;

import org.testng.Assert;

public class TestLogger {

    private TestLogger() {

    }

    public static void logPass(String message) {
        Assert.assertEquals(true, message);
    }
}
