import java.io.*;
import java.util.*;


public class LibraryCheck {
    
    static ArrayList<String> books = new ArrayList<String>();
    static int count = 0;
    
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        
        String filename;
        Scanner input = new Scanner(System.in);
        File directory = new File(".");
        
        //Receiving the file that contains the books that will populate the library
        System.out.println("Input the file that contains the library books: ");
        System.out.println("Input the file that contains the library books: ");
        filename = input.nextLine();
        
        fillLibrary(filename);
        sortLibrary();
        
    }

    
    
    public static void displayFiles()
    {
        
    }
    
    //Fills up the String Arraylist with book names from the file input.
    public static void fillLibrary(String fileName)
    {
        Scanner input = new Scanner(System.in);
 
        try {
            input = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        while (input.hasNextLine())
        {
            books.add(input.nextLine());
            count++;
        }
        
    }
    
    //Sorts the library of books by using the delimiter, and 
    //prompts the user with the number of books that were found in the file.
    public static void sortLibrary()
    {       
        
        Scanner delimiter = new Scanner(books.get(0));
        delimiter.useDelimiter(";");
        String currentBook = delimiter.next();
        
        System.out.println(currentBook);
        /*
        for (int i = 0; i < books.size(); i++)
        {       
            String nextBook = books.get(i+1);
            delimiter.useDelimiter(";");
        }
            */
            
            
        
        
        
        
    }
    
    
    
    
}