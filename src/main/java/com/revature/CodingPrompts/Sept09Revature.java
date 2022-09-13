import java.util.Arrays;
import java.util.List;

public class Sept09Revature {

    public void runTests() {
        /*
        * Given an unsorted array of length N, and we have to find the largest gap
        * between any two elements of the array.
        * In simple words, find max(|Ai-Aj|) where 1 ≤ i ≤ N and 1 ≤ j ≤ N.
        * E.g.
        *      {3, 10, 6, 7} == 7
        *      {-3, -1, 6, 7, 0} == 10
        * 
        * Given an array arr[] of integers, the task is to find the maximum sum sub-array
        * among all the possible sub-arrays.
        * E.g.
        *      {-2, 1, -3, 4, -1, 2, 1, -5, 4} == 6
        *      {2, 2, -2} == 4
        */
       List<Integer> arr1 = Arrays.asList(new Integer[] {3, 10, 6, 7});
       List<Integer> arr2 = Arrays.asList(new Integer[] {-3, -1, 6, 7, 0});
       assert(largestGap(arr1) == 7);
       assert(largestGap(arr2) == 10);

       System.out.println("Finished largest gap tests successfully");

       List<Integer> arr3 = Arrays.asList(new Integer[] {-2, 1, -3, 4, -1, 2, 1, -5, 4});
       List<Integer> arr4 = Arrays.asList(new Integer[] {2, 2, -2});
       assert(maxSumSubArray(arr3) == 6);
       assert(maxSumSubArray(arr4) == 4);
    }

    private Integer largestGap(List<Integer> arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < min)
                min = arr.get(i);
            if (arr.get(i) > max)
                max = arr.get(i);
        }

        return max - min;
    }

    private Integer maxSumSubArray(List<Integer> arr) {
        int res = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            int sum = 0;
            for (int j = i; j < arr.size(); j++) {
                sum += arr.get(j);
                if (sum > res)
                    res = sum;
            }
        }


        return res;
    }
    
}
