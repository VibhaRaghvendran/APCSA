/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Vibha Raghvendran
 *	@since November 26 2024
 */
public class SortMethods {

    /**
     *	Bubble Sort algorithm in ascending order
     *	@param arr		array of Integer objects to sort
     */
    private void bubbleSort (Integer [] arr) {
        for (int i = 0; i < arr.length; i++) { // goes through array n times (until sorted)
            for (int j = 1; j < (arr.length - i); j++) { //goes through the unsorted portion of the array
                if (arr[j - 1] > arr[j]) { // if greater, swap
                    arr = swap(arr,j - 1, j);
                }
            }
        }
    }

    /**
     *	Swaps two Integer objects in array arr
     *	@param arr		array of Integer objects
     *	@param x		index of first object to swap
     *	@param y		index of second object to swap
     */
    private Integer[] swap (Integer[] arr, int x, int y) {
        int temp = arr[x]; // stores first value in a variable
        arr[x] = arr[y]; // transfers second value to first index
        arr[y] = temp; // transfers the stored value to the second index

        return arr;
    }

    /**
     *	Selection Sort algorithm in ascending order
     *	@param arr		array of Integer objects to sort
     */
    public void selectionSort(Integer [] arr) {
        for (int i = arr.length; i > 1; i--) { // goes through the array n times (until sorted)
            int max = 0;
            for (int j = 1; j < i; j++) { // finds the maximum element
                if (arr[j] > arr[max]) {
                    max = j; // sets the index of the maximum element
                }
            }

            arr = swap(arr, max, i - 1); // swaps to move the greatest element to the end
        }
    }

    /**
     *	Insertion Sort algorithm in ascending order
     *	@param arr		array of Integer objects to sort
     */
    public void insertionSort(Integer [] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i]; // stores the temporary value

            int a = i; // only sorts the subarray (index set by i)
            while (a > 0 && temp < arr[a - 1]) { // sorts the sub array (comparing elements until it is in ascending order)
                arr[a] = arr[a - 1];
                a--;
            }

            arr[a] = temp; // transfers stored value
        }
    }

    /**
     *	Merge Sort algorithm in ascending order
     *	@param arr		array of Integer objects to sort
     */
    public void mergeSort(Integer [] arr) {
        arr = recursiveSort(arr,0, arr.length - 1);
    }

    private Integer[] recursiveSort (Integer[] a, int from, int to) {
        if (to - from < 2) { // checks if the array's length is 2 or less
            if (to > from && a[to] < a[from]) { // if the array elements are out of order, put them in order
                a = swap (a, to, from);
            }
            else {
                int middle = (from + to)/2; // splits the array in two more components
                // recursion keeps going until all arrays are 2 elements or less
                recursiveSort(a, from, middle);
                recursiveSort(a,middle + 1, to);
                // merges the arrays
                a = merge (a, from, middle, to);
            }
        }

        return a;
    }

    private Integer[] merge (Integer[] a, int from, int middle, int to) {
        Integer[] temp = new Integer[a.length - 1];
        int i = from, j = middle + 1, k = from;

        while (i <= middle && j <= to) {
            if (a[i] < a[j]) {
                temp[k] = a[i];
                i++;
            }
            else {
                temp[k] = a[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            temp[k] = a[i];
            i++;
            k++;
        }
        while (j <= to) {
            temp[k] = a[j];
            j++;
            k++;
        }

        for (k = from; k <= to; k++) {
            a[k] = temp[k];
        }

        return a;
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
        se.run();
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
        bubbleSort(arr);
        System.out.println("Array after sort:");
        printArray(arr);
        System.out.println();

		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
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
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

    }

}


