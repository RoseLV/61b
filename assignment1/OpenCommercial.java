/* OpenCommercial.java */

import java.io.*;
import java.net.*;
/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();
    String url = "https://www." + inputLine + ".com/";
    /* Replace this comment with your solution.  */
    String[] ctx = reverse(getUrlContents(url, 5));
    for (int j = 0; j < ctx.length; j++) {
      System.out.println(ctx[j]);
    }
  }

  /**
   * 
   * @param theUrl
   * @param count
   * @return [count] lines of files
   */
  public static String[] getUrlContents(String theUrl, int count)  
  {  
    // StringBuilder content = new StringBuilder(); 
    String[] arr = new String[5]; 
    // Use try and catch to avoid the exceptions  
    try  
    {  
      URL url = new URL(theUrl); // creating a url object  
      // wrapping the urlconnection in a bufferedreader  
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));  
      String line;
      int i = 0;
      // reading from the urlconnection using the bufferedreader  
      while (i < count && (line = bufferedReader.readLine()) != null)  
      {
        arr[i] = line;
        i++;
      }  
      bufferedReader.close();
    }  
    catch(Exception e)  
    {  
      e.printStackTrace();  
    }  
    return arr;  
  }

  /**
   * reverse array
   * @param array
   * @return reversed array
   */
  public static String[] reverse(String[] array) {
    // System.out.println("Array = " + Arrays.toString(array));
    int maxIndex = array.length - 1;
    int halfLength = array.length / 2;
    for (int i = 0; i < halfLength; i++) {
        String temp = array[i];
        array[i] = array[maxIndex - i];
        array[maxIndex - i] = temp;
    }
    return array;
  }
}

