/**
 * City class representing a city with various attributes.
 * This class implements the Comparable<City> interface to compare cities based on their population.
 *
 * author Stephen Juma
 * @version 1.0
 */
public class City implements Comparable<City> {
    private String name;
    private String country;
    private String state;
    private int population;

    /**
     * Constructor to initialize a City object with name, country, state, and population.
     *
     * @param name       the name of the city.
     * @param country    the country where the city is located.
     * @param state      the state where the city is located (can be empty).
     * @param population the population of the city.
     * @throws IllegalArgumentException if name or country is null/empty, or if population is non-positive.
     */
    public City(String name, String country, String state, int population) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be null or empty.");
        }
        if (population <= 0) {
            throw new IllegalArgumentException("Population must be greater than zero.");
        }

        this.name = name;
        this.country = country;
        this.state = (state == null) ? "" : state;
        this.population = population;
    }

    /**
     * Compares this city to another city based on their population.
     *
     * @param other the other city to compare to.
     * @return a negative integer, zero, or a positive integer if this city's population is less than,
     *         equal to, or greater than the other city's population.
     */
    @Override
    public int compareTo(City other) {
        // Compare cities based on population first
        return Integer.compare(this.population, other.population);
    }

    // Getters and setters with validation

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be null or empty.");
        }
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = (state == null) ? "" : state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        if (population <= 0) {
            throw new IllegalArgumentException("Population must be greater than zero.");
        }
        this.population = population;
    }

    @Override
    public String toString() {
        return "City: " + name + ", State: " + (state.isEmpty() ? "N/A" : state) +
                ", Country: " + country + ", Population: " + population;
    }
}
