/**
 * This class is for a Binary Search Tree.
 * @param <K> key associated with node in tree
 * @param <V> value associated with node in tree.
 * @author Raymond Tran
 * @version 1.0 Feb 24, 2020
 */

class LookUpBST<K extends Comparable<K>, V> {

    //-------------------------------------------------------------
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    //-------------------------------------------------------------

    //bad practice to have public inst. variables, but we want to test this...
    //Root of tree

    /**
     * This node is the root for the rest of the tree.
     * @variable Creates root node for tree.
     *
     */
    public Node<K, V> root;

    // size of the tree (the number of nodes)
    /**
     * Holds the size of the tree.
     * @variable controls the size of the tree.
     */
    public int size;
    /**
     * Holds the height of the tree.
     * @variable controls the height of the tree (most number of edges).
     */
    public int height = 0;

    /**
     * This method will return the current size of the tree.
     * @return returns the size of the tree.
     */
    public int size() {
        return size;
    }

    //provided binary tree node class
    //bad practice to have public inst. variables,
    //in a public nested class, but we want to test this...

    /**
     * This is a static class that creates nodes holding keys and values.
     *
     * @param <K> key associated with node in tree.
     * @param <V> value associated with node in tree.
     */
    public static class Node<K, V> {
        /**
         * Holds the Key.
         * @variable Key, what the BST is sorted by.
         */
        K key;                         // sorted by key
        /**
         * Holds the value of the node.
         * @variable val, the value of each node.
         */
        V val;                       // associated data
        /**
         * Holds left and right nodes.
         * @variable left Left node of the node.
         * @variable right Right node of the node.
         */
        Node<K, V> left, right;    // left and right subtrees

        /**
         * This is a constructor for a node that has keys and values in its arguments.
         *
         * @param key Key value for a given node.
         * @param val Value value for a given node.
         */
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        /**
         * This constructor will pass in 2 nodes and a key,value pair to create a tree.
         * @param key Key for the given node.
         * @param val Value for the given node.
         * @param l Left node .
         * @param r Right node.
         */

        public Node(K key, V val, Node<K, V> l, Node<K, V> r) {
            this.key = key;
            this.val = val;
            this.left = l;
            this.right = r;
        }
    }
    //-------------------------------------------------------------
    // END OF PROVIDED "DO NOT EDIT" SECTION
    //-------------------------------------------------------------

    //-------------------------------------------------------------
    // You can NOT add any instance/static variables in this class.
    // You can add methods if needed but they must be PRIVATE.
    //-------------------------------------------------------------

    /**
     * Returns True if the key is contained in the tree.
     * @param key Takes in a key that will be checked for inside the tree.
     * @return True if the key is found in the tree.
     */
    public boolean contains(K key) {
        // Return true if key is in tree;
        // return false if key is not in tree or if key is null.
        // O(H): H as the tree height
        return contains_Helper(root, key);
    }

    /**
     * This method is most of the functionality of the contains method, it helps traverse the
     * tree to find the key value.
     *
     * @param node root of a tree.
     * @param key  key that is being searched for in BST.
     * @return True if found in key is found in the tree.
     */

    private boolean contains_Helper(Node<K, V> node, K key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (node == null)
            return false;
        int comp = key.compareTo(node.key);
        if (comp == 0)
            return true;
        if (comp < 0)
            return contains_Helper(node.left, key);
        else if (comp > 0)
            return contains_Helper(node.right, key);
        else
            return true;
    }

    /**
     * This is the get function that will take in a key, and return the value associated with it
     * if it's found in the tree.
     *
     * @param key Key that is being searched for in the BST.
     * @return value associated with Key. It will return null if the key is not contained in the BST.
     * @throws IllegalArgumentException if key is null.
     */

    public V get(K key) {
        // Return the value associated with the given key if the key is in tree
        // Return null if the key is not in tree
        // throw IllegalArgumentException if key is null
        // O(H): H as the tree height
        if (!contains(key))
            return null;
        if (key == null)
            throw new IllegalArgumentException();
        return get_Value_Helper(root, key);
    }

    /**
     * Takes in a root and key, and returns the value associated with that key.
     *
     * @param node Root node of BST.
     * @param key  Key that is being searched for in BST.
     * @return Returns the value associated with the key. Will also return null if the root node is null.
     * @throws IllegalArgumentException if key == null.
     */

    private V get_Value_Helper(Node<K, V> node, K key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (node == null)
            return null;
        int comp = key.compareTo(node.key);
        if (comp == 0)
            return node.val;
        if (comp < 0)
            return get_Value_Helper(node.left, key);
        else if (comp > 0)
            return get_Value_Helper(node.right, key);
        else
            return node.val;
    }

    /**
     * Returns true if the key is able to be stored in the tree.
     * @param key Value that is being searched for /put in the tree.
     * @param val Value of the node that is being inserted into the tree.
     * @return Returns true of the put was successful.
     */

    public boolean put(K key, V val) {
        // Insert key, val into tree.
        // If key is in tree, replace already existing value for this key with the given parameter val
        // Return false if key, val cannot be added
        //    (null keys).
        // Return true for a successful insertion.
        // O(H): H as the tree height
        if (key == null)
            return false;
        Node<K, V> newNode = new Node<K, V>(key, val);
        if (root == null) {
            root = newNode;
            size += 1;
            return true;
        }
        return putHelper(root, newNode);

    }

