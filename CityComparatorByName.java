import java.util.Comparator;

public class CityComparatorByName  implements Comparator<City> {

    @Override
    /**
     * Compares the city names of two objects
     * @return      this.name - other.name
     */
    public int compare(City o1, City o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
