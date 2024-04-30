import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static void main(String[] args) {
        // чтение данных
        String circlePath = args[0]; // D:\Projects\repositories\Cifra_Test\task2\src\task2_circle.txt
        String pointsPath = args[1]; // D:\Projects\repositories\Cifra_Test\task2\src\task2_points.txt

        List<String> linesCircleFile = readFile(circlePath);
        List<String> linesPointsFile = readFile(pointsPath);
        if (linesCircleFile.size() < 2 || linesPointsFile.isEmpty()) { return; }

        // решение
        String circlePoint = linesCircleFile.get(0);
        String circleRadius = linesCircleFile.get(1);
        List<String> pointsData = new ArrayList<>();
        pointsData.addAll(linesPointsFile);

        List<Integer> results = new ArrayList<>();
        float xDiff, yDiff, hypotenuse2, radius2;

        Point point;
        Circle circle = buildCircle(circlePoint, circleRadius);
        for (String pointData : pointsData) {
            point = buildPoint(pointData);

            xDiff = circle.x - point.x;
            yDiff = circle.y - point.y;
            hypotenuse2 = xDiff  * xDiff + yDiff * yDiff;
            radius2 = circle.r * circle.r;

            if (hypotenuse2 < radius2) {
                results.add(CIRCLE_INNER);
            } else if (hypotenuse2 > radius2) {
                results.add(CIRCLE_OUTER);
            } else {
                results.add(CIRCLE);
            }
        }

        // вывод данных
        for (int result : results) {
            System.out.println(result);
        }
    }

    static Circle buildCircle(String sPoint, String sRadius) {
        String[] coordinates = sPoint.split("\\s");

        Circle circle = new Circle();
        circle.x = Float.parseFloat(coordinates[0]);
        circle.y = Float.parseFloat(coordinates[1]);
        circle.r = Float.parseFloat(sRadius);
        return circle;
    }

    static Point buildPoint(String sPoint) {
        String[] coordinates = sPoint.split("\\s");

        Point point = new Point();
        point.x = Float.parseFloat(coordinates[0]);
        point.y = Float.parseFloat(coordinates[1]);
        return point;
    }

    static List<String> readFile(String filepath) {
        try {
            Path path = Paths.get(filepath);
            return Files.readAllLines(path);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    static class Circle {
        float x;
        float y;
        float r;
    }

    static class Point {
        float x;
        float y;
    }

    static final int CIRCLE = 0;
    static final int CIRCLE_INNER = 1;
    static final int CIRCLE_OUTER = 2;
}