import java.util.Arrays;
import java.util.Random;

public class lab3 {
    public static void main(String[] args) {
        int[][] tasks = new int[5][5];
        Random rand = new Random();
        int n = rand.nextInt((3-2)+2)+2;
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
                tasks[i][j] = rand.nextInt((25-10)+10)+10;
            }
        }
        System.out.println(Arrays.deepToString(tasks));
        int[] sumOfRow = new int[tasks.length];
        for (int i = 0; i < sumOfRow.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
                sumOfRow[i] += tasks[i][j];
            }
        }
        System.out.println(Arrays.toString(sumOfRow));
    }
}
