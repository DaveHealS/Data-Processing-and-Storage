# Data Processing and Storage 

## Instructions
The assignment was done in Java. After cloning this project, it can be opened in an IDE of your choosing (like IntelliJ IDEA). 

Interactions with the In-Memory-Database can be done within the main-method() of the Main-class. An empty database already exists in the codebase under the name "inmemoryDB". Database-Methods can be used by calling the methodnames outlined in the assigment on the InMemoryDB-Object:
- inmemoryDB.begin_transaction();
- inmemoryDB.get(key)
- inmemoryDB.put(key, value);
- inmemoryDB.commit();
- inmemoryDB.rollback();

To see the values of the database, you have to print out (eg. System.out.print) in conjunction with the "inmemory.get(key)"-method. Any exceptions will terminate the program, useless used in a try-catch block. The example code from Figure 2 can be run and its output displayed by calling the "example()" method within the Main. To run the code, you need to start Main.main().

## Write-Up
Thinking about a database solution that allows for rollbacks was interessting. But this assignment lacks in-depth, as problems like dirty-writes and write-read-conflicts mostly happen with simultaneous accesses to a database. Adding this aspect could elevate this assigment. Alternatively, students could perform a write-up or inspection on database problems that can occure.
