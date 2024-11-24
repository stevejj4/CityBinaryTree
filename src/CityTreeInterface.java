import java.util.ArrayList;
public interface CityTreeInterface {
    /**
     * Retrieves a count of elements being maintained by the tree.
     *
     * @return the size of the tree (count of elements)
     *
     * @author Zion Black
     * @version 15/11/2024
     */
    public int getCount();
    /**
     * Adds a new node to the tree;
     *this method must call the protected recursive add New Node method
     * @param city the element to add to the tree
     */
    public void addCity(City city);
    /**
     *this method must call the protected recursive search method
     *
     * @param name the element to find in the tree
     * @return City class that has city name as a given parameter;
     */
    public City searchBT(String name);
    /**
     *this method must call the protected printNodes method
     *
     * @return toString() method of all nodes;
     */
    public String printTree();
    /**
     *this method must call the protected recursive listOfCities method
     *
     * @param template contains the few letters at the beginning of the name
     * @return ArrayList of cities with names starting with template;
     */
    public ArrayList listOfTemplate(String template);
    /**
     * Removes all nodes from tree;
     *
     */
    public void clear();
}
