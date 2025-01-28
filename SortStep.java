import java.util.Random;

class Sorts {
    private int stepCount;

    public Sorts() {
        stepCount = 0;
    }

    public void setStepCount(int count) {
        stepCount = count;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                stepCount++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                stepCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                stepCount++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public void mergeSort(int[] arr, int n) {
        stepCount = 0;
        mergeSortHelper(arr, 0, n - 1);
    }

    private void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            stepCount++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}

public class SortingTest {
    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800};
        Random rand = new Random();
        Sorts sorter = new Sorts();

        System.out.println("| SORT        | 100   | 200   | 400   | 800   |");
        System.out.println("------------------------------------------------");

        for (String method : new String[]{"Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort"}) {
            System.out.printf("| %-11s |", method);
            for (int size : sizes) {
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
                sorter.setStepCount(0);
                switch (method) {
                    case "Bubble Sort": sorter.bubbleSort(arr); break;
                    case "Insertion Sort": sorter.insertionSort(arr); break;
                    case "Selection Sort": sorter.selectionSort(arr); break;
                    case "Merge Sort": sorter.mergeSort(arr, arr.length); break;
                }
                System.out.printf(" %-6d |", sorter.getStepCount());
            }
            System.out.println();
        }
    }
}
