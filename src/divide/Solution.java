package divide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution
{ // init start values, counter and storage for combinations
    private static LinkedList<Double> values = new LinkedList<Double>(Arrays.asList(0.0, 5.0, 10.0, 0.0, 11.0, 14.0,
            13.0, 4.0, 11.0, 8.0, 8.0, 7.0, 1.0, 4.0, 12.0, 11.0));
    private static Integer counter = 0;
    private static HashMap<Integer, LinkedList<Double>> combinationsStorage = new HashMap();

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.countUntilRepeat(values);
        System.out.println(counter);
        for (int i = 1; i <= counter; i++)
        {
            System.out.println(combinationsStorage.get(i));
        }
    }

    public void countUntilRepeat(LinkedList<Double> list)
    { // main recursion loop. Putting all combinations to storage until first match.
        if (combinationsStorage.containsValue(list) || list.isEmpty()) return;
        combinationsStorage.put(++counter, (LinkedList<Double>) list.clone()); // put current combination to storage
        LinkedList<Double> temp = new LinkedList(); // birth of new combination list
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
        if (divideValue==0.0)
        {
            System.out.println("Check countUntilRepeat - divide by zero!");
            divideValue = 1.0; //check to avoid zero divide error
        }
        while (++maxValuePointer<temp.size())
        {// spread divided max value on the rest of values in list
            temp.set(maxValuePointer, temp.get(maxValuePointer)+maxValue/divideValue);
        }
        countUntilRepeat(temp); //attempt to add temp combination & create new
    }


}
