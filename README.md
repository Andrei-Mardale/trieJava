# trieJava
My first Object Oriented Programming homework

A Trie data structure implementation in Java.

It supports 2 operation modes: 
* one which ignores case difference
* one which ignores the following symbol set: {-,_,(,)}

#How to compile
"make build"

#How to run
"make run"

#Input/Output
The program expects 2 files: trie.in and trie.out.

For each mode the input is as follows:
* n - number of operations, followed by any of the given operations
* 0 w - adds word w
* 1 w - deletes word w
* 2 w - prints the count for word w
* 3 w - prints all the words which share the input w in lexicographical order

Both modes are expected to be found in the input file.

