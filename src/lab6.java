import java.util.*;

public class lab6 {
    static int m,n,a,b,kpovtor,individual_value;
    static double pk,pm;
    static int[] array_start;
    static List<Integer> intervals_array;
    static List<Individual> individuals_array;
    static List<Individual> phenotype;

    public static void main(String[] args) {
    initValue();
    reproduction(individuals_array,pk,pm,intervals_array,kpovtor);
}


public static void initValue(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число заданий m");
        m = scanner.nextInt();
        System.out.println("Введите число процессоров n");
        n = scanner.nextInt();
        System.out.println("Введите начало диапазона для заданий");
        a = scanner.nextInt();
        System.out.println("Введите конец диапазона для заданий");
        b = scanner.nextInt();
        System.out.println("Введите вероятность кроссовера");
        pk = scanner.nextDouble();
        System.out.println("Введите вероятность мутации");
        pm = scanner.nextDouble();
        System.out.println("Введите количество раз, когда лучшая особь повторяется в поколении");
        kpovtor = scanner.nextInt();
        System.out.println("Введите количество особей в начальном поколении");
        individual_value = scanner.nextInt();
        array_start = new int[m];
        for (int i = 0; i < array_start.length; i++) {
            array_start[i] =(int) (Math.random()*((b-a)-1)-a);
        }
        intervals_array = make_interval_array(n);
        individuals_array = make_first_generation(m,individual_value);
        phenotype = make_phenotype(individuals_array,intervals_array,array_start);
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
            System.out.println("---------------------------");
            if (make_probability(pm)) {
                int mutation_point = (int) (Math.random() * (individuals_array.size() - 1) - 1);
                System.out.println("Индекс мутированного элемента " + mutation_point);
                int mutation_index = (int) ((Math.random() * 6) - 1);
                System.out.println("Разряд, для инверсии(с 0): " + mutation_index);
                String mutation_element = Integer.toBinaryString(individual.value.get(mutation_point));
                while (mutation_element.length() != 8) {
                    mutation_element = "0" + mutation_element;
                    System.out.println("Элемент в двоичной системе исчисления: " + mutation_element);
                    String mutation_element_result = "";
                    for (int q = 0; q < mutation_element.length(); q++) {
                        if ((q == mutation_index) && (mutation_element.charAt(q) == '0')) {
                            mutation_element_result = mutation_element_result + "1";
                        } else if ((q == mutation_index) && (mutation_element.charAt(q) == '1')) {
                            mutation_element_result = mutation_element_result + "0";
                        } else mutation_element_result = mutation_element_result + mutation_element.charAt(q);
                    }
                    System.out.println("Элемент после мутации:" + mutation_element_result);
                    mutation_element = String.valueOf(Integer.parseInt(mutation_element_result, 2));
                    individual.value.set(mutation_point,Integer.parseInt(mutation_element));
                }
            }
        }
            System.out.println("Пара после мутации: ");
            for(Individual q : pair_result) q.print();
            return pair_result;
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
