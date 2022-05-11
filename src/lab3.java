import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class lab3 {
    public static void main(String[] args) {
        //Начальные значения
        int n = 4;
        Random rand = new Random();
        int m = (int) (Math.random() * (15 - 6) + 6);
        int[] T = new int[m];
        for (int i = 0; i < T.length; i++) {
            T[i] = (int) (Math.random() * (25 - 1) + 1);
        }

        Processor[] Processors = new Processor[4];
        for (int i = 0; i < Processors.length; i++) {
            Processors[i] = new Processor();
        }

        for (int i = 0; i < T.length; i++) {
            int randProc = (int) (Math.random()*3);
            Processors[randProc].addTask(T[i]);
        }

        for (int i = 0; i < Processors.length; i++) System.out.println(Processors[i].tasks +" "+Processors[i].Workload);
    }


     class Processor{
        private  List<Integer> tasks = new ArrayList<>();
         int Workload = 0;
        public  void addTask(int task){
            tasks.add(task);
            Workload+=task;
        }
    }
}
