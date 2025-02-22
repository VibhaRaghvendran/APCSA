import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author	Vibha Raghvendran
 *	@since	January 10 2025
 */
public class SortMethods {
    /**
     *	Merge Sort algorithm - in ascending order
     *	@param arr		List of String objects to sort
     */
    public List<String> mergeSort(List<String> arr) {
        int n = arr.size();
        mergeSortRecurse(arr,0, n - 1);

        return arr;
    }

    /**
     *	Recursive mergeSort method.
     *	@param a		Array of String objects to sort
     *	@param from		first index of arr to sort
     *	@param to		last index of arr to sort
     */
    public void mergeSortRecurse(List<String> a, int from, int to) {
        //System.out.println("Merge sort recurse: from: " + from + " to: " + to);
        if (to - from < 2) { // base case: 1 or 2 elements
            if (to > from && a.get(to).compareTo(a.get(from)) < 0) {
                String aTemp = a.get(to);
                a.set(to, a.get(from));
                a.set(from, aTemp);
            }
        }
        else { // recursive case
            int middle = (from + to)/2;
            mergeSortRecurse(a, from, middle);
            mergeSortRecurse(a, middle+1, to);
            //System.out.println("from: " + from + " middle: " + middle + " to: " + to);
            merge(a, from, middle, to);
        }
    }

    /**
     *	Merge two lists that are consecutive elements in array.
     *	@param a		Array of String objects to merge
     *	@param from		first index of first list
     *	@param middle	the last index of the first list;
     *					mid + 1 is first index of second list
     *	@param to		last index of second list
     */
    public void merge(List<String> a, int from, int middle, int to) {
        //System.out.println("Merge : from: " + from + " middle: " + middle + " to: " + to);
        int i = from, j = middle + 1, k = from;
        //System.out.println("i: " + i + " j: " + j + " k: " + k);
        ArrayList<String> temp = new ArrayList<>();

        for (int m = 0; m < a.size(); m++) {
            temp.add(a.get(m));
        }

        // while both arrays have elements left unprocessed:
        while (i <= middle && j <= to) {
            if (a.get(i).compareTo(a.get(j)) < 0) {
                temp.set(k, a.get(i));
                i++;
            }
            else {
                temp.set(k, a.get(j));
                j++;
            }
            k++;
        }

        // copy tail of the first half into temp
        while (i <= middle) {
            temp.set(k, a.get(i));
            i++;
            k++;
        }

        // copy tail of the second half into temp
        while (j <= to) {
            temp.set(k, a.get(j));
            j++;
            k++;
        }

        // copy back into original array
        for (k = from; k <= to; k++) {
            a.set(k, temp.get(k));
        }
    }


    /**
     *	Print an List of Strings to the screen
     *	@param arr		the List of Strings
     */
    public void printArray(List<String> arr) {
        if (arr.size() == 0) System.out.print("(");
        else System.out.printf("( %-15s", arr.get(0));
        for (int a = 1; a < arr.size(); a++) {
            if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
            else System.out.printf(", %-15s", arr.get(a));
        }
        System.out.println(" )");
    }

    /*************************************************************/
    /********************** Test program *************************/
    /*************************************************************/
    private final String FILE_NAME = "randomWords.txt";

    public static void main(String[] args) {
        SortMethods se = new SortMethods();
        se.run();
    }

    public void run() {
        List<String> arr = new ArrayList<String>();
        // Fill List with random words from file
        fillArray(arr);

        System.out.println("\nMerge Sort");
        System.out.println("Array before sort:");
        printArray(arr);
        System.out.println();
        mergeSort(arr);
        System.out.println("Array after sort:");
        printArray(arr);
        System.out.println();
    }

    // Fill String array with words
    public void fillArray(List<String> arr) {
        Scanner inFile = FileUtils.openToRead(FILE_NAME);
        while (inFile.hasNext())
            arr.add(inFile.next());
        inFile.close();
    }
}
