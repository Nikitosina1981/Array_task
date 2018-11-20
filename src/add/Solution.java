package add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution
{ // init start values, counter and storage for combinations
    private static ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(0, 5, 10, 0, 11, 14, 13, 4, 11, 8
            , 8, 7, 1, 4, 12, 11));
    private static Integer counter = 0;
    private static HashMap<ArrayList<Integer>, Integer> combinationsStorage = new HashMap<ArrayList<Integer>,
            Integer>();

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.countUntilRepeat(values);
        System.out.println(counter);
        combinationsStorage.keySet().forEach(e->{System.out.println(e.toString());});
    }

    public void countUntilRepeat(ArrayList<Integer> list)
    { // main recursion loop. Putting all combinations to storage until first match.
        if (combinationsStorage.containsKey(list) || list.isEmpty()) return;
        combinationsStorage.put(list, ++counter); // put current combination to storage
        ArrayList<Integer> temp = new ArrayList<Integer>(); // birth of new combination list
        temp.addAll(list);
        int maxValuePointer = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < temp.size(); i++)
        { // let's find maximum value & position
            if (temp.get(i)>maxValue)
            {
                maxValue = temp.get(i);
                maxValuePointer = i;
            }
        }
        temp.set(maxValuePointer, 0); // set max value = 0
        while (++maxValuePointer<temp.size())
        {// add max value to the rest of values in list
            temp.set(maxValuePointer, temp.get(maxValuePointer)+maxValue);
        }
        countUntilRepeat(temp);
    }

}
