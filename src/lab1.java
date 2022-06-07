import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
    public class lab1 {
        //Вариант 10
        //Алг 3.1
        //n = 3
        //m, T = 2
        public static void main(String[] args) {
            int n = 3;
            int m = (int) (Math.random() * ((35 - 30) + 1) + 30);//Генерация кол-ва задач
            int[] tasks = new int[m];
            Processor[] processors = new Processor[n];
            for (int i = 0; i < processors.length; i++) {
                processors[i] = new Processor();
            }
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = (int) (Math.random() * ((40 - 35) + 1) + 35);
            }
            sort(tasks);
            System.out.println(Arrays.toString(tasks));
            int j = 0;
            for (int i = 0; i < tasks.length; i++) {
                if(j==2){
                    j=0;
                }
                if (processors[j].workload<processors[j+1].workload||processors[j].workload==0){
                    processors[j].tasks.add(tasks[i]);
                    processors[j].getWorkload();
                }else{
                    processors[j+1].tasks.add(tasks[i]);
                    processors[j+1].getWorkload();
                }
                j++;
                }
            int[] ProcessorsWorkload = new int[processors.length];
            for (int i = 0; i < processors.length; i++) {
                ProcessorsWorkload[i] = processors[i].workload;
            }
            System.out.println(Arrays.toString(ProcessorsWorkload));
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

        static class Processor {
            List<Integer> tasks = new ArrayList<>();
            int workload = 0;
            public void getWorkload() {
                workload = 0;
                for (int i = 0; i < tasks.size(); i++) {
                    workload += tasks.get(i);
                }
            }
        }
    }
