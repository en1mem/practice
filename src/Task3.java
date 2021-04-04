import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) throws IOException {

        int[][] nineArray = new int[9][9];


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите 9 чисел, 9 раз, строчку за строчкой");
        System.out.println("Пример: ");
        System.out.println("123456789");
        System.out.println("987654321");

        for (int i = 0; i < 9; i++) {
            String line = bufferedReader.readLine();
            char[] charArray = line.toCharArray();

            for (int j = 0; j < charArray.length; j++) {
                nineArray[i][j] = charArray[j] - '0';
            }
        }


//        for (int i = 0; i < nineArray.length; i++) {
//            for (int j = 0; j < nineArray[i].length; j++) {
//                nineArray[i][j] = 10 + (int) (Math.random() * 50);
//            }
//        }

        System.out.println("Созданная матрица 9 на 9");
        for (int[] value : nineArray) {
            System.out.println(Arrays.toString(value));
            System.out.println();
        }

        System.out.println();
        System.out.println();


        System.out.println("Результирующая матрица 9 на 9");
        int[][] oneOrNullArray = createNewArray(nineArray);

        for (int[] ints : oneOrNullArray) {
            System.out.println(Arrays.toString(ints));
            System.out.println();
        }
    }

    private static int[][] createNewArray(int[][] nineArray) {
        int [][] result = new int[9][9];

        for (int i = 0; i < nineArray.length; i++) {
            for (int j = 0; j < nineArray[i].length; j++) {
                int digitResult = 0;
                if (nineArray[i][j] > nineArray[i][i]) {
                    digitResult = 1;
                }
                result[i][j] = digitResult;
            }
        }

        return result;
    }

}
