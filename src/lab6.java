import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lab6 {
public static void main(String[] args) {
    int[] tasks = new int[(int) (Math.random()*(25-20)+20)];
    for (int i = 0; i < tasks.length; i++) {
        tasks[i] = (int) (Math.random()*(35-20)+20);
    }

}

        public static List<Integer> make_interval_array(int n){
            List<Integer> intervals_array = new ArrayList<>();
            int interval_value = 0;
            int interval = Math.round(255 / n);
            while(intervals_array.size()<=n-1){
                intervals_array.add(interval_value);
            }

            interval_value = interval_value+interval;
            intervals_array.add(interval_value+(255%n));
            System.out.println((intervals_array));
            return intervals_array;
}
        public static List<Individual> make_first_generation(int m,int individual_value){
            List<Individual> individuals_array =new ArrayList<>();
            for (int i = 0; i<individual_value; i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    temp.add((int) (Math.random() * (254)));
                }
                individuals_array.add(new Individual(temp));
                temp.clear();
            }
            System.out.println("Первое поколение:");
            for (Individual q :individuals_array){
                System.out.println(q.value);
            }
            System.out.println("---------------------------");
            return individuals_array;
}
    public static List<Individual> make_phenotype(List<Individual> individuals_array,List<Integer>intervals_array,int []array){
        System.out.println("Подсчёт фенотипов:");
        for (int x = 0; x < individuals_array.size(); x++) {
            int[] interval_division = new int[intervals_array.size()];
            for (int i = 0; i < intervals_array.size()-1; i++) {
                interval_division[i] = 0;
            }
            for (int y = 0; y<individuals_array.get(x).value.size(); y++){
                int t = 0;
                while (intervals_array.get(t) < individuals_array.get(x).value.get(y)) {
                t = t + 1;
            }
                interval_division[t - 1] = interval_division[t - 1] + array[y];
            }
        Arrays.sort(interval_division);
        individuals_array.get(x).phenotype = interval_division[interval_division.length-1];
        }
        for (Individual i : individuals_array) {
            i.print();
        }
        System.out.println("---------------------------");
        return individuals_array;
}
    static class Individual{
        List<Integer> value;
        int phenotype = -1;
        public Individual(ArrayList<Integer> value){
            this.value = value;
        }
        public void print(){
            System.out.println(this.value +" "+this.phenotype);
        }
    }
}
