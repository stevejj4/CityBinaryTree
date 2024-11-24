/**
 * TreeNode class representing a node in the binary tree.
 * Each node stores a City object and references to left and right child nodes.
 *
 * @author Zion Black
 * @version 19/11/2024
 */
public class TreeNode {
    City city;
    TreeNode left, right;

    /**
     * Constructor for TreeNode.
     *
     * @param city the city stored in the node.
     */
    public TreeNode(City city) {
        this.city = city;
        this.left = this.right = null;
    }
}
