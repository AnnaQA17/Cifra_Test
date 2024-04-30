import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    // используется библиотека GSON-2.10.1
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        // чтение данных
        String valuesPath = args[0]; // D:\Projects\repositories\Cifra_Test\task3\task3_values.json
        String testsPath = args[1]; // D:\Projects\repositories\Cifra_Test\task3\task3_tests.json
        String reportPath = args[2]; // D:\Projects\repositories\Cifra_Test\task3\task3_report.json

        String valuesJson = readFile(valuesPath);
        ValueItems valuesData = gson.fromJson(valuesJson, ValueItems.class);

        String testsJson = readFile(testsPath);
        TestItems testsData = gson.fromJson(testsJson, TestItems.class);

        // решение
        Map<Integer, String> valuesMap = new HashMap<>();
        for (ValueItem valueItem : valuesData.values) {
            valuesMap.put(valueItem.id, valueItem.value);
        }

        for (TestItem test : testsData.tests) {
            updateTests(test, valuesMap);
        }

        // запись данных
        String report = gson.toJson(testsData);
        writeFile(reportPath, report);
    }

    // рекурсивно проходим по элементу теста
    private static void updateTests(TestItem test, Map<Integer, String> valuesMap) {
        Integer testId = test.id;

        if (valuesMap.containsKey(testId)) {
            test.value = valuesMap.get(testId);
        } else {
            test.value = "";
        }

        if (test.values != null) {
            for (TestItem childTest : test.values) {
                updateTests(childTest, valuesMap);
            }
        }
    }

    private static String readFile(String filepath) {
        try {
            Path path = Paths.get(filepath);
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Что-то пошло не так. Не найден файл - " + filepath);
            return null;
        }
    }

    private static void writeFile(String filepath, String data) {
        try {
            Path path = Paths.get(filepath);
            Files.writeString(path, data);
        } catch (IOException e) {
            System.out.println("Что-то пошло не так. Не смогли записать в файл - " + filepath);
        }
    }

    private static class ValueItem {
        int id;
        String value;
    }

    private static class ValueItems {
        List<ValueItem> values;
    }

    private static class TestItem {
        int id;
        String title;
        String value;
        List<TestItem> values;
    }

    private static class TestItems {
        List<TestItem> tests;
    }
}