import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Vibha Raghvendran
 *	@since November 26 2024
 */
public class SortMethods {
    private City[] temp;
    private CityComparatorByName compare = new CityComparatorByName();

    /**
     *	Bubble Sort algorithm in descending order
     *	@param arr		ArrayList of City objects to sort
     */
    public void bubbleSort (List<City> arr) {
        for (int i = 0; i < arr.size(); i++) { // goes through array n times (until sorted)
            for (int j = 1; j < (arr.size() - i); j++) { //goes through the unsorted portion of the array
                if (arr.get(j - 1).compareTo(arr.get(j)) < 0) { // if greater, swap
                    arr = swap(arr,j - 1, j);
                }
            }
        }
    }

    /**
     *	Swaps two City objects in ArrayList arr
     *	@param arr		ArrayList of City objects
     *	@param x		index of first object to swap
     *	@param y		index of second object to swap
     */
    private List<City> swap (List<City> arr, int x, int y) {
        City temp = arr.get(x); // stores first value in a variable
        arr.set(x, arr.get(y)); // transfers second value to first index
        arr.set(y, temp); // transfers the stored value to the second index

        return arr;
    }

    /**
     *	Selection Sort algorithm in ascending order
     *	@param arr		ArrayList of City objects to sort
     */
    public void selectionSort(List<City> arr) {
        for (int i = arr.size(); i > 1; i--) { // goes through the array n times (until sorted)
            int max = 0;
            for (int j = 1; j < i; j++) { // finds the maximum element
                if (arr.get(j).compareTo(arr.get(max)) > 0) {
                    max = j; // sets the index of the maximum element
                }
            }

            arr = swap(arr, max, i - 1); // swaps to move the greatest element to the end
        }
    }

    /**
     *	Insertion Sort algorithm in ascending order
     *	@param arr		ArrayList of City objects to sort
     */
    public void insertionSort(List<City> arr) {
        for (int i = 1; i < arr.size(); i++) {
            City temp = arr.get(i); // stores the temporary value

            int a = i; // only sorts the subarray (index set by i)
            while (a > 0 && compare.compare(temp, arr.get(a - 1)) < 0) { // sorts the sub array (comparing elements until it is in ascending order)
                arr.set(a, arr.get(a - 1));
                a--;
            }

            arr.set(a, temp); // transfers stored value
        }
    }

    /**
     *	Merge Sort algorithm in descending order for population
     *	@param a		ArrayList of City objects to sort
     *  @return a       ArrayList of sorted City objects
     */
    public List<City> mergeSort1(List<City> a) {
        int n = a.size();
        City[] yay = a.toArray(new City[0]);
        temp = new City[n];
        recursiveSort1(yay,0, n - 1);

        a = new ArrayList<>((Arrays.asList(yay)));
        return a;
    }

    /**
     * Helper method for Merge Sort (population) - splits
     * @param a             Array of City objects
     * @param from          starting index
     * @param to            ending index
     */
    private void recursiveSort1 (City[] a, int from, int to) {
        if (to - from < 2) { // base case: 1 or 2 elements
            if (to > from && a[to].compareTo(a[from]) > 0) {
                City aTemp = a[to];
                a[to] = a[from];
                a[from] = aTemp;
            }
        }
        else { // recursive case
            int middle = (from + to)/2;
            recursiveSort1(a, from, middle);
            recursiveSort1(a, middle+1, to);
            //System.out.println("from: " + from + " middle: " + middle + " to: " + to);
            merge1(a, from, middle, to);
        }
    }

    /**
     * Helper method for Merge Sort (population) - merges
     * @param a             Array of City objects
     * @param from          starting index
     * @param middle        middle index (where the two arrays merge)
     * @param to            end index
     */
    private void merge1 (City[] a, int from, int middle, int to) {
        int i = from, j = middle + 1, k = from;
        //System.out.println("i: " + i + " j: " + j + " k: " + k);

        // while both arrays have elements left unprocessed:
        while (i <= middle && j <= to) {
            if (a[i].compareTo(a[j]) > 0) {
                temp[k] = a[i];
                i++;
            }
            else {
                temp[k] = a[j];;
                j++;
            }
            k++;
        }

        // copy tail of the first half into temp
        while (i <= middle) {
            temp[k] = a[i];
            i++;
            k++;
        }

        // copy tail of the second half into temp
        while (j <= to) {
            temp[k] = a[j];
            j++;
            k++;
        }

        // copy back into original array
        for (k = from; k <= to; k++) {
            a[k] = temp[k];
        }
    }

