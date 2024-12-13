import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author Vibha Raghvendran
 *	@since December 9 2024
 */
public class Population {

    // List of cities
    private List<City> cities;
    // instance of SortMethods
    private SortMethods sorting;

    // US data file
    private final String DATA_FILE = "usPopData2017";
    // scanners - one for file, one for user input
    private Scanner scanFile, regScan;

    /**
     * Constructor
     */
    public Population () {
        // initializes variables
        regScan = new Scanner(System.in);
        cities = new ArrayList<>();
        sorting = new SortMethods();
        cities = readFile();
//      runs the game
        printIntroduction();
        menuRun();
    }

    /**
     * Main method
     * @param args
     */
    public static void main (String[] args) {
        Population run = new Population();
    }

    /**
     * Reads the file and puts in the values inside ArrayList cities
     * @return ArrayList<City> cities
     */
    public List<City> readFile () {
        File e = new File(DATA_FILE);
        try {
            scanFile = new Scanner(e);
            scanFile.useDelimiter("[\\t*|\\n*|\\r*]");
            while (scanFile.hasNext()) {
                City temp = new City();
                String state = scanFile.next();
                temp.setState(state);
                String name = scanFile.next();
                temp.setName(name);
                temp.setDesignation(scanFile.next());
                temp.setPopulation(scanFile.nextInt());
                cities.add(temp);
                scanFile.next();

            }
        } catch (Exception k) {
            System.out.println();
        }
        scanFile.close();
        return cities;
    }

    /**	Prints the introduction to Population */
    public void printIntroduction() {
        System.out.println("   ___                  _       _   _");
        System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
        System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
        System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
        System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
        System.out.println("           |_|");
        System.out.println();
    }

    /**	Print out the choices for population sorting */
    public void printMenu() {
        System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
        System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
        System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
        System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
        System.out.println("5. Fifty most populous cities in named state");
        System.out.println("6. All cities matching a name sorted by population");
        System.out.println("9. Quit");

    }

