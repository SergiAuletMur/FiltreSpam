package filtrespam;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author Oulet
 */

public class FiltreSpam {
    public static Vector<String> bagOfWords = new Vector<String>();

    public static void main(String[] args) throws IOException {
        String target_dir = "./dirProva";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                BufferedReader inputStream = null;
                
                Scanner input=new Scanner(f);
                input.useDelimiter(" +"); //delimitor is one or more spaces
                while(input.hasNext()){
                    bagOfWords.addElement(input.next());
                    //System.out.println(input.next());
                }
            }
        }
    
    // Test bagOfWords que guardi be.
    Enumeration en = bagOfWords.elements();
    System.out.println("\nElements are:");
    while(en.hasMoreElements())
         System.out.print(en.nextElement() + " ");
    }

}
