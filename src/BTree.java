import java.util.ArrayList;

/**
 * BTree class for managing city data using a binary search tree (BST).
 * Implements the CityTreeInterface, providing recursive methods
 * for adding cities, searching, printing, and listing cities by template.
 *
 * @author Stephen Juma
 * @version 20/11/2024
 */
public class BTree implements CityTreeInterface {

    private TreeNode root;  // Root node of the binary tree
    private int size;       // Number of cities in the tree

    /**
     * TreeNode class representing a node in the binary tree.
     * Each node holds a City object and references to left and right child nodes.
     */
    private class TreeNode {
        City city;
        TreeNode left, right;

        /**
         * Constructs a TreeNode with the specified City object.
         *
         * @param city the City object to store in this node.
         */
        TreeNode(City city) {
            this.city = city;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Constructor for the BTree, initializes an empty tree.
     */
    public BTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Retrieves the total number of cities in the tree.
     *
     * @return the size of the tree (number of cities).
     */
    @Override
    public int getCount() {
        return size;
    }

    /**
     * Adds a city to the binary tree. This method uses recursion to insert
     * the city in the correct position based on population.
     *
     * @param city the City object to add to the tree.
     */
    @Override
    public void addCity(City city) {
        root = addCityRecursive(root, city);
        size++;
    }

    /**
     * Recursive helper method to add a city into the tree.
     * It inserts the city in the correct location based on population.
     *
     * @param node the current node in the tree.
     * @param city the City object to add.
     * @return the updated node after insertion.
     */
    private TreeNode addCityRecursive(TreeNode node, City city) {
        // If the current node is null, we've found the spot to insert the new city
        if (node == null) {
            return new TreeNode(city);  // Create a new node
        }

        // Compare populations first
        int comparison = city.compareTo(node.city);

        // If the city's population is smaller, go to the left subtree
        if (comparison < 0) {
            node.left = addCityRecursive(node.left, city);
        }
        // If the city's population is larger, go to the right subtree
        else if (comparison > 0) {
            node.right = addCityRecursive(node.right, city);
        }
        // If populations are equal, check for duplicates based on city name
        else {
            // Skip inserting the city if both population and name are the same
            if (city.getName().equalsIgnoreCase(node.city.getName())) {
                return node;  // Duplicate found, return the node to stop recursion
            } else {
                // If population is the same but name is different, handle it as needed
                System.out.println("City with the same population but different name detected: " + city.getName());
                return node;  // Skip the city but return the node
            }
        }

        return node;
    }

    /**
     * Searches for a city by name in the binary tree.
     * It uses a recursive method to find the city.
     *
     * @param name the name of the city to search for.
     * @return the City object if found, or null if not found.
     */
    @Override
    public City searchBT(String name) {
        return searchCityRecursive(root, name);
    }

    /**
     * Recursive helper method to search for a city by name.
     *
     * @param node the current node being evaluated.
     * @param name the name of the city to search for.
     * @return the City object if found, or null if not found.
     */
    private City searchCityRecursive(TreeNode node, String name) {
        if (node == null) {
            return null;
        }
        if (node.city.getName().equalsIgnoreCase(name)) {
            return node.city;  // City found
        }

        // Recursively search the left or right subtrees based on the city name
        if (name.compareToIgnoreCase(node.city.getName()) < 0) {
            return searchCityRecursive(node.left, name);
        } else {
            return searchCityRecursive(node.right, name);
        }
    }

    /**
     * Prints all cities in the binary tree in ascending order of population.
     * It performs an in-order traversal to print cities.
     *
     * @return a string representation of all cities in the tree.
     */
    @Override
    public String printTree() {
        StringBuilder sb = new StringBuilder();
        printTreeRecursive(root, sb);
        return sb.toString();
    }

    /**
     * Recursive helper method to perform an in-order traversal and print cities.
     *
     * @param node the current node being traversed.
     * @param sb   the StringBuilder used to accumulate the results.
     */
    private void printTreeRecursive(TreeNode node, StringBuilder sb) {
        if (node != null) {
            printTreeRecursive(node.left, sb);  // Traverse left subtree
            sb.append(node.city.toString()).append("\n");  // Print the current city
            printTreeRecursive(node.right, sb);  // Traverse right subtree
        }
    }

    /**
     * Lists cities whose names start with the specified template.
     * It performs a recursive search to find matching cities.
     *
     * @param template the prefix template for city names.
     * @return a list of cities matching the template.
     */
    @Override
    public ArrayList<City> listOfTemplate(String template) {
        ArrayList<City> matchingCities = new ArrayList<>();
        listCitiesRecursive(root, template, matchingCities);
        return matchingCities;
    }

    /**
     * Recursive helper method to find all cities that start with the specified template.
     *
     * @param node           the current node being evaluated.
     * @param template       the city name prefix template.
     * @param matchingCities the list to store matching cities.
     */
    private void listCitiesRecursive(TreeNode node, String template, ArrayList<City> matchingCities) {
        if (node != null) {
            if (node.city.getName().toLowerCase().startsWith(template.toLowerCase())) {
                matchingCities.add(node.city);  // Add city if it matches the template
            }
            listCitiesRecursive(node.left, template, matchingCities);  // Search left subtree
            listCitiesRecursive(node.right, template, matchingCities);  // Search right subtree
        }
    }

    /**
     * Clears the tree by setting the root to null and resetting the size.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