    /**
     * Returns True if the new node was able to be inserted into the tree.
     *
     * @param node Root of the tree.
     * @param newNode Node that is being inserted.
     * @return True if the new node is able to be inserted into the tree.
     */

    private boolean putHelper(Node<K, V> node, Node<K, V> newNode) {
        int comp = node.key.compareTo(newNode.key);
        if (comp == 0) {
            node.val = newNode.val;
            return true;
        }
        if (comp < 0) {
            if (node.right == null) {
                node.right = newNode;
                size++;
                return true;
            }
            return putHelper(node.right, newNode);
        }
        if (comp > 0) {
            if (node.left == null) {
                node.left = newNode;
                size++;
                return true;
            }
            return putHelper(node.left, newNode);
        }
        return false;
    }

    /**
     * This method will find the largest key inside the BST.
     *
     * @param t        This is the root node that will be passed through the
     * @param <K>      Key value associated with the node that is passed in.
     * @param <V>  Value of the node that is being passed in.
     * @return K Returns key of the largest node in the BST. Will also return null if t is null.
     */
    public static <K, V> K findBiggestKey(Node<K, V> t) {
        // Return the biggest key in the tree rooted at t.
        // Return null if tree is null.
        // O(H): H as the tree height
        if (t == null)
            return null;
        if (t.right == null)
            return t.key;
        if (t.right != null)
            return findBiggestKey(t.right);
        else
            return findBiggestKey(t.left);
    }

    /**
     * This method will return the height of the tree (number of edges).
     *
     * @return returns the height of the tree.
     */

    public int height() {
        // Return the height of the tree.
        // Return -1 for null trees.
        // O(N): N is the tree size
        return getHeight(root);
    }

    /**
     * This method will recursively find the height of the tree.
     *
     * @param node This is the root of the tree.
     * @return returns the height of the tree.
     */

    private int getHeight(Node<K, V> node) {
        if (node == null)
            return -1;
        if (size() == 1)
            return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (leftHeight > rightHeight) {
            return 1 + leftHeight;
        } else if (leftHeight < rightHeight) {
            return 1 + rightHeight;
        }
        return 0;
    }

    /**
     * This method will calculate the number of leaves inside a tree.
     *
     * @return an integer that represents the number of leaves in the root's tree.
     */

    public int numLeaves() {
        // Return the number of leaf nodes in the tree.
        // Return zero for null trees.
        // NOTE: Your implementation MUST be recursive.
        // O(N): N is the tree size
        if (root == null)
            return 0;
        return numLeavesHelper(root);
    }

    /**
     * This method will find the number of left nodes inside the tree.
     *
     * @param node root node that is being passed into the function
     * @return returns the number of leaf notes inside BST.
     */

    private int numLeavesHelper(Node<K, V> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        else
            return numLeavesHelper(node.left) + numLeavesHelper(node.right);
    }

    /**
     * Returns a string representation of the tree.
     * @return String representing the tree.
     */

    public String toString() {
        // Returns a string representation of the tree
        // Change the function call below to
        //        toStringInOrder: to see (IN-ORDER) string representation of LookUpBST while in debug mode
        //        toStringLevelOrder: to see (LEVEL-ORDER) string representation of LookUpBST in level-order traversal while in debug mode

        return toStringInOrder(this.root);
    }

    /**
     * Returns a string representing the tree.
     * @param t Root of the tree.
     * @return String representing the tree.
     */

    private String toStringInOrder(Node<K, V> t) {
        // Follow IN-ORDER traversal to include data of all nodes.
        // Example 1: a single-node tree with the root data as "a:112":
        //            toString() should return a:112
        //
        // Example 2: a tree with four nodes:
        //			 d:310
        //           /   \
        //        a:112   p:367
        //                /
        //              f:330
        // toStringInOrder() should return a:112 d:310 f: 330 p:367

        if (t == null)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toStringInOrder(t.left));
        sb.append(t.key);
        sb.append(":");
        sb.append(t.val);
        sb.append(" ");
        sb.append(toStringInOrder(t.right));
        return sb.toString();
    }

    /**
     * Shows a level order version of the tree in string form.
     * @param t Root of the tree.
     * @return String representation of a level-order tree.
     */
    private String toStringLevelOrder(Node<K, V> t) {
        // Follow LEVEL-ORDER traversal to include data of all nodes.
        // Example: a tree with four nodes:
        //			 d:310
        //           /   \
        //        a:112   p:367
        //                /
        //              f:330
        // toStringLevel() should return
        //                              d:310
        //                              a:112 p:367
        //                              null null f:330 null

        StringBuilder sb = new StringBuilder();
        int capacity = (int) Math.pow(2, size() + 1) - 1;
        Node<?, ?>[] list = new Node<?, ?>[capacity];
        list[0] = root;

        int count = 0;
        int level = 0;
        for (int i = 0; i < capacity; i++) {
            if (i == Math.pow(2, level + 1) - 1) {
                if (count == size) break;
                level++;
                sb.append("\n");
            }
            if (list[i] == null) {
                sb.append("null ");
            } else {
                count++;
                sb.append(list[i].key);
                sb.append(":");
                sb.append(list[i].val);
                sb.append(" ");
                if ((i * 2) + 1 < list.length) {
                    list[(i * 2) + 1] = list[i].left;
                    list[(i * 2) + 2] = list[i].right;
                }
            }
        }
        return sb.toString();
    }
}



