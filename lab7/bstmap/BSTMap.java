package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    public Iterator<K> iterator() {
        return null;
    }
    public class node<K extends Comparable, V>{
        public node right = null;
        public node left = null;
        public K key;
        public V value;
        public node (K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    public node root;


    public void clear(){
        root = null;

    };

    public BSTMap(){
        root = null;
    }
    public BSTMap(K key, V value){
        root = new node(key, value);
    }

    /* Returns true if this map contains a mapping for the specified key. */
    private boolean helper_contain(K key, node tmp_node){
        if(tmp_node == null){
            return false;
        } else if (tmp_node.key.compareTo(key) == 0) {
            return true;
        } else {
            return helper_contain(key, tmp_node.left) || helper_contain(key, tmp_node.right);
        }
    }
    public boolean containsKey(K key){
        return helper_contain(key, root);
    };

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */

    public V get(K key){
        Stack<node> stack = new Stack<node>();
        if (root == null){
            return null;
        }
        stack.push(root);
        while (!stack.empty()){
            node tmp_node = stack.pop();
            if(tmp_node.left!=null){
                stack.push(tmp_node.left);
            }
            if(tmp_node.right!=null){
                stack.push(tmp_node.right);
            }
            if (tmp_node.key.compareTo(key) == 0){
                return (V) tmp_node.value;
            }
        }
        return null;
    };

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        int size = 0;
        Stack<node> stack = new Stack<node>();
        if (root == null){
            return size;
        }
        stack.push(root);
        size = 1;
        while (!stack.empty()){
            node tmp_node = stack.pop();
            if(tmp_node.left!=null){
                stack.push(tmp_node.left);
                size += 1;
            }
            if(tmp_node.right!=null){
                stack.push(tmp_node.right);
                size += 1;
            }
        }
        return size;
    };

    /* Associates the specified value with the specified key in this map. */
    public node helper_put(K key, V value, node tmp){
        if (tmp == null){
            return new node(key, value);
        }
        if (tmp.key.compareTo(key) >0){
            tmp.left = helper_put(key, value, tmp.left);
        } else if (tmp.key.compareTo(key) <0) {
            tmp.right = helper_put(key, value, tmp.right);
        }
        return tmp;
    }
    public void put(K key, V value){
        root = helper_put(key, value, root);

    };

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    };

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){throw new UnsupportedOperationException();};

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){throw new UnsupportedOperationException();};
}
