import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Вариант 12
//Алгоритм 3 2
//n = 10
//m = 6-15
//T = 1-25
public class lab2 {
    public static void main(String[] args) {
        int m = 10;
        Random rand = new Random();
        int n = rand.nextInt((15-6)+1)+6;
        int[][] workload = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                workload[i][j] = rand.nextInt((25-1)+1)+1;
            }
        }
        System.out.println("Матрица рабочих процессов");
        for (int i = 0; i < workload.length; i++) {
            System.out.println(Arrays.toString(workload[i]));
        }
        int [] sumRow = new int[n];
        for (int i = 0; i < sumRow.length; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += workload[i][j];
            }
            sumRow[i] = sum;
        }
        System.out.println("Суммы строк ");
        System.out.println(Arrays.toString(sumRow));
        int[] timeless_row = sumRow.clone();
        sort(timeless_row);
        List<Integer> I = new ArrayList<>();
        for (int i = 0; i < sumRow.length; i++) {
            int rIndex = 0;
            for (int j = 0; j < timeless_row.length; j++) {
                if (sumRow[i]==timeless_row[j]){
                    rIndex = j;
                    break;
                }
            }
            I.add(rIndex);
        }

        System.out.println("Индексы в зависимости от уравнения " + I);

        List<int[]>wokload_sorted = new ArrayList<>();
        for (int i = 0; i < I.size(); i++) {
            wokload_sorted.add(workload[I.get(i)]);
        }

        System.out.println("Новая матрица");
        for (int i = 0; i < wokload_sorted.size(); i++) {
            System.out.println(Arrays.toString(wokload_sorted.get(i)));
        }

        int [][] process_table = new int[n][m];

        for (int i = 0; i < n; i++) {
            int min_in_row = min(wokload_sorted.get(i));
            for (int j = 0; j < m; j++) {
                if (wokload_sorted.get(i)[j]==min_in_row){
                    process_table[i][j] = wokload_sorted.get(i)[j];
                }
            }
        }

        System.out.println("\nМатрица полученная по шагу 2:");
        for (int i = 0; i < process_table.length; i++) {
            System.out.println(Arrays.toString(process_table[i]));
        }

        

    }

    public static int min (int[] arr){
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]<min) min = arr[i];
        }
        return min;
    }

    public static int[] sort(int[] mas){
        boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < mas.length-1; i++) {
                if(mas[i] < mas[i+1]){
                    isSorted = false;

                    buf = mas[i];
                    mas[i] = mas[i+1];
                    mas[i+1] = buf;
                }
            }
        }
        return mas;
    }
}
