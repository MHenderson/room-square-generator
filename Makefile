run: Room/graph1.class Room/test.class
	java Room.test

Room/graph1.class: Room/graph1.java
	javac $<

Room/test.class: Room/test.java
	javac $<

clean:
	rm Room/*.class

