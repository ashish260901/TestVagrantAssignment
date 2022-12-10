import java.util.*;

public class AshishTest {
    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("TOI", 26.0);
        map.put("Hindu", 20.5);
        map.put("ET", 34.0);
        map.put("BM", 10.5);
        map.put("HT", 18.0);

        Scanner sc = new Scanner(System.in);
        double budget = sc.nextDouble();

        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        ArrayList<String> combination = new ArrayList<>();

        // generate all possible addition combinations
        for (String key : map.keySet()) {
            combination.add(key);
            combinations.add(new ArrayList<>(combination));
            combination.remove(key);
        }

        // generate all possible combinations
        for (int i = 0; i < combinations.size(); i++) {
            for (String key : map.keySet()) {
                if (!combinations.get(i).contains(key)) {
                    combination = new ArrayList<>(combinations.get(i));
                    combination.add(key);
                    combinations.add(new ArrayList<>(combination));
                }
            }
        }

        // remove the combinations that are not possible

        for (int i = 0; i < combinations.size(); i++) {
            double sum = 0;
            for (String key : combinations.get(i)) {
                sum += map.get(key);
            }
            if (sum > budget) {
                combinations.remove(i);
                i--;
            }
        }

        // remove the redundant combinations
        for (int i = 0; i < combinations.size(); i++) {
            for (int j = i + 1; j < combinations.size(); j++) {
                if (combinations.get(i).containsAll(combinations.get(j))) {
                    combinations.remove(j);
                    j--;
                }
            }
        }

        // print the combinations
        for (ArrayList<String> comb : combinations) {
            if (comb.size() == 1) {
                continue;
            }
            System.out.println(comb);
        }
    }
}