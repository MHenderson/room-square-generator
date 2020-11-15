search: Room/search.class
	java Room.search 20 2000 100

test: Room/graph1.class Room/test.class
	java Room.test

Room/graph1.class: Room/graph1.java
	javac $<

Room/test.class: Room/test.java
	javac $<

Room/search.class: Room/search.java
	javac $<

clean:
	rm Room/*.class

