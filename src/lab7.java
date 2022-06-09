import java.util.List;

public class lab7 {
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
        List<Integer> value;
        int phenotype = -1;
        public Individual(List<Integer> value){
            this.value = value;
        }
        public void print(){
            System.out.println(this.value +" "+this.phenotype);
        }
    }
}
