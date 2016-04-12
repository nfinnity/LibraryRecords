import java.io.*;
import java.util.*;
import java.awt.*;
import java.lang.*;

public class LibraryRecords {
    
    //Books is my global arrayList of library books from the data file.
    //Data is an arrayList for the ".dat" files listed in the directory.
    static ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
    static ArrayList<String> data = new ArrayList<String>();
    static int count = 0;
    static String fileName;

    //********************************************************************************************
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //Get all files from directory
        File curDir = new File(".");
        String[] fileNames = curDir.list();       
        //Find files which may have data. (aka, are in the .dat format)
        for(String s:fileNames)
                if(s.endsWith(".dat"))
                        data.add(s);
        
        //Initiate the sequence
        setFileName();
        fillLibrary();
    }
    
    //********************************************************************************************
    public static void setFileName()
    {
        Scanner input = new Scanner(System.in);
    
        //Receiving the file that contains the books that will populate the library
        System.out.println("\n     Input the data file that contains the library books.\n     These are the available data files in this directory:\n");
       
        
        //Prints the files in the array
        for (int i = 0; i < data.size(); i++)
        {
            System.out.print(data.get(i) + "        ");
        }
        System.out.print("\n\n     File input:   ");
        
        //Inputs the filename
        fileName = input.next();    
    }
    
    //********************************************************************************************

    //Fills up the String Arraylist with book names from the file input.
    public static void fillLibrary()
    {
        //As it stands, a user can enter a file that is inside the directory
        //and the program will accept it. This will cause an error if it is 
        //not a .dat file, so this checks to see if the filename has a ".dat" extension.
        boolean fileExists = false;
         for (int i = 0; i < data.size(); i++)
         {
             String checker = data.get(i);
             if (checker.equals(fileName))
             {
                 fileExists = true;
                 break;
             }
         }
         //If it does not, it will repeat the process.
         if (fileExists == false)
         {
             System.out.println("\n\nThe file is not a data file. Hit enter to try again.");
             waitForResponse();
             clearScreen();
             setFileName();
             fillLibrary();
         }
         else{}
         
        //Checks to see if the file can be found.
        //If it is not, it runs through again to prompt the
        //user with the available files and accepts a new input.
        Scanner input = new Scanner(System.in);
          try {
                input = new Scanner(new File(fileName));                
            } catch (FileNotFoundException e) {
                System.out.println("\n\nThere was an error.\n");
                setFileName();
                fillLibrary();
            }

        //Takes the strings from the data file and uses a delimiter to 
        //create the library books.
        while (input.hasNext())
        {        
            String bookTitle;
            String authorName;
            int copyright;
            double bookPrice;
            String genre;

            Scanner bookPieces = new Scanner(input.nextLine());
            bookPieces.useDelimiter(";");
            bookTitle = bookPieces.next();
            authorName = bookPieces.next();
            copyright = bookPieces.nextInt();
            bookPrice = bookPieces.nextDouble();
            genre = bookPieces.next();
            LibraryBook nextBook = new LibraryBook(bookTitle, authorName, copyright, bookPrice, genre); 
            books.add(nextBook);
            count++;
        }        
        sortLibrary();
    }
    
    //********************************************************************************************
    //Sorts the library of books by using a selection sort, and prompts
    //the user with the number of books that were found in the file.
    public static void sortLibrary()
    {   
        //Variables
        int minIndex, index, j;
        LibraryBook temp;
        int pass = 0;
        
        //Selection Sort Loop
        for (index = 0; index < books.size()-1; index++)
        {
            minIndex = index;
            for (j = index + 1; j < books.size(); j++)
            {
                LibraryBook currentBook = books.get(minIndex);
                LibraryBook nextBook = books.get(j);
                if (currentBook.getTitle().compareTo(nextBook.getTitle()) > 0)
                {
                    minIndex = j;
                }
            }
                if (minIndex != index)
                {
                    temp = books.get(index);
                    books.set(index, books.get(minIndex));
                    books.set(minIndex, temp);
                }              
        }               
        System.out.println("\nA total of " + count + " books have been imported and sorted by title.");
        //Prompt to allow the user to continue at their own pace.
        System.out.print("Please hit enter to continue.  ");
        waitForResponse();
        //Clears the screen and displays the main menu.
        clearScreen();
        displayMenu();
   }
    
    //********************************************************************************************
    //Method that creates a pause to allow the user to continue at their own pace.
    //A "stop" option is also included that allows the user to return to the main menu.
    public static void waitForResponse()
    {
        Scanner listener = new Scanner(System.in);
        String input = listener.nextLine();
        if (input.compareToIgnoreCase("STOP") == 0)
        {
            clearScreen();
            displayMenu();
        }
        if (input.compareToIgnoreCase("RETURN") == 0)
        {
            clearScreen();
            displayMenu();
        }
    }
    
    //********************************************************************************************
    //Displays the main menu and takes an entry as a string. 
    public static void displayMenu()
    {
        boolean isValid = false;
        Scanner listener = new Scanner(System.in);
        Scanner checker = new Scanner(System.in);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("             THE GREAT BOOKS SEARCH PROGRAM");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("    1) Display all book records");
        System.out.println("    2) Search for a book by an Exact Title");
        System.out.println("    3) Search for a book by Title");
        System.out.println("    4) Search for a book by Author");
        System.out.println("    5) Search for a book by Genre");
        System.out.println("    6) Exit Search Program");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.print("    Please Enter Your Choice >   ");
        String input = listener.next();
        
        //The string is then checked to see if it is a letter or number. 
        //If it is a letter, an error prompt will be given and the display 
        //menu will restart. If it is a number and it is not found in the menu,
        //an error prompt will also be given and the menu restarted. If it is
        //found, then the string is parsed into an int and sent to the switch statement.
        int numberChoice = 0;
        while (isValid != true)
        {
            if (Character.isLetter(input.charAt(0)))
            {
                System.out.print("\nThe entry is invalid.   "); 
                waitForResponse();
                clearScreen();
                displayMenu();
            }
            if (Character.isDigit(input.charAt(0)))
            {
                numberChoice = Integer.parseInt(input);
                isValid = true;
            }
            if ((numberChoice > 6) || (numberChoice == 0))
            {
                System.out.print("\nThe number entered is invalid.   ");
                waitForResponse();
                clearScreen();
                displayMenu();
            }           
        }
        
        //Depending on the input, the switch statement will call the following methods:
        switch (numberChoice)
        {
            case 1:
            {
                clearScreen();
                displayRecords();
                break;
            }
            case 2:
            {
                searchByExactTitle();
                break;
            }
            case 3:
            {
                searchByTitle();
                break;
            }
            case 4:
            {
                searchByAuthor();
                break;
            }
            case 5:
            {
                searchByGenre();
                break;
            }
            case 6:
            {
                exitProgram();
                break;
            }
        }       
}
    
    
    //********************************************************************************************
    //This method displays all of the records within the libary by calling the recordsFormat() method
    //that formats and prints out the information for each book. The user has the option of stopping 
    //the programming at any time by typing "stop."
    public static void displayRecords()
    {
        int counter = 0;
        for (int i = 0; i < books.size()-1; i++)
        {
            counter++;
            LibraryBook book = books.get(i);
            recordsFormat(book, counter);
            System.out.print("Please hit enter to view the next book record.\nType \"stop\" to return to the main menu.  ");
            waitForResponse();
            clearScreen();
        }

        recordsFormat(books.get(books.size()-1), counter + 1);
        System.out.print("This is the end of the list of book records.\nHit enter to return to the main menu.   ");
        waitForResponse();
        clearScreen();
        displayMenu();
    }
    
    //********************************************************************************************
    //Method that formats and displays the book record. It takes a library book and an int as parameters.
    //The int is for the position of the book in the records.
    public static void recordsFormat(LibraryBook book, int bookNumber)
    {
        System.out.println("Record #" + bookNumber);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.printf("%20s : %25s\n", "Title", book.getTitle());
        System.out.printf("%20s : %25s\n", "Author's Name", book.getAuthor());
        System.out.printf("%20s : %25d\n", "Copyright", book.getCopyright());
        System.out.printf("%20s : %25.2f\n", "Price", book.getPrice());
        System.out.printf("%20s : %25s\n", "Genre", book.getGenre());
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
    }
    
    //********************************************************************************************
    //This method searches for books by exact titles, meaning the entire title has to be entered
    //exactly as it exists in the arrayList. Otherwise, it will not be found. This performs a binary search.
    public static void searchByExactTitle()
    {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the entire book title you are searching for:   ");
        String key = input.nextLine();
        
        int first = 0, last = books.size()-1, middle, location;
        boolean keyFound = false;
        LibraryBook book;
        
        do
        {
            middle = (first + last)/2;
            book = books.get(middle);
            if (book.getTitle().equals(key))
            {
                keyFound = true;
            }
            else if (key.compareToIgnoreCase(book.getTitle()) < 0)
            {
                last = middle - 1;
            }
            else
                first  = middle + 1;
        }
        while ((! keyFound) && (first <= last));

        if (keyFound == true)
        {
        location = middle;
        recordsFormat(books.get(location), 1);
        System.out.print("Please hit enter to return to the main menu.");
        waitForResponse();
        clearScreen();
        displayMenu();
        }
        
        if (keyFound == false)
        {
         System.out.print("No book was found by the title.\nHit enter to return to the main menu.   ");
        waitForResponse();
        clearScreen();
        displayMenu();   
        }
    }
    
    //********************************************************************************************
    //This method performs a linear search and looks for any book matching the input. It puts all of
    //the books matching the criteria into an arrayList and sends the arrayList to the method selectBook().
    public static void searchByTitle()
    {
        ArrayList<LibraryBook> titleArray = new ArrayList<LibraryBook>();        
        boolean keyFound = false;
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the book title you are searching for:   ");
        String key = input.nextLine();
        
        for (int i = 0; i < books.size(); i++)
        {
            LibraryBook book = books.get(i);
            String title = book.getTitle();
            
            if (title.contains(key))
            {
                titleArray.add(book);
                keyFound = true;
            }
        }
        
        if (keyFound == true)
        {
            System.out.println("The following books were found: ");
            for (int i = 0; i < titleArray.size(); i++)
            {
                LibraryBook book = titleArray.get(i);
                String title = book.getTitle();
                String author = book.getAuthor();
                System.out.println((i+1) + ") " + title + "/" + author);
            }
            System.out.println("\nIf the book you wanted is not here, type \"return\" to go back to the main menu.\nOtherwise, hit enter to enter the book you want.");
            waitForResponse();
        }
        else
        {
            System.out.println("No books were found.\nHit enter to return to the main menu.    ");
            waitForResponse();
            clearScreen();
            displayMenu();
        }
        selectBook(titleArray);
    }
    //********************************************************************************************
    //This method performs a linear search and looks for any book matching the input. It puts all of
    //the books matching the criteria into an arrayList and sends the arrayList to the method selectBook().
    public static void searchByAuthor()
    {
        boolean keyFound = false;
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the full name of the book author you are searching for:   ");
        String lastName = input.next();
        String firstName = input.next();
        ArrayList<LibraryBook> authorArray = new ArrayList<LibraryBook>(); 
        
        for (int i = 0; i < books.size(); i++)
        {
            LibraryBook book = books.get(i);
            String author = book.getAuthor();
            
            if (author.contains(lastName) || author.contains(firstName))
            {
                authorArray.add(book);
                keyFound = true;
            }
        }
        
        if (keyFound == true)
        {
            System.out.println("The following books were found: ");
            for (int i = 0; i < authorArray.size(); i++)
            {
                LibraryBook book = authorArray.get(i);
                String title = book.getTitle();
                String author = book.getAuthor();
                System.out.println((i+1) + ") " + title + "/ " + author);
            }
            System.out.println("\nIf the book you wanted is not here, type \"return\" to go back to the main menu.\nOtherwise, hit enter to enter the book you want.");
            waitForResponse();
        }
        else
        {
            System.out.println("No books were found.\nHit enter to return to the main menu.   ");
            waitForResponse();
            clearScreen();
            displayMenu();
        }
        selectBook(authorArray);
    }
    //********************************************************************************************  
    //This method performs a linear search and looks for any book matching the input. It puts all of
    //the books matching the criteria into an arrayList and sends the arrayList to the method selectBook().
    public static void searchByGenre()
    {
        boolean keyFound = false;
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the book author you are searching for:   ");
        String key = input.nextLine();
        ArrayList<LibraryBook> genreArray = new ArrayList<LibraryBook>(); 
        
        for (int i = 0; i < books.size(); i++)
        {
            LibraryBook book = books.get(i);
            String genre = book.getGenre();
            
            if (genre.contains(key))
            {
                genreArray.add(book);
                keyFound = true;
            }
        }
        
        if (keyFound == true)
        {
            System.out.println("The following books were found: ");
            for (int i = 0; i < genreArray.size(); i++)
            {
                LibraryBook book = genreArray.get(i);
                String title = book.getTitle();
                String genre = book.getGenre();
                System.out.println((i+1) + ") " + title + ", " + genre);
            }
            System.out.println("\nIf the book you wanted is not here, type \"return\" to go back to the main menu.\nOtherwise, hit enter to enter the book you want.");
            waitForResponse();
        }
        else
        {
            System.out.println("No books were found.\nHit enter to return to the main menu.   ");
            waitForResponse();
            clearScreen();
            displayMenu();
        }
        selectBook(genreArray);
    }   
    //********************************************************************************************   
    //This method takes an arrayList as a parameter, and allows the user to select a book from the list.
    //If the number selected is out of bounds, the method is called again and restarted. If it is acceptable,
    //then the chosen book is sent to recordsFormat() to print the book record.
    public static void selectBook(ArrayList<LibraryBook> array)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease select a book by typing the associated number.   ");
        int chosenBook = input.nextInt();
        clearScreen();
        if (chosenBook <= array.size())
        {
            recordsFormat(array.get(chosenBook-1), chosenBook);
            System.out.print("Please hit enter to return to the main menu.   ");
            waitForResponse();
            clearScreen();
            displayMenu();
        }
        else
        {
            System.out.println("Your input is out of bounds.");
            clearScreen();
            selectBook(array);
        }
    }
    //********************************************************************************************
    //This method exits the program.
    public static void exitProgram()
    {
        System.out.print("Goodbye and have a nice day!  ");
        waitForResponse();
        System.exit(0);
    }
    
    
    //********************************************************************************************
    //Method to clear the screen
    private static void clearScreen()
    {
        System.out.println("\u001b[H\u001b[2J");
    }

}