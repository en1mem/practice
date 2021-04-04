import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task5 {
    public static void main(String[] args) throws IOException {
//        int[][] oneOrNullArray = createOneOrNullArray();
        int[][] oneOrNullArray = new int[10][10];

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите 10 строк по 10 раз, формируя тем самым матрицу 10 на 10");
        System.out.println(", где 1 это закрытая клетка, а 0 - открытая.");

        for (int i = 0; i < 10; i++) {
            String line = bufferedReader.readLine();
            char[] charArray = line.toCharArray();

            for (int j = 0; j < charArray.length; j++) {
                oneOrNullArray[i][j] = charArray[j] - '0';
            }
        }

        System.out.println("Incoming story: ");
        String words = bufferedReader.readLine();
//        String words = "This is a story of how a Baggins had an adventure, found himself doing and saying things altogether.";
        System.out.println(words);

        System.out.println();
        System.out.println();

        System.out.println("Encoded story: ");
        System.out.println();
        String[][] encoded = encode(oneOrNullArray, words);
        for (String[] value : encoded) {
            System.out.println(Arrays.toString(value));
            System.out.println();
        }

        System.out.println();
        System.out.println();

        System.out.println("Decoded story: ");
        String decoded = decode(oneOrNullArray, encoded);
        System.out.println(decoded);
    }

    private static int[][] createOneOrNullArray() {
        int[][] result = new int[10][10];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (i < 5 && j < 5) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 1;
                }
            }
        }

        System.out.println("Our beginning key: ");
        System.out.println();

        for (int[] value : result) {
            System.out.println(Arrays.toString(value));
            System.out.println();
        }

        return result;
    }

    private static String decode(int[][] oneOrNullArray, String[][] letters) {
        StringBuilder result = new StringBuilder();

        for (int counter = 0; counter < 4; counter++) {

            for (int i = 0; i < letters.length; i++) {
                for (int j = 0; j < letters[i].length; j++) {
                    if (oneOrNullArray[i][j] == 0) {
                        result.append(letters[i][j]);
                    }
                }
            }

            oneOrNullArray = turnArray90Degree(oneOrNullArray);
        }

        return result.toString();
    }

    private static String[][] encode(int[][] oneOrNullArray, String words) {
        String[][] result = new String[10][10];
        char[] letters = words.toCharArray();

        int count = 0;

        for (int counter = 0; counter < 4; counter++) {

            for (int i = 0; i < oneOrNullArray.length; i++) {
                for (int j = 0; j < oneOrNullArray[i].length; j++) {
                    if (oneOrNullArray[i][j] == 0) {
                        result[i][j] = String.valueOf(letters[count]);
                        count++;
                    }
                }
            }

            oneOrNullArray = turnArray90Degree(oneOrNullArray);
        }


        return result;
    }

    private static int[][] turnArray90Degree(int[][] oneOrNullArray) {

        final int M = oneOrNullArray.length;
        final int N = oneOrNullArray[0].length;
        int[][] result = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[j][M - 1 - i] = oneOrNullArray[i][j];
            }
        }

        return result;
    }
}
