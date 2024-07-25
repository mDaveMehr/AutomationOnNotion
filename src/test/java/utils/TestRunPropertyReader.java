package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestRunPropertyReader {

    protected static Properties testRunProperties;

    public static void loadTestRunProperties() {
        File propFile = new File("src/test/resources/testrun.properties");
        FileInputStream fis = null;
        try {
             fis = new FileInputStream(propFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }
        testRunProperties = new Properties();
        try {
            testRunProperties.load(fis);
        } catch (IOException e) {
            System.out.println("IO Exception.");
        }
    }

    public static String getTestRunProperty(String key) {
        return (String) testRunProperties.get(key);
    }

}