    /**
     * Runs the game
     */
    public void menuRun () {
        int choice = 0;
        // while loop to keep the game running until the user quits
        while (choice != 9) {
            while (choice < 1 || (choice > 6 && choice != 9)) {
                printMenu();
                // asks user for choice
                System.out.println("Enter selection:");
                choice = regScan.nextInt();
                // exits if choice is 9
                if (choice == 9) {
                    break;
                }
            }
            // Fifty least populous cities in USA (Selection Sort)
            if (choice == 1) {
                long startMillisec = System.currentTimeMillis(); // measures timer
                sorting.selectionSort(cities); // sorts
                long endMillisec = System.currentTimeMillis();
                // prints out the list of cities
                for (int i = 0; i < 50; i++) {
                    String printed = ((i + 1) + ". " + cities.get(i).toString());
                    System.out.println(printed);
                }
                long time = endMillisec - startMillisec;
                System.out.println();
                System.out.println("Time taken is " + time + " milliseconds."); // prints out timer data
            }
            // Fifty most populous cities in USA (Merge Sort)
            else if (choice == 2) {
                long startMillisec = System.currentTimeMillis(); // measures timer
                cities = sorting.mergeSort1(cities); // sorts
                long endMillisec = System.currentTimeMillis();
                // prints out the list of cities
                for (int i = 0; i < 50; i++) {
                    String printed = ((i+1) + ". " + cities.get(i).toString());
                    System.out.println(printed);
                }
                long time = endMillisec - startMillisec;
                System.out.println();
                System.out.println("Time taken is " + time + " milliseconds."); // prints out timer data
            }
            // First fifty cities sorted by name (Insertion Sort)
            else if (choice == 3) {
                long startMillisec = System.currentTimeMillis(); // measures timer
                sorting.insertionSort(cities); // sorts
                long endMillisec = System.currentTimeMillis();
                // prints out the list of cities
                for (int i = 0; i < 50; i++) {
                    String printed = ((i + 1) + ". " + cities.get(i).toString());
                    System.out.println(printed);
                }
                long time = endMillisec - startMillisec;
                System.out.println();
                System.out.println("Time taken is " + time + " milliseconds."); // prints out timer data
            }
            // Last fifty cities sorted by name descending (Merge Sort)
            else if (choice == 4) {
                long startMillisec = System.currentTimeMillis(); // measures timer
                cities = sorting.mergeSort2(cities); // sorts
                long endMillisec = System.currentTimeMillis();
                // prints out the list of cities
                for (int i = 0; i < 50; i++) {
                    String printed = cities.get(i).toString();
                    System.out.println(printed);
                }
                long time = endMillisec - startMillisec;
                System.out.println();
                System.out.println("Time taken is " + time + " milliseconds."); // prints out timer data
            } else if (choice == 5) {
                String stateChoice = "";
                // asks for user input
                System.out.println("Enter state name (ie. Alabama):");
                stateChoice = regScan.next();
                boolean found = false;
                // checks if state is valid
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getState().equalsIgnoreCase(stateChoice)) {
                        found = true;
                        break;
                    }
                }
                // keeps asking for user input until state is valid
                while (!found) {
                    System.out.println("Enter state name (ie. Alabama):");
                    stateChoice = regScan.next();
                    for (int i = 0; i < cities.size(); i++) {
                        if (cities.get(i).getState().equalsIgnoreCase(stateChoice)) {
                            found = true;
                            break;
                        }
                    }
                }
                // creates a new ArrayList to add all cities in the state
                ArrayList<City> tempCities = new ArrayList<>();
                // reads in data from big list
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getState().equalsIgnoreCase(stateChoice)) {
                        tempCities.add(cities.get(i));
                    }
                }
                long startMillisec = System.currentTimeMillis(); // measures timer
                sorting.bubbleSort(tempCities); // sorts
                long endMillisec = System.currentTimeMillis();
                for (int i = 0; i < 50; i++) {
                    String printed = ((i+1) + ". " + tempCities.get(i).toString());
                    System.out.println(printed);
                }
                long time = endMillisec - startMillisec;
                System.out.println();
                System.out.println("Time taken is " + time + " milliseconds."); // prints out timer data
            } else if (choice == 6) {
                String cityChoice = "";
                // asks for user input
                System.out.println("Enter city name (ie. Fremont):");
                cityChoice = regScan.next();
                boolean found = false;
                // checks if city is valid
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getName().equalsIgnoreCase(cityChoice)) {
                        found = true;
                    }
                }
                // keeps asking for user input until city is valid
                while (!found) {
                    System.out.println("Enter city name (ie. Fremont):");
                    cityChoice = regScan.next();
                    for (int i = 0; i < cities.size(); i++) {
                        if (cities.get(i).getName().equalsIgnoreCase(cityChoice)) {
                            found = true;
                            break;
                        }
                    }
                }
                // creates a new ArrayList to add all cities in the state
                ArrayList<City> tempCities = new ArrayList<>();
                // reads in data from big list
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getName().equalsIgnoreCase(cityChoice)) {
                        tempCities.add(cities.get(i));
                    }
                }
                long startMillisec = System.currentTimeMillis(); // measures timer
                sorting.bubbleSort(tempCities); // sorts
                long endMillisec = System.currentTimeMillis();
                for (int i = 0; i < 50; i++) {
                    if (i == tempCities.size()) { // breaks if the size is less than 50
                        break;
                    }
                    String printed = ((i+1) + ". " + tempCities.get(i).toString());
                    System.out.println(printed);
                }
                long time = endMillisec - startMillisec;
                System.out.println();
                System.out.println("Time taken is " + time + " milliseconds."); // prints out timer data
                // breaks if choice is 9
            } else if (choice == 9) {
                break;
            }
            System.out.println();
            // asks for choice again
            printMenu();
            System.out.println("Enter selection:");
            choice = regScan.nextInt();
        }

        // out of loop means choice is 9:
        System.out.println("Thank you for using Population!");

    }

}
