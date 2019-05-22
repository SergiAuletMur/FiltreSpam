/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtrespam;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author Oulet
 */

public class FiltreSpam {
    public static Vector<String> bagOfWords = new Vector<String>();
    private static Integer K = 1;
    private static Integer PHI=2;
    private int numMissatges,falsePositive,falseNegative=0;
    public void main(String[] args) throws IOException {
        
        //Crear bagOfWords SPAM i HAM
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

        //Calcular conjunt de probabilitats de cada grup HAM i SPAM

        //Per cada missatge
            //Classificar-lo
            //Actualitzar comptadors falsePositive, falseNegative
        int prob=0;
        for (File f : files) {
            if(f.isFile()) {
                prob=calcularProbSpam(f);
                //Comprovar la certesa i actualitzar comptadors.
            }
        }
        
        //Calcular estadistiques i mostrar-les (Recopilar els comptadors i mostrar-los)
        mostrarEstaidistiques();
    }

    private Integer calcularProbSpam(File f) throws FileNotFoundException{
        int prob=0;
        if(f.isFile()){
            Scanner input = new Scanner(f);  
            List<String> paraulesMissatge = new ArrayList();
            //Agafem totes les paraules del missatge
            while (input.hasNext()) {  
               String paraula = input.next();
               paraulesMissatge.add(paraula);
            }
            //Metode per calcular per tot el missatge (paraulesMissatge) la prob de SPAM o HAM
            prob=probSpam(paraulesMissatge); 
        }
        return prob;
    }
    
    private Integer probSpam(List<String> missatge){
        int prob=0;
        //Crear Vocabulari (paraules diferents delmissatge) TENIR EN COMPTE ".", ",", etc en les paraules.
        
        //prob=algoritme de Bayes per totes les paraules p(SPAM/"Paraula1","Paraula2",etc)
        
        return prob;
    }
    
    private void mostrarEstaidistiques(){
        
    }
}