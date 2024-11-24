import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main class to demonstrate the functionality of the BTree by reading city data
 * from a file and performing various tree operations.
 * <p>
 * It reads data from cities.txt, adds cities to the BTree, and performs
 * searches, prints, and listing operations.
 * </p>
 *
 * author Zion Black
 * Version 21/11/2024
 */
public class Main {
    public static void main(String[] args) {
        BTree cityTree = new BTree();

        // Load data from cities.txt for main functionality
        loadCityData(cityTree, "src/citiesShort.txt");

        // Print the count of cities
        System.out.println("Total cities in BinaryTree: " + cityTree.getCount());

        // Print all cities in the tree (correct method call)
        System.out.println("Cities in BinaryTree:");
        System.out.println(cityTree.printTree());

        // Test finding a specific city
        String searchCity = "New York";
        City foundCity = cityTree.searchBT(searchCity);
        if (foundCity != null) {
            System.out.println("Found city: " + foundCity);
        } else {
            System.out.println("City " + searchCity + " not found.");
        }

        // Test searching for cities starting with a specific template
        String template = "Portland";
        System.out.println("Cities starting with \"" + template + "\":");
        cityTree.listOfTemplate(template).forEach(System.out::println);
    }

    /**
     * Loads city data from a specified file path and adds each city to the BTree.
     * It skips the first row (metadata) and parses valid city data.
     *
     * @param cityTree the BTree to populate with city data
     * @param filePath the path to the file containing city data
     */
    private static void loadCityData(BTree cityTree, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the first row (metadata or header)
            reader.readLine(); // Read and discard the first line

            // Read the rest of the lines and parse them into City objects
            while ((line = reader.readLine()) != null) {
                try {
                    City city = parseCity(line);
                    if (city != null) { // Only add valid City objects
                        cityTree.addCity(city);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * Parses a line of text to create a City object.
     * Expected format: "{population} {city name}, {state}, {country}"
     * or "{population} {city name}, {country}" if state is absent.
     *
     * @param line a line from the file representing a city's data
     * @return a City object with parsed data, or null if the format is invalid
     * @throws IllegalArgumentException if the line format is incorrect
     */
    private static City parseCity(String line) {
        line = line.trim();
        String[] parts = line.split("\\s+", 2); // Split into population and city details

        if (parts.length < 2) return null;  // Skip if the line format is incorrect

        int population;
        try {
            population = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return null;  // Skip if population is not a valid integer
        }

        String[] cityInfo = parts[1].split(", ");
        if (cityInfo.length < 2) return null;  // Ensure there's at least city name and country

        String cityName = cityInfo[0];
        String country = cityInfo[cityInfo.length - 1];
        String state = cityInfo.length == 3 ? cityInfo[1] : "";  // Handle optional state

        return new City(cityName, country, state, population);
    }
}
