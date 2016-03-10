/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mardale Andrei-Iulian
 */
import test.Command;
import test.TestReader;
import test.TestWriter;
import trie.CaseInsensitiveElement;
import trie.SymbolInsensitiveElement;
import trie.Trie;
import trie.TrieElement;

public class Main {
    public static void main (String[] args) {
        //open files
        TestReader in = new TestReader("./trie.in");
        TestWriter out = new TestWriter("./trie.out");
        String[] initWords = in.getWords();
        
        //create trie
        Trie t = new Trie();
   
        //add initial elements
        for (int i = 0; i < initWords.length; ++i) {
            TrieElement te = new CaseInsensitiveElement(initWords[i]);
            t.add(te);
        }
        
        //get and execute commands for first case
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
        
        //create new trie
        t = new Trie();
   
        //initialize it
        for (int i = 0; i < initWords.length; ++i) {
            TrieElement te = new SymbolInsensitiveElement(initWords[i]);
            t.add(te);
        }
        
        //execute commands for second case
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
        
        //close file
        out.close();
    }
    
}
