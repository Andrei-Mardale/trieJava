package trie;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Trie implementation. 
 * Provides methods for adding, counting, removing and retrieving elements with a common prefix.
 * @author Mardale Andrei-Iulian
 *
 */

public class Trie implements AbstractTrie {
    private Element nodeValue = null; //stored element
    private final Trie[] next = new Trie[69]; //childred vector
    private int nextCount = 0; //number of children, useful when checking if leaf or not
    private int count = 0; //number of occurrences for element
    
    private static final int ALPHABET_SIZE = 69;
    
    /**
     * Adds or increments counter for element in trie
     * 
     * @param element element to be added in trie
     */
    
    @Override
    public void add(TrieElement element) {
        
        /*take a current element variable, and go down on the path given by
        * toCharArray() 
        */
        Trie current = this;
        char[] key = element.toCharArray();
        
        for (int i = 0; i < key.length; ++i) {
            //if path is not already constructed then create it
            if (current.next[key[i]] == null) {
                current.next[key[i]] = new Trie();
                ++current.nextCount;
            }
            
            current = current.next[key[i]];
        }
        
        //if element is not in trie then add it
        //if element is lexicographly smaller then replace node
        if (current.nodeValue == null) {
            current.nodeValue = (Element) element;
        } else if (current.nodeValue.compareTo(element) > 0 ) {
            current.nodeValue = (Element) element;
        }
        
        //increase number of occurrences
        ++current.count;
    }

    /**
     * Query for number of occurrences of element in trie
     * @param element element for which the query will be made. 
     * @return number of occurrences for element + any equivalent element
     */
    @Override
    public int count(TrieElement element) {
        //go down path given by toCharArray()
        //stop if null pointer is encountered
        Trie current = this;
        char[] key = element.toCharArray();
        
        for (int i = 0; i < key.length && current != null; ++i) {
            current = current.next[key[i]];
        }
        
        //if path inexistent
        if (current == null) return 0;
        
        return current.count;
    }

    /**
     * Remove or decrease count for element.
     * @param element - element to be removed
     */
    @Override
    public void remove(TrieElement element) {
        //go down path given by toCharArray()
        //if null encountered stop and exit
        Trie current = this;
        char[] key = element.toCharArray();
        
        //used to simulate recursivity
        Stack<Trie> stack = new Stack();
        
        int i; //will be used when going back
        for (i = 0; i < key.length && current != null; ++i) {
            current = current.next[key[i]];
            stack.push(current);
        }
        
        if (current == null) return;
        
        //decrease count
        if (current.count > 0) --current.count;
        
        //if 0 reached then delete nodeValue
        if (current.count == 0) current.nodeValue = null;
        
        //go back
        //while node is leaf, destroy it
        --i;
        while (!stack.isEmpty() && 
               (current.count == 0) && 
               (current.nextCount == 0)) {
            
           current = stack.pop();
           current.next[key[i--]] = null; //delete next node and decrement i
           
           --current.nextCount;
        }
    }

    /**
     * Return a vector of sorted elements, all of which start with given prefix
     * @param prefix common starting sequence for all returned elements
     * @return vector of sorted elements
     */
    @Override
    public TrieElement[] getSortedElements(TrieElement prefix) {
        //go down path given by toCharArray()
        Trie current = this;
        char[] key = prefix.toCharArray();
        
        for (int i = 0; i < key.length && current != null; ++i) {
            current = current.next[key[i]];
        }
        
        //create new array with 0 elements
        TrieElement[] result = new TrieElement[0];
        
        //if prefix actually exists then find words
        if (current != null) {
            result = getWords(current);
        } 
        
        return result;        
    }
    
    
    //auxilliary function used by getSortedElements
    private TrieElement[] getWords (Trie t) {
        //simulate recursivity
        Stack<Trie> stack = new Stack();                                                                                           
        stack.push(t);
        
        //list with sorted elements
        ArrayList<TrieElement> list = new ArrayList();
        
        //keep adding elements in stack as it would be done in a recursive function
        //preorder traversal
        while (!stack.isEmpty()) {
            t = stack.pop();
            
            if (t.count > 0) list.add(t.nodeValue);
            
            //go from last to first elements
            //when adding in stack, order will be reversed
            int i = ALPHABET_SIZE - 1, c = 0;
            while (i >= 0 && c < t.nextCount) {
                if (t.next[i] != null) {
                    stack.push(t.next[i]);
                    ++c;
                }
                --i;
            } 
        }
        
        //tranform list -> array
        TrieElement[] array = new TrieElement[list.size()];
        list.toArray(array);
        return array;        
    }
}
