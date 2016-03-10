package trie;

import java.util.ArrayList;

/**
 * Element which ignores characters -, _, (, ).
 * These characters will be ignored when comparing 2 elements
 * @author Mardale Andrei-Iulian
 */
public class SymbolInsensitiveElement extends Element {
    
    /**
     * Calls superclass constructor
     * @param value
     */
    public SymbolInsensitiveElement (String value) {
        super(value);
    }
    
    /**
     * Generates path for trie traversal, ignoring specified characters
     * @return array which will be used when traveling down trie
     */
    @Override
    public char[] toCharArray() {
       //allow dynamic adding of elements
       ArrayList<Character> charList = new ArrayList();
       
       for (int i = 0; i < super.value.length(); ++i) {
           if (super.value.charAt(i) != '-' &&
               super.value.charAt(i) != '_' &&
               super.value.charAt(i) != '(' &&
               super.value.charAt(i) != ')') {
               charList.add(indexOf(super.value.charAt(i)));
           }
       }
       
       //convert list to array
       char[] charArray = new char[charList.size()];
       int i = 0;
       
       for (Character c: charList) {
           charArray[i++] = c;
       }
       
       return charArray;       
    }
    
}
