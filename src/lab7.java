import java.util.*;

public class lab7 {
    static List<Individual> best_crossover, phenotype, individuals_array, IndividualOfMutation;
    static int m,n,a,b,kpovtor,individual_value,counterPok;
    static double pk,pm;
    static int[][] array_start;
    static List<Integer> intervals_array;

    public static void main(String[] args) {
        initValue();
        best_crossover = new ArrayList<>();
        IndividualOfMutation = new ArrayList<>();
        while (best_crossover.size()==0){
            intervals_array = make_interval_array(n);
            individuals_array = make_first_generation(m,individual_value,intervals_array,array_start);
            phenotype = make_phenotype(individuals_array,intervals_array,array_start);
            reproduction(individuals_array,pk,pm,intervals_array,kpovtor);
        }
        System.out.println();
        System.out.println("Лучший кроссовер");
        for (Individual individual : best_crossover) {
            individual.print();
        }
        System.out.println();
        System.out.println("Особи, мутация которых привела к улучшению фенотипа");
        for (Individual individual : IndividualOfMutation) {
            individual.print();
        }
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
        array_start = new int[m][n];
        for (int i = 0; i < array_start.length; i++) {
            for (int j = 0; j < array_start[i].length; j++) {
                array_start[i][j] = (int) (Math.random()*((b-a)+1)+a);
            }
        }
        for (int i = 0; i <array_start.length ; i++) {
            for (int j = 0; j <array_start[i].length ; j++) {
                System.out.print(array_start[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println("Границы интервалов");
        intervals_array = make_interval_array(n);
        individuals_array = make_first_generation(m,individual_value, intervals_array,array_start);
        phenotype = make_phenotype(individuals_array,intervals_array,array_start);
    }

    public static List<Integer> make_interval_array(int n){
        List<Integer> intervals_array = new ArrayList<>();
        int interval_value = 0;
        int interval = 255 / n;
        while(intervals_array.size()<=n-1){
            intervals_array.add(interval_value);
            interval_value += interval;
        }
        intervals_array.add(interval_value+(255%n));
        System.out.println(intervals_array);
        return intervals_array;
    }
    public static List<Individual> make_first_generation(int m,int individual_value, List<Integer> intervals_array, int[][] array){
        List<Individual> individuals_array =new ArrayList<>();
        for (int i = 0; i<individual_value; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                temp.add((int) (Math.random() * (255)+1));
            }
            individuals_array.add(new Individual(temp));
        }
        make_phenotype(individuals_array,intervals_array,array);
        System.out.println("Первое поколение:");
        for (Individual q :individuals_array){
            System.out.println(q.value);
        }
        System.out.println("---------------------------");
        return individuals_array;
    }
    public static List<Individual> make_phenotype(List<Individual> individuals_array,List<Integer>intervals_array,int [][]array){
        for (int x = 0; x < individuals_array.size(); x++) {
            int []interval_division = new int[intervals_array.size()-1];
            for (int i = 0; i < intervals_array.size()-1; i++) interval_division[i] = 0;
            for (int y = 0; y < individuals_array.get(x).value.size(); y++) {
               int t = 0;
               while (intervals_array.get(t)<individuals_array.get(x).value.get(y)) t++;
               interval_division[t-1] += array[y][t-1];
               individuals_array.get(x).tasks.add(array[y][t-1]);
            }
            Arrays.sort(interval_division);
            individuals_array.get(x).phenotype = interval_division[interval_division.length-1];
        }
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
                if (pair_crossing.size()==2) break;
                if (make_probability(pk)){
                    if ((pair_crossing.size() == 1) && (pair_crossing.get(0)==i)){
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
    public static void mutation(List<Individual> pair_result, List<Integer> intervals_array, int[][] array){
        Random rand = new Random();
        System.out.println("---------------------------");
        System.out.println("Мутация");
        for (Individual individual : pair_result) {
            System.out.println("---------------------------");
            if (make_probability(pm)) {
                int mutation_point = rand.nextInt(individuals_array.size()-1);
                System.out.println("Элемент на замену: " + mutation_point);
                StringBuilder mutation_element = new StringBuilder(Integer.toBinaryString(individual.value.get(mutation_point)));
                int mutation_index = rand.nextInt(mutation_element.length()-1);
                System.out.println("Разряд для замены: " + mutation_index);
                int mutation_index2 = rand.nextInt(mutation_element.length()-1);
                while (mutation_index==mutation_index2) {
                    mutation_index2 = rand.nextInt(mutation_element.length()-1);
                }
                System.out.println("Разряд для замены2: " + mutation_index2);

                while (mutation_element.length()!=8){
                    mutation_element.insert(0, "0");
                    System.out.println("Элемент в двоичной системе исчисления до преобразований: " + mutation_element);
                    StringBuilder mutation_element_result = new StringBuilder();
                    for (int q = 0; q < mutation_element.length(); q++) {
                        if ((q == mutation_index)) {
                            mutation_element_result.append(mutation_element.charAt(mutation_index2));
                        } else if ((q == mutation_index2)) {
                            mutation_element_result.append(mutation_element.charAt(mutation_index));
                        } else mutation_element_result.append(mutation_element.charAt(q));
                    }
                    System.out.println("Элемент после мутации:" + mutation_element_result);
                    individual.value.set(mutation_point,Integer.parseInt(mutation_element_result.toString(), 2));
                }
            }
        }
        System.out.println("Пара после мутации: ");
        make_phenotype(pair_result, intervals_array, array);
        for(Individual q : pair_result) q.print();
    }



    public static List<Individual> selection_sort(List<Integer> phenotypes, List<Individual> individuals){
        List<Individual> resArr = new ArrayList<>();
        for (int i = 0; i < phenotypes.size(); i++) {
            int min = i;
            for (int j = i+1; j < phenotypes.size(); j++){
                if (phenotypes.get(j)>phenotypes.get(min)){
                    min = j;
                }
            }
            int tempForArr = phenotypes.get(min);
            phenotypes.set(min,phenotypes.get(i));
            phenotypes.set(i, tempForArr);
            Individual tempForArray = individuals.get(min);
            individuals.set(min,individuals.get(i));
            individuals.set(i, tempForArray);
        }
        int index = individuals.size()-1;
        while (index>=0){
            resArr.add(individuals.get(index));
            index--;
        }
        return resArr;
    }

    public static void tourney(List<Individual> individuals_array, List<Individual> best_new_individuals) {
        List<Individual> generation = new ArrayList<>();
        List<Integer> generation_phenotype = new ArrayList<>();
        System.out.println("---------------------------" + "\nОтбор" + "\n---------------------------" + "\nИсходное поколение:");
        System.out.println("Исходное поколение:");
        for (Individual i : individuals_array){
            generation.add(i);
            generation_phenotype.add(i.phenotype);
            i.print();
        }
        System.out.println("---------------------------" +"\nНовые особи:");
        for (Individual i : best_new_individuals){
            generation.add(i);
            generation_phenotype.add(i.phenotype);
            i.print();
        }
        List<Individual> array_sort = selection_sort(generation_phenotype,generation);
        counterPok++;
        System.out.println("Результат отбора(Поколение " + counterPok+ "):");
        int size = individuals_array.size();
        individuals_array.clear();
        for (int i = 0; i < size; i++) individuals_array.add(array_sort.get(i));
        for (Individual q : individuals_array){
            q.print();
        }
        System.out.println("---------------------------");
    }


    public static void reproduction(List<Individual> individuals_array,double pk,double pm,List<Integer> intervals_array,int kpovtor){
        List<Individual> best_individuals_in_generation = new ArrayList<>();
        Random random = new Random();
        while(true) {
            List<Individual> best_new_individuals = new ArrayList<>();
            if (best_individuals_in_generation.size() >= kpovtor) {
                List<Integer> phenotypes = new ArrayList<>();
                for(Individual t : best_individuals_in_generation) {
                    phenotypes.add(t.phenotype);
                }
                Counter counter = new Counter(phenotypes);
                int index_dop = phenotypes.get(phenotypes.size()-1);
                if (counter.getCount(index_dop) == kpovtor) break;
            }
            for(Individual i : individuals_array) {
                List<Integer> p1 = new ArrayList<>();
                List<Integer> p2 = new ArrayList<>();
                List<Individual> pair_result = new ArrayList<>();
                List<Individual> pair_crossing = make_pair_crossing(individuals_array, pk);
                int split_point = 1+random.nextInt(i.value.size()-1);
                int split_point2 = 1 + random.nextInt(i.value.size()-1);
                while (split_point==split_point2) split_point2 = (int) (Math.random()*((individuals_array.size()-1))-1);
                System.out.println("Точка разбиения №1: " +  split_point);
                System.out.println("Точка разбиения №2: " +  split_point2);
                if (split_point<split_point2){
                    for (int j = 0; j < split_point; j++) p1.add(pair_crossing.get(0).value.get(j));
                    for (int j = split_point; j < split_point2; j++) p1.add(pair_crossing.get(1).value.get(j));
                    for (int j = split_point2; j < pair_crossing.get(0).value.size(); j++) p1.add(pair_crossing.get(0).value.get(j));

                    for (int j = 0; j < split_point; j++) p2.add(pair_crossing.get(1).value.get(j));
                    for (int j = split_point; j <split_point2 ; j++) p2.add(pair_crossing.get(0).value.get(j));
                    for (int j = split_point2; j < pair_crossing.get(1).value.size(); j++) p2.add(pair_crossing.get(1).value.get(j));
                }else {
                    for (int j = 0; j < split_point2; j++) p1.add(pair_crossing.get(0).value.get(j));
                    for (int j = split_point2; j < split_point; j++) p1.add(pair_crossing.get(1).value.get(j));
                    for (int j = split_point; j < pair_crossing.get(0).value.size(); j++) p1.add(pair_crossing.get(0).value.get(j));

                    for (int j = 0; j < split_point2; j++) p2.add(pair_crossing.get(1).value.get(j));
                    for (int j = split_point2; j < split_point; j++) p2.add(pair_crossing.get(0).value.get(j));
                    for (int j = split_point; j < pair_crossing.get(1).value.size(); j++) p2.add(pair_crossing.get(1).value.get(j));
                }
                System.out.println(p1);
                System.out.println(p2);
                pair_result.add(new Individual(p1));
                pair_result.add(new Individual(p2));
                make_phenotype(pair_result, intervals_array, array_start);
                for (Individual j : pair_result) j.print();
                if (pair_result.get(0).phenotype<pair_crossing.get(0).phenotype && pair_result.get(0).phenotype<pair_crossing.get(1).phenotype &&
                        pair_result.get(1).phenotype<pair_crossing.get(0).phenotype && pair_result.get(1).phenotype<pair_crossing.get(1).phenotype){
                    best_crossover.add(pair_result.get(0));
                    best_crossover.add(pair_result.get(1));
                }else if(pair_result.get(0).phenotype<pair_crossing.get(0).phenotype && pair_result.get(0).phenotype<pair_crossing.get(1).phenotype){
                    best_crossover.add(pair_result.get(0));
                }else if (pair_result.get(1).phenotype<pair_crossing.get(0).phenotype && pair_result.get(1).phenotype<pair_crossing.get(1).phenotype){
                    best_crossover.add(pair_result.get(1));
                }
                int[] phenotypesBeforeMutation = new int[pair_result.size()];
                for (int j = 0; j < phenotypesBeforeMutation.length; j++) {
                    phenotypesBeforeMutation[j] = pair_result.get(j).phenotype;
                }
                mutation(pair_result,intervals_array,array_start);
                make_phenotype(pair_result, intervals_array, array_start);
                for (int j = 0; j < phenotypesBeforeMutation.length; j++) {
                    if (phenotypesBeforeMutation[j]>pair_result.get(j).phenotype){
                        IndividualOfMutation.add(pair_result.get(j));
                    }
                }
                if (pair_result.get(0).phenotype > pair_result.get(1).phenotype)best_new_individuals.add(pair_result.get(1));
                else best_new_individuals.add(pair_result.get(0));
            }
            tourney(individuals_array, best_new_individuals);
            Individual best_individual = individuals_array.get(0);
            for (Individual individual : individuals_array)
                if (individual.phenotype<best_individual.phenotype) best_individual = individual;
            best_individuals_in_generation.add(best_individual);
            System.out.println("Лучшие особи в поколениях:");
            for(Individual y : best_individuals_in_generation) y.print();
        }
    }

    static class Counter{
        List<Integer> data;
        public Counter(List<Integer> data){
            this.data = data;
        }
        public int getCount(int key){
            int counter = 0;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i)==key) counter++;
            }
            return counter;
        }
    }
    static class Individual{
        List<Integer> tasks;
        List<Integer> value;
        int phenotype = -1;
        public Individual(List<Integer> value){
            this.value = value;
            this.tasks = new ArrayList<>();
        }
        public void print() {
            System.out.println(this.value + " " + this.phenotype);
            System.out.println(tasks);
        }
    }
}