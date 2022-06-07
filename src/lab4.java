import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class lab4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 2+rand.nextInt(20);
        while (n%2!=0){
            n = 2+rand.nextInt(20);
        }
        int[] tasks = new int[n];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = 1+rand.nextInt(50);
        }
        sort(tasks);
        List<Processor> processors = new ArrayList<>();
        processors.add(new Processor());
        processors.add(new Processor());
        processors = getRaspred(processors,tasks);
        for (int i = 0; i < processors.size(); i++) {
            System.out.println(processors.get(i).tasks);
        }

        processors.add(new Processor());
        processors.add(new Processor());



    }
    public static List<Processor> getRaspred(List<Processor> processors, int[] tasks){
        int j = 0;
        for (int i = 0; i < tasks.length; i++) {
            if(j==1){
                j=0;
            }
            if (processors.get(j).workload< processors.get(j + 1).workload || processors.get(j).workload==0){
                processors.get(j).tasks.add(tasks[i]);
                processors.get(j).getWorkload();
            }else{
                processors.get(j + 1).tasks.add(tasks[i]);
                processors.get(j + 1).getWorkload();
            }
            j++;
        }
        int[] ProcessorsWorkload = new int[processors.size()];
        for (int i = 0; i < processors.size(); i++) {
            ProcessorsWorkload[i] = processors.get(i).workload;
        }
        return processors;
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
    static class Processor{
        List<Integer> tasks = new ArrayList<>();
        int workload = 0;
        public void getWorkload(){
            for (int i = 0; i < this.tasks.size(); i++) {
                workload += tasks.get(i);
            }
        }
    }
}
