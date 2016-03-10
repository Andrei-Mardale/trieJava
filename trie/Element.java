package trie;

/**
 * Abstract class which provides the basic functionality of a trie element
 * @author Mardale Andrei-Iulian
 */
public abstract class Element implements TrieElement, Comparable {

    /**
     * This value will be stored with element in trie.
     * 
     * Is returned when calling toString()
     */
    protected final String value;
    
    /**
     * Creates new element with given value stored as string
     * @param value actual string to be stored
     */
    public Element (String value) {
        this.value = value;
    }

    /**
     * Maps character to index.
     * Using this preserves ordering in trie
     * Mapping is O(1)
     * Order is [!()?-_][1-9][_][A-Z][a-z]
     * @param c character to be mapped
     * @return 
     */
        protected char indexOf (char c) {
        switch (c) {
            case '!': return 0;
            case '(': return 1;
            case ')': return 2;
            case '?': return 3;
            case '-': return 4;
            case '_': return 15;
        }
        
        if (c >= '0' && c <= '9') {
            return (char) (5 + (c - '0'));
        }
        
        if (c >= 'A' && c <= 'Z') {
            return (char) (16 + (c - 'A'));
        } else if (c >= 'a' && c <= 'z') {
            return (char) (43 + (c - 'a'));
        }
        
        return (char) -1;
    }
    
    @Override 
    public String toString() {
        return value;
    }
    
    @Override
    public int compareTo (Object e) {    
        return this.value.compareTo(((Element) e).value);
    }
}
