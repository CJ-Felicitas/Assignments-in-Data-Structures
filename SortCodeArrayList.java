import java.util.ArrayList;

public class SortCodeArrayList {
    public static void main(String[] args) {


    
        int[] set = {10,8,7,6,4,3,2,1};

        int temp;
        ArrayList list = new ArrayList<>();


        for (int i = 0; i < set.length; i++) {
            for (int j = i + 1; j < set.length; j++) {
                if (set[i] > set[j]) { // swap elements if not in order
                    temp = set[i];
                    set[i] = set[j];
                    set[j] = temp;
                }
            }
        }
   
        for (int i = 0; i < set.length; i++) {
            System.out.println(set[i]);
        }
   
    }
}
