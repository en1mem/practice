import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите а1");
        String a1String = bufferedReader.readLine();

        System.out.println("Введите а2");
        String a2String = bufferedReader.readLine();

        System.out.println("Введите а3");
        String a3String = bufferedReader.readLine();

        System.out.println("Введите n");
        String nString = bufferedReader.readLine();

        List<String> digitsInString = Arrays.asList(a1String, a2String, a3String, nString);
        Boolean allRight = checkDigits(digitsInString);

        if (allRight) {
            List<Double> result = new ArrayList<>();

            double a1 = parseDigit(a1String);
            double a2 = parseDigit(a2String);
            double a3 = parseDigit(a3String);
            double N = parseDigit(nString);

            double ak;//элемент
            System.out.println("Последовательность: ");
            for (int i = 1; i <= N; i++) {
                ak = expression(a1, a2, a3, i);
                if (i % 2 == 0) {
                    result.add(ak);
                }
            }
            System.out.println();

            if (isSequence(result)) {
                System.out.println("Причина остановки: четные элементы последовательности сохранили возрастающий порядок");
            } else {
                System.out.println("Причина остановки: последовательность из четных элементов не сохранила порядок");
            }
        } else {
            System.out.println("Введенные числа не соответсвуют формату");
        }
    }

    private static boolean isSequence(List<Double> result) {
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    private static Double parseDigit(String digit) {
        return Double.parseDouble(digit);
    }

    private static Boolean checkDigits(List<String> digits) {
        for (String digit : digits) {
            if (isDigit(digit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double expression(double a1, double a2, double a3, int i) {
        double ak;//Нынешний элемент последовательности
        if (i == 1) {
            ak = a1;
        } else if (i == 2) {
            ak = a2;
        } else if (i == 3) {
            ak = a3;
        } else {
            ak = 13 * expression(a1, a2, a3, i - 1) - 10 * expression(a1, a2, a3, i - 2) + expression(a1, a2, a3, i - 3);//находим нынешний элемент последовательности
        }
        return (ak);//возвращаем нынешний элемент последовательности
    }
}
