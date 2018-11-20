package add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution
{ // init start values, counter and storage for combinations
    private static ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(0, 5, 10, 0, 11, 14, 13, 4, 11, 8
            , 8, 7, 1, 4, 12, 11));
    private static Integer counter = 0;
    private static HashMap<Integer, ArrayList<Integer>> combinationsStorage = new HashMap();

    public static void main(String[] args)
    {
        while (!combinationsStorage.containsValue(values))
        {// generate combinations until first match
            countUntilRepeat();
        }
        // printing results
        System.out.println("Total cycles: "+counter);
        combinationsStorage.entrySet().forEach(e->{
            if (e.getValue().equals(values)) System.out.println("Cycle length: "+(counter-e.getKey()+1));
        });
    }

    public static void countUntilRepeat()
    {
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.addAll(values);
        combinationsStorage.put(++counter,tmp); // put current combination to storage
        int maxValuePointer = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < values.size(); i++)
        { // let's find maximum value & position
            if (values.get(i)>maxValue)
            {
                maxValue = values.get(i);
                maxValuePointer = i;
            }
        }
        values.set(maxValuePointer, 0); // replace max value with 0 in list
        while (maxValue>0)
        {// spread max value according to task logic
            if (++maxValuePointer==values.size()) maxValuePointer=0;
            values.set(maxValuePointer, values.get(maxValuePointer)+1);
            maxValue--;
        }
    }

}
