import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.Array;
import java.util.*;

public class lab5 {
    public static void main(String[] args) {
        String inf = "inf";
        String[][] tasks = new String[10][10];
        Processor[] Proc = new Processor[10];
        for (int i = 0; i < Proc.length; i++) {
            Proc[i] = new Processor();
        }
        Random random = new Random();
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
                tasks[i][j] = String.valueOf(random.nextInt(100));
            }
        }
        for (int i = 0; i < random.nextInt(100); i++) {
            tasks[random.nextInt(9)][random.nextInt(9)] = inf;
        }
        HashMap<Integer,Integer> map = getSumOfRow(tasks);
        int sizeMap = map.size();
        int min = getMinAtHashMap(getSumOfRow(tasks))[1];
        int minIndex = getMinAtHashMap(getSumOfRow(tasks))[0];
        for (int i = 0; i < tasks.length*tasks[0].length; i++) {
            for (int j = 0; j < Proc.length; j++) {
                if (Proc[j].workload()==0){
                    Proc[j].listOfTask.add(Integer.valueOf(tasks[minIndex][i]));
                }
            }

        }
    }
    public static void printMatrix(String[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static HashMap<Integer,Integer> getSumOfRow(String[][] matrix){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int summ = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (!matrix[i][j].equals("inf")){
                    summ = Integer.parseInt(matrix[i][j]);
                }
            }
            map.put(i, summ);
        }
        return map;
    }
    public static int[] getMinAtHashMap(HashMap<Integer,Integer> map){
        int[] arr = new int[map.size()];
        int[] returnArr = new int[2];
        for (int i = 0; i < map.size(); i++) {
            arr[i] = map.get(i);
        }
        Arrays.sort(arr);
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i)==arr[0]){
                returnArr[0] = i;
                break;
            }
        }
        returnArr[1] = arr[0];
        return returnArr;
    }
}
class Processor{
    List<Integer> listOfTask = new ArrayList<>();
    public int workload(){
        int summ = 0;
        for (int i = 0; i < listOfTask.size(); i++) {
            summ+= listOfTask.get(i);
        }
        return summ;
    }
}
