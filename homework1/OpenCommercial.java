/* OpenCommercial.java */

import java.net.*;
import java.io.*;

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
    /* Replace this comment with your solution.  */
    String webpage= new String("https://people.eecs.berkeley.edu/~jrs/61b/hw/hw1/OpenCommercial.java");
    String webpage_new= webpage.replace("X", inputLine);
    BufferedReader in=new BufferedReader(new InputStreamReader(new URL(webpage).openConnection().getInputStream()));
    String line = new String();
    int i =0;
    while(i <= 5){
        if(i==0){
            line=line.concat(in.readLine());
        }else{
            line=line.concat("\n").concat(in.readLine());
        }
        i++;
    }
    String[] ss=line.split("\n");
    int j = 4;
    while (j!=-1){
        System.out.println(ss[j]);
        j--;
    }
    System.out.println("我是l");
    System.out.println(line);
  }
}