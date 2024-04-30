import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task4 {

    public static void main(String[] args) {
        // чтение данных
        String numsFilepath = args[0]; // D:\Projects\repositories\Cifra_Test\task4\task4_nums.txt
        List<String> linesNumsFile = readFile(numsFilepath);

        List<Integer> nums = new ArrayList<>();
        for (String numLine : linesNumsFile) {
            nums.add(Integer.valueOf(numLine));
        }

        // решение
        Long numSteps; // количество ходов для конкретного числа
        Long numTotalSteps; // общее количество ходов для всех чисел
        Long minimalSteps = Long.MAX_VALUE; // максимально возможное (может быть и больше)

        for (Integer targetNum : nums) {
            numTotalSteps = 0L;
            for (Integer num : nums) {
                numSteps = Math.abs(targetNum.longValue() - num);
                numTotalSteps += numSteps;
            }

            if (numTotalSteps < minimalSteps) minimalSteps = numTotalSteps;
        }

        // вывод данных
        System.out.println(minimalSteps);
    }

    static List<String> readFile(String filepath) {
        try {
            Path path = Paths.get(filepath);
            return Files.readAllLines(path);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}