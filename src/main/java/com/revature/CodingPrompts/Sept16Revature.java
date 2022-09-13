import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Sept16Revature {

    public void runTests() {
        /*
         * Create a function that reorders the digits of each numerical element in an array
         * based on ascending (asc) or descending (desc) order.
         * Examples:
         *      reorderDigits({515, 341, 98, 44, 211}, "asc") == {155, 134, 89, 44, 112}
         *      reorderDigits({515, 341, 98, 44, 211}, "desc") == {551, 431, 98, 44, 211}
         *      reorderDigits({63251, 78221}, "asc") == {12356, 12278}
         *      reorderDigits({63251, 78221}, "desc") == {65321, 87221}
         *      reorderDigits({1, 2, 3, 4}, "asc") == {1, 2, 3, 4}
         *      reorderDigits({1, 2, 3, 4}, "desc") == {1, 2, 3, 4}
         * Notes:
         *      Single-digit numbers should be ordered the same regardless of direction.
         *      Numbers in the array should be kept the same order.
         */
        int expected[] = new int[] {155, 134, 89, 44, 112};
        int actual[] = reorderDigits(new int[] {515, 341, 98, 44, 211}, "asc");
        assert(Arrays.equals(expected, actual));
        expected = new int[] {551, 431, 98, 44, 211};
        actual = reorderDigits(new int[] {515, 341, 98, 44, 211}, "desc");
        assert(Arrays.equals(expected, actual));
        expected = new int[] {12356, 12278};
        actual = reorderDigits(new int[] {63251, 78221}, "asc");
        assert(Arrays.equals(expected, actual));
        expected = new int[] {65321, 87221};
        actual = reorderDigits(new int[] {63251, 78221}, "desc");
        assert(Arrays.equals(expected, actual));
        expected = new int[] {1, 2, 3, 4};
        actual = reorderDigits(new int[] {1, 2, 3, 4}, "asc");
        assert(Arrays.equals(expected, actual));
        expected = new int[] {1, 2, 3, 4};
        actual = reorderDigits(new int[] {1, 2, 3, 4}, "desc");
        assert(Arrays.equals(expected, actual));


        /*
         * Write a function that returns true if you can partition an array
         * into one element and the rest, such that this element is equal to the
         * product of all other elements excluding itself.
         * Examples:
         *      canPartition([2, 8, 4, 1]) == true // 8 = 2 x 4 x 1
         *      canPartition([-1, -10, 1, -2, 20]) == false
         *      canPartition([-1, -20, 5, -1, -2, 2]) == true
         * Notes:
         *      The array may contain duplicates.
         *      Multiple solutions can exist, any solution is sufficient to return true.
         */

        assert(canPartition(new int[] {2, 8, 4, 1}));
        assert(!canPartition(new int[] {-1, -10, 1, -2, 20}));
        assert(canPartition(new int[] {-1, -20, 5, -1, -2, 2}));
    }

    private int[] reorderDigits(int[] arr, String order) {
        for (int i = 0; i < arr.length; i++) {
            List<Integer> numbers = new LinkedList<>();
            for (int j = arr[i]; j > 0; j /= 10)
                numbers.add(j % 10);
            if (order.equals("asc")) {
                String num = numbers.stream()
                            .sorted()
                            .map(e -> Integer.toString(e))
                            .collect(Collectors.joining(""));
                arr[i] = Integer.parseInt(num);
            }
            else {
                String num = numbers.stream()
                            .sorted(Collections.reverseOrder())
                            .map(e -> Integer.toString(e))
                            .collect(Collectors.joining(""));
                arr[i] = Integer.parseInt(num);
            }
        }
        // System.out.println(Arrays.toString(arr));
        return arr;
    }

    private boolean canPartition(int[] arr) {
        int mult = 1;
        for (int i = 0; i < arr.length; i++) {
            mult *= arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            if (mult / arr[i] == arr[i]) 
                return true;
        }
        return false;
    }
    
}
