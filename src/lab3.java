import java.util.Arrays;
import java.util.Random;

public class lab3 {


    public static void main(String[] args) {

        int[][] tasks = getTasksMatrix();
        printMatrix(tasks);
        int min = getMin(getSumOfRow(tasks)),
                max = getMax(getSumOfRow(tasks)),
                delta = max - min,
                indexMax = getIndexMax(getSumOfRow(tasks),getMax(getSumOfRow(tasks))),
                indexMin = getIndexMin(getSumOfRow(tasks),getMin(getSumOfRow(tasks)));
                int oldDelta = 0;

                while (doAlgoritm(tasks[indexMax],delta)){
                if (oldDelta==delta){
                break;
                    }
            delta = getMax(getSumOfRow(tasks)) - getMin(getSumOfRow(tasks));
            indexMax = getIndexMax(getSumOfRow(tasks),getMax(getSumOfRow(tasks)));
            indexMin = getIndexMin(getSumOfRow(tasks),getMin(getSumOfRow(tasks)));
            getRotation(tasks, indexMax, indexMin, delta);
            printMatrix(tasks);
            oldDelta = delta;
        }


    }
    public static boolean doAlgoritm(int[] arr, int delta){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<delta){
                return true;
            }
        }
        return false;
    }
    public static void getRotation(int[][] arr, int indexMax, int indexMin, int delta){
        for (int i = 0; i < arr[indexMax].length; i++) {
            if (arr[indexMax][i]<delta){
                for (int k = 0; k < arr[indexMin].length; k++) {
                    if (arr[indexMin][k]==0){
                        arr[indexMin][k] = arr[indexMax][i];
                        break;
                    }
                }
                arr[indexMax][i] = 0;
                break;
            }
        }
    }
    public static int[][] getTasksMatrix(){
        Random rand = new Random();
        int n = rand.nextInt((3-2)+2)+2;
        int[] task = new int[20];

        int[][] tasks = new int[5][20];
        for (int i = 0; i < task.length; i++) {
            n = rand.nextInt((50-5)+5)+5;
            task[i] = n;
        }
        System.out.println(Arrays.toString(task));
        for (int value : task) {
            int u = rand.nextInt(5);
            int l  = (int) (Math.random() * tasks[u].length);
            if(tasks[u][l]== 0 && l < tasks[u].length){
                tasks[u][l] = value;
            }
        }
        return tasks;
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
        System.out.println(Arrays.toString(getSumOfRow(arr)));
        System.out.println();
    }
    public static int getIndexMax(int[] arr, int max){
        for (int i = 0; i < arr.length; i++) {
            if (max == arr[i]){
                return i;
            }
        }
        return 0;
    }
    public static int getIndexMin(int[] arr, int min){
        for (int i = 0; i < arr.length; i++) {
            if (min == arr[i]){
                return i;
            }
        }
        return 0;
    }
}