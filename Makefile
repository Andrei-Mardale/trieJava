JC = javac

.SUFFIXES: .java .class
.java.class:
		$(JC) $*.java

CLASSES = \
	test/Command.java \
	test/AddCommand.java \
	test/ListCommand.java \
	test/RemoveCommand.java \
	test/TestReader.java \
	test/TestWriter.java \
	test/CountCommand.java \
	trie/AbstractTrie.java \
	trie/CaseInsensitiveElement.java \
	trie/Element.java \
	trie/SymbolInsensitiveElement.java \
	trie/Trie.java \
	trie/TrieElement.java \
	Main.java

build: classes

classes: $(CLASSES:.java=.class)

run: build 
	java -Xmx512m Main

clean: 
	cd test; rm -f *.class
	cd trie; rm -f *.class
	rm -f *.class


