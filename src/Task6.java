import pojos.HelpPojoStatistic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task6 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[][] arrays = new int[3][];

        System.out.println("Введите 3 массива, 3 строками. Начиная от сортированного по возрастанию, заканчивая не сортированным");
        System.out.println("Пример: ");
        System.out.println("123456789");
        System.out.println("987654321");
        System.out.println("12473864523765481");

        for (int i = 0; i < 3; i++) {
            String line = bufferedReader.readLine();
            char[] charArray = line.toCharArray();

            int [] tempArray = new int[charArray.length];
            for (int j = 0; j < charArray.length; j++) {
                tempArray[j] = charArray[j] - '0';
            }
            arrays[i] = tempArray;
        }
        for (int[] array : arrays) {
            int[] mergeExample = array.clone();
            int[] heapExample = array.clone();

            //region merge sort
            HelpPojoStatistic mergeStat = new HelpPojoStatistic();

            long beforeSort = System.currentTimeMillis();
            mergeSort(mergeExample, array.length, mergeStat);
            long afterSort = System.currentTimeMillis();

            System.out.println("Отсортированный merge: " + Arrays.toString(mergeExample));
            System.out.println("Потребовалось: " + (afterSort - beforeSort) + " милисекунд");
            System.out.println("Потребовалось: " + mergeStat.getTransferCount() + " пересылок");
            System.out.println("Потребовалось: " + mergeStat.getComparisonsCount() + " сравнений");
            //endregion

            System.out.println();

            //region heap sort
            HelpPojoStatistic heapStat = new HelpPojoStatistic();

            beforeSort = System.currentTimeMillis();
            heapSort(heapExample, heapStat);
            afterSort = System.currentTimeMillis();


            System.out.println("Отсортированный heap: " + Arrays.toString(heapExample));
            System.out.println("Потребовалось: " + (afterSort - beforeSort) + " милисекунд");
            System.out.println("Потребовалось: " + heapStat.getTransferCount() + " пересылок");
            System.out.println("Потребовалось: " + heapStat.getComparisonsCount() + " сравнений");
            //endregion

            System.out.println();
            System.out.println();
        }
    }

    public static void mergeSort(int[] a, int n, HelpPojoStatistic statistic) {
        if (n < 2) {
            statistic.setComparisonsCount(statistic.getComparisonsCount() + 1);
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            statistic.setTransferCount(statistic.getTransferCount() + 1);
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            statistic.setTransferCount(statistic.getTransferCount() + 1);
        }
        mergeSort(l, mid, statistic);
        mergeSort(r, n - mid, statistic);

        merge(a, l, r, mid, n - mid, statistic);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right, HelpPojoStatistic statistic) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                statistic.setTransferCount(statistic.getTransferCount() + 1);
                statistic.setComparisonsCount(statistic.getComparisonsCount() + 1);
            }
            else {
                a[k++] = r[j++];
                statistic.setTransferCount(statistic.getTransferCount() + 1);
                statistic.setComparisonsCount(statistic.getComparisonsCount() + 1);
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            statistic.setTransferCount(statistic.getTransferCount() + 1);
        }
        while (j < right) {
            a[k++] = r[j++];
            statistic.setTransferCount(statistic.getTransferCount() + 1);
        }
    }

    private static void heapSort(int[] arr, HelpPojoStatistic statistic) {
        int n = arr.length;

        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, statistic);
        }
        // Один за другим извлекаем элементы из кучи
        for (int i=n-1; i>=0; i--) {
            // Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            statistic.setTransferCount(statistic.getTransferCount() + 3);

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(arr, i, 0, statistic);
        }
    }

    // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i, что является
    // индексом в arr[]. n - размер кучи
    private static void heapify(int[] arr, int n, int i, HelpPojoStatistic statistic) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2*i + 1; // левый = 2*i + 1
        int r = 2*i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
            statistic.setComparisonsCount(statistic.getComparisonsCount() + 1);
        }
        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
            statistic.setComparisonsCount(statistic.getComparisonsCount() + 1);
        }
        // Если самый большой элемент не корень
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            statistic.setTransferCount(statistic.getTransferCount() + 3);
            statistic.setComparisonsCount(statistic.getComparisonsCount() + 1);
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largest, statistic);
        }
    }

}
