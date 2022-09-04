// Importing input output classes
import java.io.*;
import java.util.Arrays;


// Main class
public class sortname {
 
    // main driver method
    public static void main(String[] args) throws Exception
    {
        // File path is passed as parameter
        File file = new File("./roster.txt");
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(file));
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        String[] names;
        while ((st = br.readLine()) != null)
            // Print the string
            // names.add(st);
            // System.out.println(st); 

        // names.sort(/*Enter the name of the Array*/);
    }
}