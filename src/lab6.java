import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static jdk.nashorn.internal.objects.Global.print;

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

    public static boolean make_probability(double probability_value) {
        probability_value = probability_value * 100;
        int random_value = (int) (Math.random() * ((100 - 1) - 1) - 1);
        return random_value <= probability_value;
    }

        public static List<Individual> make_pair_crossing (List<Individual> individuals_array, double pk){
        List<Individual> pair_crossing = new ArrayList<>();
        while (pair_crossing.size() != 2) {
            for (Individual i : individuals_array){
                if (make_probability(pk)){
                    if ((pair_crossing.size() == 1) && (pair_crossing.get(0).equals(i))){
                        continue;
                    }
                        pair_crossing.add(i);
                }
            }
        }
        System.out.println("Пара для размножения");
        for (Individual w : pair_crossing) {
            w.print();
        }
        return pair_crossing;
}
    public static List<Individual>  mutation(List<Individual> pair_result){
        System.out.println("---------------------------");
        System.out.println("Мутация");
        for (Individual individual : pair_result) {
            print("---------------------------");
            if (!make_probability(pm)) {
            }
            else{
                int mutation_point = Math.random()*individuals_array - 1);
                print("Индекс мутированного элемента", mutation_point);
                mutation_index = random.randint(0, 7)
                print("Разряд, для инверсии(с 0):", mutation_index)
                mutation_element = bin(individual.value[mutation_point]).replace("0b", "")
                while (len(mutation_element) != 8):
                mutation_element = "0" + mutation_element
                print("Элемент в двоичной системе исчисления:", mutation_element)
                mutation_element_result = ""
                for q in range(0, len(mutation_element)):
                if ((q == mutation_index) and(mutation_element[q] == "0")):
                mutation_element_result = mutation_element_result + "1"
                continue elif ((q == mutation_index) and(mutation_element[q] == "1")):
                mutation_element_result = mutation_element_result + "0"
                continue
            else:
                mutation_element_result = mutation_element_result + mutation_element[q]
                print("Элемент после мутации:", mutation_element_result)
                mutation_element = int(mutation_element_result, base = 2)
                (individual.value)[mutation_point] = mutation_element
            }
            }
            print("Пара после мутации:")
            for q in pair_result:
            q.print()
            return pair_result
        }

    def tourney(individuals_array,best_new_individuals):
    print("---------------------------")
    print("Турнир")
    print("---------------------------")
    print("Исходное поколение:")
    for i in individuals_array:
            i.print()
    print("---------------------------")
    print("Новые особи:")
    for j in best_new_individuals:
            j.print()
            for x in range(0,len(individuals_array)):
            if (individuals_array[x].phenotype>best_new_individuals[x].phenotype):
    individuals_array[x]=best_new_individuals[x]
    print("Результат турнира(новое поколение):")
    for q in individuals_array:
            q.print()
    print("---------------------------")
    return  individuals_array

    def reproduction(individuals_array,pk,pm,intervals_array,kpovtor):
    best_individuals_in_generation = []


            while(True):
    best_new_individuals = []
            if (len(best_individuals_in_generation)>=kpovtor):
    phenotypes =[]
            for t in best_individuals_in_generation:
            phenotypes.append(t.phenotype)
    counter = Counter(phenotypes)
    index_dop = phenotypes[-1]
            if (counter[index_dop]==kpovtor):
            break
            for i in individuals_array:
    pair_result = []
    pair_crossing = make_pair_crossing(individuals_array,pk)
    split_point = random.randint(1,len(individuals_array)-1)
    print("Точка разбиения:",split_point)
    p1 = Individual(pair_crossing[0].value[:split_point]+pair_crossing[1].value[split_point:])
    p2 = Individual(pair_crossing[1].value[:split_point]+pair_crossing[0].value[split_point:])
    print(p1.value)
    print(p2.value)
            pair_result.append(p1)
            pair_result.append(p2)
    pair_result = make_phenotype(pair_result, intervals_array, array_start)
    pair_result = mutation(pair_result)
    pair_result = make_phenotype(pair_result,intervals_array,array_start)
            if (pair_result[0].phenotype>pair_result[1].phenotype):
            best_new_individuals.append(pair_result[1])
            else:
            best_new_individuals.append(pair_result[0])
    individuals_array = tourney(individuals_array,best_new_individuals)
    best_individual = individuals_array[0]
            for individual in individuals_array:
            if individual.phenotype<best_individual.phenotype:
    best_individual = individual
        best_individuals_in_generation.append(best_individual)
    print("Лучшие особи в поколениях:")
    for y in best_individuals_in_generation:
            y.print()

        }
    }
}
class Individual{
    List<Integer> value;
    int phenotype = -1;
    public Individual(ArrayList<Integer> value){
        this.value = value;
    }
    public void print(){
        System.out.println(this.value +" "+this.phenotype);
    }
}
