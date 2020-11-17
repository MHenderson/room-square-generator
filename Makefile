search: Room/search.class Room/algorithm.class Room/Graph.class Room/square.class
	java Room.search 8 1000 100

test: Room/Graph.class Room/test.class
	java Room.test

Room/algorithm.class: Room/algorithm.java
	javac $<

Room/Graph.class: Room/Graph.java
	javac $<

Room/test.class: Room/test.java
	javac $<

Room/search.class: Room/search.java
	javac $<

Room/square.class: Room/square.java
	javac $<

clean:
	rm Room/*.class