    /**
     *	Merge Sort algorithm in descending order for city names
     *	@param a		ArrayList of City objects to sort
     *  @return a       ArrayList of sorted City objects
     */
    public List<City> mergeSort2(List<City> a) {
        int n = a.size();
        City[] yay = a.toArray(new City[0]);
        temp = new City[n];
        recursiveSort2(yay,0, n - 1);

        a = new ArrayList<>((Arrays.asList(yay)));
        return a;
    }

    /**
     * Helper method for Merge Sort (city names) - splits
     * @param a             Array of City objects
     * @param from          starting index
     * @param to            ending index
     */
    private void recursiveSort2 (City[] a, int from, int to) {
        if (to - from < 2) { // base case: 1 or 2 elements
            if (to > from && compare.compare(a[to], a[from]) > 0) {
                City aTemp = a[to];
                a[to] = a[from];
                a[from] = aTemp;
            }
        }
        else { // recursive case
            int middle = (from + to)/2;
            recursiveSort2(a, from, middle);
            recursiveSort2(a, middle+1, to);
            //System.out.println("from: " + from + " middle: " + middle + " to: " + to);
            merge2(a, from, middle, to);
        }
    }

    /**
     * Helper method for Merge Sort (city names) - merges
     * @param a             Array of City objects
     * @param from          starting index
     * @param middle        middle index (where the two arrays merge)
     * @param to            end index
     */
    private void merge2 (City[] a, int from, int middle, int to) {
        int i = from, j = middle + 1, k = from;
        //System.out.println("i: " + i + " j: " + j + " k: " + k);

        // while both arrays have elements left unprocessed:
        while (i <= middle && j <= to) {
            if (compare.compare(a[i], a[j]) > 0) {
                temp[k] = a[i];
                i++;
            }
            else {
                temp[k] = a[j];;
                j++;
            }
            k++;
        }

        // copy tail of the first half into temp
        while (i <= middle) {
            temp[k] = a[i];
            i++;
            k++;
        }

        // copy tail of the second half into temp
        while (j <= to) {
            temp[k] = a[j];
            j++;
            k++;
        }

        // copy back into original array
        for (k = from; k <= to; k++) {
            a[k] = temp[k];
        }
    }

/*****************************************************************/
/************************* For Testing ***************************/
/*****************************************************************/

    /**
     *	Print an array of Integers to the screen
     *	@param arr		the array of Integers
     */
    public void printArray(Integer[] arr) {
        if (arr.length == 0) System.out.print("(");
        else System.out.printf("( %4d", arr[0]);
        for (int a = 1; a < arr.length; a++) {
            if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
            else System.out.printf(", %4d", arr[a]);
        }
        System.out.println(" )");
    }

    public static void main(String[] args) {
        SortMethods se = new SortMethods();
        //se.run();
    }

    public void run() {
        Integer[] arr = new Integer[10];
        // Fill arr with random numbers
        for (int a = 0; a < 10; a++)
            arr[a] = (int)(Math.random() * 100) + 1;
        System.out.println("\nBubble Sort");
        System.out.println("Array before sort:");
        printArray(arr);
        System.out.println();
        //bubbleSort(arr);
        System.out.println("Array after sort:");
        printArray(arr);
        System.out.println();

        for (int a = 0; a < 10; a++)
            arr[a] = (int)(Math.random() * 100) + 1;
        System.out.println("\nSelection Sort");
        System.out.println("Array before sort:");
        printArray(arr);
        System.out.println();
        //selectionSort(arr);
        System.out.println("Array after sort:");
        printArray(arr);
        System.out.println();

        for (int a = 0; a < 10; a++)
            arr[a] = (int)(Math.random() * 100) + 1;
        System.out.println("\nInsertion Sort");
        System.out.println("Array before sort:");
        printArray(arr);
        System.out.println();
        //insertionSort(arr);
        System.out.println("Array after sort:");
        printArray(arr);
        System.out.println();
//
//
        for (int a = 0; a < 10; a++)
            arr[a] = (int)(Math.random() * 100) + 1;
        System.out.println("\nMerge Sort");
        System.out.println("Array before sort:");
        printArray(arr);
        System.out.println();
        //mergeSort(arr);
        System.out.println("Array after sort:");
        printArray(arr);
        System.out.println();

    }

}
