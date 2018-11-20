package divide;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

public class Solution
{ // init start values, counter and storage for combinations
    private static ArrayList<Double> values = new ArrayList<Double>(Arrays.asList(0.0, 5.0, 10.0, 0.0, 11.0, 14.0,
            13.0, 4.0, 11.0, 8.0, 8.0, 7.0, 1.0, 4.0, 12.0, 11.0));
    private static Integer counter = 0;
    private static HashMap<Integer, ArrayList<Double>> combinationsStorage = new HashMap();

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.countUntilRepeat(values);
        System.out.println(counter);
       // for (int i = 1; i <= counter; i++){System.out.println(combinationsStorage.get(i));}
    }

    public void countUntilRepeat(ArrayList<Double> list)
    { // main recursion loop. Putting all combinations to storage until first match.
        if (combinationsStorage.containsValue(list) || list.isEmpty()) return;
        combinationsStorage.put(++counter, (ArrayList<Double>) list.clone()); // put current combination to storage
        ArrayList<Double> temp = new ArrayList(); // birth of new combination list
        temp.addAll(list);
        int maxValuePointer = 0;
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < temp.size(); i++)
        { // let's find maximum value & position
            if (temp.get(i)>maxValue)
            {
                maxValue = temp.get(i);
                maxValuePointer = i;
            }
        }
        temp.set(maxValuePointer, 0.0); // set max value in list = 0
        double divideValue = (double)(temp.size()-maxValuePointer-1); // by how much we should divide max value
        while (++maxValuePointer<temp.size() && divideValue>0.0)
        {// spread divided max value on the rest of values in list
            temp.set(maxValuePointer, temp.get(maxValuePointer)+maxValue/divideValue);
        }
        countUntilRepeat(temp); //attempt to add temp combination & create new
    }


}
