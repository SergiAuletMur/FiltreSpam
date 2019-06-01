package filtrespam;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;

/**
 *
 * @author Oulet
 */



public class FiltreSpam {
    //----------------------------Atributs-----------------------------------------
    public static HashSet<String> vocabulari = new HashSet<String>();
    public static ArrayList<Missatge> bagOfWordsSPAM = new ArrayList<Missatge>();
    public static ArrayList<Missatge> bagOfWordsHAM = new ArrayList<Missatge>();
    //------------------------------------------------------------------------------
    
private static void calcularProbSpams (ArrayList<Missatge> listSpam){
    //Per cada paraula fer la formula amb k=1 demoment:
//p("paraula"/spam)= (aparicionsParaula+k)/paraulesSpam+NParaulesVocabulari
//P(spam)= (nSpam+k/totalMissatges+(k*2))
}

private static void calcularProbHams (ArrayList<Missatge> listHam){
//Per cada paraula fer la formula amb k=1 demoment:
//p("paraula"/ham)= (aparicionsParaula+k)/paraulesHam+NParaulesVocabulari
//P(Ham)= (nHam+k/totalMissatges+(k*2))
}
    
    private static Integer calcularProbSpam(File f) throws FileNotFoundException{
        int prob=0;
        if(f.isFile()){
            Scanner input = new Scanner(f);  
            ArrayList<String> paraulesMissatge = new ArrayList();
            //Agafem totes les paraules del missatge
            while (input.hasNext()) {  
               String paraula = input.next();
               paraula=paraula.replaceAll("[^A-Za-z0-9]+","");
               paraulesMissatge.add(paraula);
            }
            //Metode per calcular per tot el missatge (paraulesMissatge) la prob de SPAM o HAM
            prob=probSpam(paraulesMissatge); 
        }
        return prob;
    }
    
    private static Integer probSpam(ArrayList<String> missatge){
        int prob=0;
        for(int i=0; i<missatge.size(); i++){
            String paraula = missatge.get(i);
            //prob=prob+log();
        }
        //prob=algoritme de Bayes per totes les paraules p(SPAM/"Paraula1","Paraula2",etc)
        
        return prob;
    }
    
    private static void mostrarEstadistiques(){
        
    }	
    
    public static void main(String[] args) throws IOException {
        String target_dir = "./SPAM";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                Scanner input = new Scanner(f);
                Missatge m = new Missatge();
                 while (input.hasNext()) {
                     String word  = input.next();
                     word = word.replaceAll("[^A-Za-z0-9]+", "");
                     if (!word.equals("")){
                        vocabulari.add(word); // guardo totes les paraules sense repetits
                        m.afegirTotal(word);
                    }
                }
                 bagOfWordsSPAM.add(m);
            }
        }
        
        String target_dir2 = "./HAM";
        File dir2 = new File(target_dir2);
        File[] files2 = dir2.listFiles();

        for (File f : files2) {
            if(f.isFile()) {
                Scanner input = new Scanner(f);
                Missatge m = new Missatge();
                 while (input.hasNext()) {
                     String word  = input.next();
                     word = word.replaceAll("[^A-Za-z0-9]+", "");
                     if (!word.equals("")){
                        vocabulari.add(word); // guardo totes les paraules sense repetits
                        m.afegirTotal(word);
                    }
                }
                 bagOfWordsHAM.add(m);
            }
        }
        //Calcular conjunt de probabilitats de cada grup HAM i SPAM
        calcularProbSpams(bagOfWordsSPAM);
        calcularProbHams(bagOfWordsHAM);
         //Ara per cada mostra de missatges a mirar
            //Classificar-lo
            //Actualitzar comptadors falsePositive, falseNegative
        int prob=0;
        for (File f : files) { // directori TEST.
            if(f.isFile()) {
                prob=calcularProbSpam(f);
                //Comprovar la certesa i actualitzar comptadors.
            }
        }
        
        //Calcular estadistiques i mostrar-les (Recopilar els comptadors i mostrar-los)
        mostrarEstadistiques();
     }
}
