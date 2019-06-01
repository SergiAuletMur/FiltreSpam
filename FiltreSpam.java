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
    
    public static boolean repetit(String paraula) {
    boolean conte = false;
    for(String el : vocabulari) {
        if(el.equals(paraula)) {
            conte = true;
        }
    }
    return conte;
}
    
    public static boolean estaASpam(String paraula) {
      boolean conte = false;
      for(int i = 0; i< bagOfWordsSPAM.size();i++){
          if(bagOfWordsSPAM.get(i).paraula.equals(paraula))
              conte = true;
      }
      return conte;
}
    
    public static boolean estaAHam(String paraula) {
      boolean conte = false;
      for(int i = 0; i< bagOfWordsHAM.size();i++){
          if(bagOfWordsHAM.get(i).paraula.equals(paraula))
              conte = true;
      }
      return conte;
}
    
  public static int posicioSPAM(String paraula) {
      int pos = -1;
      for(int i = 0; i< bagOfWordsSPAM.size();i++){
          if(bagOfWordsSPAM.get(i).paraula.equals(paraula))
              pos = i;
      }
      return pos;
}
    public static int posicioHAM(String paraula) {
      int pos = -1;
      for(int i = 0; i< bagOfWordsHAM.size();i++){
          if(bagOfWordsHAM.get(i).paraula.equals(paraula))
              pos = i;
      }
      return pos;
}
    
    public static ArrayList<String> vocabulari=new ArrayList<String>();
    public static ArrayList<Missatge> bagOfWordsSPAM = new ArrayList<Missatge>();
    public static ArrayList<Missatge> bagOfWordsHAM = new ArrayList<Missatge>();
    public static void main(String[] args) throws IOException {
        String target_dir = "./dirProva";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                Scanner input = new Scanner(f); 
                 while (input.hasNext()) {
                    String word  = input.next();
                    if(!repetit(word)){
                        vocabulari.add(word);
                        Missatge m = new Missatge (word);
                        bagOfWordsSPAM.add(m);
                        bagOfWordsHAM.add(m);
                    }
                    else{
                        System.out.println("word " + word + "cops " + bagOfWordsSPAM.get(posicioSPAM(word)).nCops);
                        bagOfWordsSPAM.get(posicioSPAM(word)).nCops++;
                        //bagOfWordsHAM.get(posicioHAM(word)).nCops++;
                    }
                }
            }
        }
     
        for(int i = 0; i< bagOfWordsSPAM.size();i++)
            System.out.println(bagOfWordsSPAM.get(i).paraula + " " + bagOfWordsSPAM.get(i).nCops);
     }
}
