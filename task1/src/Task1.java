import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // 5
        int m = Integer.parseInt(args[1]); // 4

        // круговой массив
        List<Integer> cycleSource = new ArrayList<>();
        for (int i = 1; i <= n; i++) cycleSource.add(i);

        // интервалы
        List<Integer> interval;
        List<List<Integer>> intervals = new ArrayList<>();

        int currentElement;
        int currentIndex = 0;
        boolean isFound = false;

        while (!isFound) {
            interval = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                currentElement = cycleSource.get(currentIndex);
                interval.add(currentElement);

                // последний элемент является первым для следущего интервала
                if (i < m - 1) currentIndex = getNextIndex(currentIndex, n);
            }
            intervals.add(interval);

            if (interval.getLast().intValue() == cycleSource.getFirst().intValue()) isFound = true;
        }

        // путь
        StringBuilder resultPath = new StringBuilder();
        for (List<Integer> item : intervals) {
            resultPath.append(item.getFirst().toString());
        }

        System.out.println("Путь: " + resultPath);
    }

    // получение следущего индекса элемента в круговом массиве
    static int getNextIndex(int index, int n) {
        if (index + 1 > n - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }
}