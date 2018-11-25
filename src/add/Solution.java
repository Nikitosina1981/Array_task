package add;

import java.util.*;

public class Solution
{ // init start values, counter and storage for combinations
    private ArrayList<Integer> values;
    private HashMap<Integer, ArrayList<Integer>> combinationsStorage = new HashMap<>();

    public Solution(ArrayList<Integer> values)
    {
        this.values = values;
    }

    public static void main(String[] args)
    {
        Solution s1 = new Solution(new ArrayList<Integer>(Arrays.asList(2000000000, 0)));
        s1.countUntilRepeat();
    }

    public void countUntilRepeat()
    {
        while (!combinationsStorage.containsValue(values))
        {// generate combinations until first match
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.addAll(values);
            combinationsStorage.put(combinationsStorage.size(), tmp); // put current combination to storage
            int maxValue = Collections.max(values); // Max in values
            int maxValuePointer = values.indexOf(maxValue); // Index of max in values
            values.set(maxValuePointer, 0); // replace max value with 0 in list
            int addToAll = maxValue / values.size(); // amount to add to each element in values
            int rest = maxValue - addToAll * values.size(); // the rest to add after increasing by addToAll
            int restEnd = 0; // How much elements should be increased by 1 from values start
            int restStart = 0; // How much elements should be increased by 1 after maxValuePointer and up to values.size()
            if (values.size() - maxValuePointer <= rest) restStart = rest - (values.size() - maxValuePointer - 1);
            if (restStart == 0) restEnd = maxValuePointer + rest;
            else restEnd = values.size();
            for (int i = 0; i < values.size(); i++)
            {// increasing values
                int a;
                if ((i < restStart) || (i <= restEnd && i > maxValuePointer)) a = 1;
                else a = 0;
                values.set(i, values.get(i) + addToAll + a);
            }
        }
        // printing results
        System.out.println("Total cycles: "+combinationsStorage.size());
        combinationsStorage.entrySet().forEach(e->{
            if (e.getValue().equals(values)) System.out.println("Cycle length: "+(combinationsStorage.size()-e.getKey()+1));
        });
        System.out.println(values);
        System.out.println(combinationsStorage.toString());
    }
}
