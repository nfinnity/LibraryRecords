# LibraryRecords
Program that mimics a library. Library becomes populated by user, and several actions can be taken by the user when interacting with the library.

PROBLEM DESCRIPTION

The program for this project is meant to be designed to perform and act as a
library. A user should be 
be able to input their own data file containing the library books that will populate
their library.
They should also then be provided with a menu of options of what they can do
with the library they just
created. The options should include: (1) displaying all of the book records,
(2) searching for a book by its title, 
and (3) exiting out of the program. For instance, if the user were to enter
(1) as their input, all library
book records should be displayed one by one on the screen with the books
information and record number displayed.
Sample output for each record should look like:

Record #number

    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	Title :		  Book Name
	
    Author's Name :		Author Name

    Copyright :		       Year
    
        Price :		      Price
        
        Genre :		      Genre
        

There are two different sorting algorithms this program uses. One of them is
the binary search, and it searches 
by comparing the current middle and seeing where it is in association to the
keyword of interest. It is much faster
as compared to the other search that the program uses, which is the linear
sort. The linear sort goes through each
item in the array until it finds what it is looking for. The sorting algorithm
used in this program is the selection
sort, which takes the smallest value in the array and places it in the last
unsorted spot, and moves on to the next one
until all items have been sorted.
Selection Sort:
{1, -3, 5, 9, 2}
{-3, 1, 5, 9, 2}  ----1st pass
{-3, 1, 2, 9, 5}  ----2nd pass
{-3, 1, 2, 5, 9}  ----3rd pass


		 1  2  3   4  5
Binary Search: {-5, -1, 0, 2, 6}
Check # | Middle | First | Last
   1	|    1   |    0  |  3
   2    |    0   |    0  |  3
Checking for the digit -5 requires two checks.
The middle changes to middle - 1 if the middle > key
The middle changes to middle + 1 if the middle < key

		 1   2  3   4  5  
Linear Search: {-5, -1, 0 , 2, 6}
It goes through each digit and checks to see if it is equal to the keyword.
So if it were search for 2, it would require:
Check #1 ---> -5
Check #2 ---> 0
Check #3 ---> 2
Three checks.


Outline

	-setFileName() to get a valid input name. This then calls fillLibrary() to fill the library.
	
	-fillLibrary() populates the book arrayList with library books from	the input data file.
	
	-sortLibrary() sorts the library in alphabetical order from A-Z.
	
	-waitForResponse() will pause and allow the user to continue by	hitting the "enter" key.
	
	-displayMenu() displays the main menu with the program options.
	
	-displayRecords() allows the user to view all records available in the library, and provides with a "stop" option.
	
	-recordsFormat() displays the formatted record for each book that is sent in as a parameter.
	
	-searchByExactTitle() searches for the exact title with a binary search.
	
	-searchByTitle() searches for and displays any titles matching the input using a linear search.
	
	-searchByAuthor() searches for and displays all books matching to any authors that match the keyword using a linear search.
	
	-searchByGenre() searches for and displays all books matching to the genre using a linear search.
	
	-selectBook() allows the user to select the book that he/she want the record for.
	
	-exitProgram() exits the program.
	
	-clearScreen() clears the entire screen.
	
