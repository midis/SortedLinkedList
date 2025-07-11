# ShipMonk library - SortedLinkedList

This is an interview task implementation. 

The task description is:

> “Implement a library providing SortedLinkedList
(linked list that keeps values sorted). It should be
able to hold string or int values, but not both. Try to
think about what you'd expect from such library as a
user in terms of usability and best practices, and
apply those.”


## Basics
This library provides implementation of [SortedLinkedList](src/main/java/com/shipmonk/SortedLinkedList.java) class 
which should fulfill all the requirements mentioned in the above task description:


## Solution design
As the task description doesn't specify more about a concrete technical solution and what is allowed or requested, I've
decided to these key points:
- Using standard Java LinkedList as base not to reinvent the wheel (by implementing the linked list on my own)
and focus more on the "sorting" part of requirement
as there's no "sorted linked list" in standard Java libraries.
- Using the encapsulation of the mentioned LinkedList instead of inheriting from it to better control the exposed
methods in the SortedLinkedList, especially hiding the methods which would break the contract of keeping the list
sorted (e.g. adding element to specific position in the list etc.).
- Exposing the List of all elements (while keeping the immutability) to the consumer to still provide all the useful
standard features (e.g. streams, iterators, etc.) of List interface.
- Maven was chosen for the build as the currently easiest and fastest set-up for me.


## Potential improvements and enhancements
There are at least few improvements which could be implemented depending on concrete use cases, but at this moment
I can imagine at least these:
1. introduce more constructor options to construct the list with specified size and copy-constructor
2. introduce more options to add multiple elements
3. introduce more options to remove a certain element or more elements at once
4. allow more data manipulation, especially the order-related, e.g., reversing the order, etc.

Most of the features can be worked around easily, so they would be added just for the developer convenience but
e.g. adding or removing multiple elements could be performance optimized leveraging the fact the list is sorted.



## Implementation notes
- IDE used for the development is IntelliJ Idea Community Edition
- No AI based features were used for the implementation. Only standard IDE features were used for code writing
and refactoring.
- The project is kept IDE-agnostic, so it should be compilable all the time just by maven (mvn) CLI. Therefore,
also no IDE specific stuff is added to the source code repository.
- SonarQube plug-in was used for the code analysis.
- Due to the small scope of this task the test coverage is able to be kept on 100%.


## Example use
```java
SortedLinkedList<String> sortedList = new SortedLinkedList<>();

sortedList.add("A element");
sortedList.add("C element");
sortedList.add("B element");

System.out.println("All elements after initialization:");
sortedList.toList().forEach(System.out::println);

System.out.println("First element is: %s".formatted(sortedList.get(0)));
System.out.println("Last element is: %s".formatted(sortedList.get(sortedList.size()-1)));

sortedList.remove("A element");

System.out.println("All elements after A removal:");
sortedList.toList().forEach(System.out::println);
```
