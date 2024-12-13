/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author Vibha Raghvendran
 *	@since December 6 2024
 */
public class City implements Comparable<City> {

    // fields
    private String state, name, designation;
    private int population;

    // constructor
    public City () {
        state = "";
        name = "";
        designation = "";
        population = 0;
    }

    /**	Compare two cities populations
     *	@param other		the other City to compare
     *	@return				the following value:
     *		If populations are different, then returns (this.population - other.population)
     *		else if states are different, then returns (this.state - other.state)
     *		else returns (this.name - other.name)
     */
    public int compareTo (City other) {
        if (this.getPopulation() != other.getPopulation()) {
            return this.getPopulation() - other.getPopulation();
        }
        else if (this.getState().equalsIgnoreCase(other.getState())) {
            this.getState().compareTo(other.getState());
        }

        return this.getName().compareTo(other.getName());
    }

    /**	Equal city name and state name
     *	@param other		the other City to compare
     *	@return				true if city name and state name equal; false otherwise
     */
    public boolean equals (Object other) {
        return other instanceof City && compareTo((City) other) == 0;
    }

    /**	Accessor methods */
    public int getPopulation () {
        return population;
    }

    public String getName() {
        return name;
    }

    public String getState () {
        return state;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**	toString */
    @Override
    public String toString() {
        return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
                population);
    }
}
