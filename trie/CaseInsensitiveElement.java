package trie;

import java.util.ArrayList;

/**
 * Element which ignores case difference.
 * Uppercase and Lowercase elements are equal
 * @author Mardale Andrei-Iulian
 */
public class CaseInsensitiveElement extends Element {
    
    /**
     * Calls superclass constructor
     * @param value
     */
    public CaseInsensitiveElement (String value) {
        super(value);
    }
    
    /**
     * Generates path for trie traversal, ignoring case difference
     * @return array which will be used when traveling down trie
     */
    @Override
    public char[] toCharArray() {
        //allow dynamic adding of elements
        ArrayList<Character> charList = new ArrayList();
       
        //convert to uppercase then add everything to list
        String upperValue = super.value.toUpperCase();
        
        for (int i = 0; i < upperValue.length(); ++i) {
            charList.add(indexOf(upperValue.charAt(i)));
        }
        
        //transform list -> array
        char[] charArray = new char[charList.size()];
        
        int i = 0;
        for (Character c: charList) {
            charArray[i++] = c;
        }
        
        return charArray;
    }
}
