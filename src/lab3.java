import java.util.Arrays;
import java.util.Random;

public class lab3 {
    public static void main(String[] args) {

        Random rand = new Random();
        int n = rand.nextInt((3-2)+2)+2;
        int[] task = new int[rand.nextInt(10-7)+7];
        int[][] tasks = new int[5][task.length];
        for (int i = 0; i < task.length; i++) {
            n = rand.nextInt((50-5)+5)+5;
            task[i] = n;
        }
        for (int value : task) {
            tasks[rand.nextInt(5)][(int) (Math.random() * task.length)] = value;
        }
        printMatrix(tasks);

        int[] sumOfRow = getSumOfRow(tasks);

        System.out.println(Arrays.toString(sumOfRow));

        System.out.println();

        int min = getMin(sumOfRow),
                max = getMax(sumOfRow),
                delta = max - min,
                indexMax = 0,
                indexMin = 0;

        System.out.println(max + " "+ min+ " " + delta);
        System.out.println();
        for (int i = 0; i < sumOfRow.length; i++) {
            if (max == sumOfRow[i]){
                indexMax = i;
                break;
            }
        }
        for (int i = 0; i < sumOfRow.length; i++) {
            if (min == sumOfRow[i]){
                indexMin = i;
                break;
            }
        }
        //перекидываем задачу на другой проц
        tasks = getRotation(tasks,indexMax,indexMin,delta);

        printMatrix(tasks);

        System.out.println(Arrays.toString(getSumOfRow(tasks)));
    }

    public static int[][] getRotation(int arr[][],int indexMax,int indexMin,int delta){
        for (int i = 0; i < arr[indexMax].length; i++) {
            if (arr[indexMax][i]<delta){
                int j = 0;
                for (int k = 0; k < arr[indexMin].length; k++) {
                    if (arr[indexMin][k]==0){
                        j = k;
                        break;
                    }
                }
                arr[indexMin][j] = arr[indexMax][i];
                arr[indexMax][i] = 0;
            }
        }
        return arr;
    }
    public static int[] getSumOfRow(int[][] arr){
        int [] sumOfRow = new int[arr.length];
        for (int i = 0; i < sumOfRow.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sumOfRow[i] += arr[i][j];
            }
        }
        return sumOfRow;
    }
    public static int getMin(int[] arr){
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]<min){
                min = arr[i];
            }
        }
        return min;
    }
    public static int getMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }
    public static void printMatrix(int[][] arr){
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}