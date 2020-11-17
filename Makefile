search: Room/search.class Room/algorithm.class Room/graph.class Room/square.class
	java Room.search 8 1000 100

test: Room/graph.class Room/test.class
	java Room.test

Room/algorithm.class: Room/algorithm.java
	javac $<

Room/graph.class: Room/graph.java
	javac $<

Room/test.class: Room/test.java
	javac $<

Room/search.class: Room/search.java
	javac $<

Room/square.class: Room/square.java
	javac $<

clean:
	rm Room/*.class

