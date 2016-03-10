/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import test.Command;
import test.TestReader;
import test.TestWriter;

/**
 *
 * @author aim
 */
public class Main {
    public static void main (String[] args) {
        TestReader in = new TestReader("/home/aim/Programming/test/trie.in");
        TestWriter out = new TestWriter("/home/aim/Programming/test/trie.out");
        String[] initWords = in.getWords();
        
        Trie t = new Trie();
   
        for (int i = 0; i < initWords.length; ++i) {
            TrieElement te = new CaseInsensitiveElement(initWords[i]);
            t.add(te);
        }
        
        Command[] commands = in.getFirstCommands();
        
        for (int i = 0; i < commands.length; ++i) {
            TrieElement te = new CaseInsensitiveElement(commands[i].getWord());
            switch(commands[i].getType()) {
                case Command.ADD:   
                    t.add(te); 
                    break;
                case Command.REMOVE:    
                    t.remove(te); 
                    break;
                case Command.COUNT: 
                    out.printCount(t.count(te));
                    break;
                case Command.LIST: 
                    out.printSortedWords(t.getSortedElements(te));
                    break;
            }
        }
        
        t = new Trie();
   
        for (int i = 0; i < initWords.length; ++i) {
            TrieElement te = new SymbolInsensitiveElement(initWords[i]);
            t.add(te);
        }
        
        commands = in.getSecondCommands();
        
        for (int i = 0; i < commands.length; ++i) {
            TrieElement te = new SymbolInsensitiveElement(commands[i].getWord());
            switch(commands[i].getType()) {
                case Command.ADD:   
                    t.add(te); 
                    break;
                case Command.REMOVE:    
                    t.remove(te); 
                    break;
                case Command.COUNT: 
                    out.printCount(t.count(te));
                    break;
                case Command.LIST: 
                    out.printSortedWords(t.getSortedElements(te));
                    break;
            }
        }
        
        out.close();
    }
    
}
