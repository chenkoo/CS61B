package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key.compareTo(p.key) > 0) {
            if (p.right != null) {
                if (key.compareTo(p.right.key) == 0) {
                    return p.right.value;
                } else {
                    return getHelper(key, p.right);
                }
            } else {
                return null;
            }
        } else {
            if (p.left != null) {
                if (key.compareTo(p.left.key) == 0) {
                    return p.left.value;
                } else {
                    return getHelper(key, p.left);
                }
            } else {
                return null;
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) == 0) {
            return root.value;
        }
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            p = new Node(key, value);
            return p;
        }
        int cmp = key.compareTo(p.key);
        if (cmp == 0) {
            p.value = value;
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.left = putHelper(key, value, p.left);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return ketSetHelper(root);
    }

    private Set<K> ketSetHelper(Node n) {
        Set<K> kset = new HashSet<>();
        if (n.equals(null)) {
            return null;
        }
        kset.add(n.key);
        kset.addAll(ketSetHelper(n.left));
        kset.addAll(ketSetHelper(n.right));
        return kset;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (!keySet().contains(key)) {
            throw new IllegalArgumentException();
        }
        return remove(key, get(key));
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (!keySet().contains(key)) {
            throw new IllegalArgumentException();
        }
        if (!get(key).equals(value)) {
            return null;
        }
        root = remove(key, root);
        size -= 1;
        return value;
    }

    private Node remove(K key, Node n) {
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return remove(key, n.left);
        } else if (cmp > 0) {
            return remove(key, n.right);
        } else {
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else {
                Node temp = n;
                n = max(n.left);
                n.right = temp.right;
                n.left = deleteMax(temp.left);
            }
        }
        return n;
    }

    private Node max(Node n) {
        if (n.right == null) {
            return n;
        }
        return max(n.right);
    }

    private Node deleteMax(Node n) {
        if (n.right == null) {
            return n.left;
        }
        n.right = deleteMax(n.right);
        return n;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("starChild", 5);
        bstmap.put("KISS", 5);
        System.out.print(bstmap.root.left.key.equals("KISS"));
    }
}
