import java.util.*;


public class RevatureWeeklies {
    public static void main(String[] args) {

        // Sept02Testing();
        Sept09Testing();
        
        System.out.println("All tests passed successfully!");
    }

    private static void Sept09Testing() {
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

    private static Integer largestGap(List<Integer> arr) {
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

    private static Integer maxSumSubArray(List<Integer> arr) {
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

    private static void Sept02Testing() {
        /*
        Create a function that takes the name of a chess piece, its position and a target position.
        The function should return true if the piece can move to the target and false if it can't.
        The possible inputs are "Pawn", "Knight", "Bishop", "Rook", "Queen" and "King".

        Examples:
        canMove("Rook", "A8", "H8") == true
        canMove("Bishop", "A7", "G1") == true
        canMove("Queen", "C4", "D6") == false

        Notes
        Do not include pawn capture moves and en passant.
        Do not include castling.
        Remember to include pawns' two-square move on the second rank!
        Look for patterns in the movement of the pieces.
        */
        assert(canMove("Rook", "A8", "H8"));
        assert(canMove("Bishop", "A7", "G1"));
        assert(!canMove("Queen", "C4", "D6"));
    }

    private static boolean canMove(String piece, String start, String end) {
        Map<String, Integer> columnToNumberMap = new HashMap<>();
        columnToNumberMap.put("A", 1);
        columnToNumberMap.put("B", 2);
        columnToNumberMap.put("C", 3);
        columnToNumberMap.put("D", 4);
        columnToNumberMap.put("E", 5);
        columnToNumberMap.put("F", 6);
        columnToNumberMap.put("G", 7);
        columnToNumberMap.put("H", 8);
        int startRow = Integer.parseInt(start.substring(1));
        int startColumn = columnToNumberMap.get(start.substring(0, 1));
        int endRow = Integer.parseInt(end.substring(1));
        int endColumn = columnToNumberMap.get(end.substring(0, 1));

        if (startRow > 8 || startRow < 1 || startColumn > 8 || startColumn < 1 || 
                endRow > 8 || endRow < 1 || endColumn > 8 || endColumn < 1) {
                    System.err.println("Invalid starting or ending location");
                    return false;
            }
        switch (piece) {
            case "Pawn":
                return validPawnMove(startRow, startColumn, endRow, endColumn);
            case "Rook":
                return validRookMove(startRow, startColumn, endRow, endColumn);
            case "Knight":
                return validKnightMove(startRow, startColumn, endRow, endColumn);
            case "Bishop":
                return validBishopMove(startRow, startColumn, endRow, endColumn);
            case "Queen":
                return validQueenMove(startRow, startColumn, endRow, endColumn);
            case "King":
                return validKingMove(startRow, startColumn, endRow, endColumn);
            default:
                System.err.println(piece + " is not a valid chess piece");
                return false;
        }
    }

    private static boolean validPawnMove(Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        // Normal 1 space move
        if (startCol == endCol && Math.abs(startRow - endRow) == 1) {
            return true;
        } 
        // Starting 2 space move
        else if (startCol == endCol && ((startRow == 7 && endRow == 5) || (startRow == 2 && endRow == 4)) ) {
            return true;
        }
        // capture or en-passant (commented out due to instruction restrictions)
        // else if (Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1) {
        //     return true;
        // }
        // otherwise an invalid move
        return false;
    }

    private static boolean validRookMove(Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        // basic rook move
        if ((startCol == endCol && startRow != endRow) || (startCol != endCol && startRow == endRow)) {
            return true;
        }
        // invalid move
        return false;
    }

    private static boolean validKnightMove(Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        if ((Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1) ||
            (Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2)) {
                return true;
            }

        return false;
    }

    private static boolean validBishopMove(Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
            return true;
        }

        return false;
    }

    private static boolean validQueenMove(Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        return validRookMove(startRow, startCol, endRow, endCol) || validBishopMove(startRow, startCol, endRow, endCol);
    }

    private static boolean validKingMove(Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) {
            return true;
        }

        return false;
    }

    
}