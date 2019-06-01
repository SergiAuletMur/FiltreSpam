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
    public static ArrayList<String> vocabulari=new ArrayList<String>();
    public static ArrayList<Missatge> bagOfWordsSPAM = new ArrayList<Missatge>();
    public static ArrayList<Missatge> bagOfWordsHAM = new ArrayList<Missatge>();
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
        mostrarEstadistiques();
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
    
    private void mostrarEstadistiques(){
        
    }
    
    public static boolean repetit(String paraula) {
        boolean conte = false;
        for(String el : vocabulari) {
            if(el.equals(paraula)) conte = true;
        }
        return conte;
    }
		
    public static boolean estaASpam(String paraula) {
        boolean conte = false;
	for(int i = 0; i< bagOfWordsSPAM.size();i++){
            if(bagOfWordsSPAM.get(i).paraula.equals(paraula)) conte = true;
	}
        return conte;
    }
		
    public static boolean estaAHam(String paraula) {
	boolean conte = false;
	for(int i = 0; i< bagOfWordsHAM.size();i++){
            if(bagOfWordsHAM.get(i).paraula.equals(paraula)) conte = true;
        }
	return conte;
    }
		
    public static int posicioSPAM(String paraula) {
	int pos = -1;
	for(int i = 0; i< bagOfWordsSPAM.size();i++){
            if(bagOfWordsSPAM.get(i).paraula.equals(paraula)) pos = i;
	}
        return pos;
    }
    
    public static int posicioHAM(String paraula) {
	int pos = -1;
	for(int i = 0; i< bagOfWordsHAM.size();i++){
            if(bagOfWordsHAM.get(i).paraula.equals(paraula)) pos = i;
	}
	return pos;
    }

}