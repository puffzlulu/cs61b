package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {

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
    private V removeValue;
    private V removeKeyValue;

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
        if(p==null){
            return null;
        } else if (p.key.compareTo(key)==0) {
            return p.value;
        } else if (p.key.compareTo(key)>0) {
            return getHelper(key,p.left);
        } else {
            return getHelper(key,p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if(root==null){
            return null;
        } else if (root.key.compareTo(key)==0) {
            return root.value;
        } else if (root.key.compareTo(key)>0) {
            return getHelper(key,root.left);
        }
        else {
            return getHelper(key,root.right);
        }
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p==null){
            p=new Node(key,value);
            p.left=null;
            p.right=null;
            return p;
        } else if (p.key.compareTo(key)==0) {
            p.value=value;
            return p;
        } else if (p.key.compareTo(key)>0) {
            p.left=putHelper(key,value,p.left);
            return p;
        }else {
            p.right=putHelper(key,value,p.right);
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if(root==null){
            root=new Node(key,value);
            root.left=null;
            root.right=null;
            size+=1;
        } else if (root.key.compareTo(key)==0) {
            root.value=value;
        } else if (root.key.compareTo(key)>0) {
            root.left=putHelper(key,value,root.left);
            size+=1;
        }else {
            root.right=putHelper(key,value,root.right);
            size+=1;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    private void traverse(Node n,Set<K> s){
        if(n==null){
            return;
        }else {
            s.add(n.key);
            if(n.left!=null){
                traverse(n.left,s);
            }
            if(n.right!=null){
                traverse(n.right,s);
            }
        }
    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        //throw new UnsupportedOperationException();
        Set<K> s=new HashSet<>();
        traverse(root,s);
        return s;
    }

    private Node swapSmallest(Node T,Node R){
        if(R.left==null){
            T.key=R.key;
            T.value=R.value;
            return R.right;
        }else {
            R.left=swapSmallest(T,R.left);
            return R;
        }
    }
    
    private Node removeHelp(K key,Node n){
        if(n==null){
            return null;
        } else if (key.compareTo(n.key)>0) {
            n.right=removeHelp(key,n.right);
            return n;
        } else if (key.compareTo(n.key)<0) {
            n.left=removeHelp(key,n.left);
            return n;
        } else if (n.left==null) {
            removeValue=n.value;
            return n.right;
        } else if (n.right==null) {
            removeValue=n.value;
            return n.left;
        }else {
            removeValue=n.value;
            n.right=swapSmallest(n,n.right);
            return n;
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        //throw new UnsupportedOperationException();
        removeHelp(key,root);
        return removeValue;
    }

    private Node removeKeyValueHelp(K key,V value,Node n){
        if(n==null){
            return null;
        } else if (n.key.compareTo(key)>0) {
            n.left=removeKeyValueHelp(key,value,n.left);
            return n;
        } else if (n.key.compareTo(key)<0) {
            n.right=removeKeyValueHelp(key,value,n.right);
            return n;
        } else if (n.left==null) {
            if(n.value.compareTo(value)==0){
                removeKeyValue=n.value;
                return n.right;
            }else {
                return n;
            }
        } else if (n.right==null) {
            if(n.value.compareTo(value)==0){
                removeKeyValue=n.value;
                return n.left;
            }else {
                return n;
            }
        }else {
            if(n.value.compareTo(value)==0){
                removeKeyValue=n.value;
                n.right=swapSmallest(n,n.right);
                return n;
            }else {
                return n;
            }
        }
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        //throw new UnsupportedOperationException();
        removeKeyValueHelp(key,value,root);
        return removeKeyValue;
    }

    private class InorderIterator implements Iterator<K>{
        private Stack<Node> n=new Stack<>();

        private void pushToLeftMost(Node node){
            n.push(node);
            while (node.left!=null){
                n.push(node.left);
                node=node.left;
            }
        }
        InorderIterator(){
            pushToLeftMost(root);
        }

        public boolean hasNext(){
            return !n.empty();
        }

        public K next(){
            Node popNode=n.pop();
            pushToLeftMost(popNode.right);
            return popNode.key;
        }
    }

    @Override
    public Iterator<K> iterator() {
        //throw new UnsupportedOperationException();
        return new InorderIterator();
    }
}
