public class lab6 {
//вариант 11
//n = 3
//m = 20...25 T = 20...35
public static void main(String[] args) {
    int[] tasks = new int[(int) (Math.random()*(25-20)+20)];
    for (int i = 0; i < tasks.length; i++) {
        tasks[i] = (int) (Math.random()*(35-20)+20);
    }
    
}
}
