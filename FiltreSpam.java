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
    public static Integer K=1;
    public static Integer PHI=1;
    public Integer falsePositive, falseNegative, truePositive, trueNegative=0;
    
    //------------------------------------------------------------------------------
    
    public static void main(String[] args) throws IOException {
        String target_dir = "./SPAM";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        
        //LLEGIM SPAM
        Missatge m = new Missatge();
        for (File f : files) {
            if(f.isFile()) {
                Scanner input = new Scanner(f);
                 while (input.hasNext()) {
                     String word  = input.next();
                     word = word.replaceAll("[^A-Za-z0-9]+", "");
                     if (!word.equals("")){
                        vocabulari.add(word); // guardo totes les paraules sense repetits
                        m.afegirTotal(word);
                    }
                }
            }
        }
        bagOfWordsSPAM.add(m);
        
        String target_dir2 = "./HAM";
        File dir2 = new File(target_dir2);
        File[] files2 = dir2.listFiles();

        //LLEGIM HAM
        Missatge m2 = new Missatge();
        for (File f : files2) {
            if(f.isFile()) {
                Scanner input = new Scanner(f);
                 while (input.hasNext()) {
                    String word  = input.next();
                    word = word.replaceAll("[^A-Za-z0-9]+", "");
                    if (!word.equals("")){
                        vocabulari.add(word); // guardo totes les paraules sense repetits
                        m2.afegirTotal(word);
                    }
                }
            }
        }
        bagOfWordsHAM.add(m2);
        
        //Calcular conjunt de probabilitats de cada grup HAM i SPAM
        double pSpam = calcProbSpams();
        double pHam = calcProbHams();
        
        String target_dir3 = "./testSpam";
        File dir3 = new File(target_dir3);
        File[] files3 = dir3.listFiles();
        
        //TESTAGEM SPAM
        double probSpamMissatge=0;
        //Testagem fitcher Spam
        for (File f : files3) { // directori TEST Spam. Mirem tots els fitxers a testejar.
            if(f.isFile()) {
                probSpamMissatge=calcularProbSpam(f);
                //Comprovar la certesa i actualitzar comptadors. (Metode del PHI amb les probabilitats de spam/missatge i ham/missatge)
                //metodeActualitzar comptadors (falsePositiu,falseNegatiu,etc)
            }
        }
        
        String target_dir4 = "./testHam";
        File dir4 = new File(target_dir4);
        File[] files4 = dir4.listFiles();
        
        //TESTAGEM HAM
        double probHamMissatge=0;
        //Testagem fitcher Ham
        for (File f : files3) { // directori TEST Spam. Mirem tots els fitxers a testejar.
            if(f.isFile()) {
                probHamMissatge=calcularProbHam(f);
                //Comprovar la certesa i actualitzar comptadors.(Metode del PHI amb les probabilitats de spam/missatge i ham/missatge)
                //metodeActualitzar comptadors (falsePositiu,falseNegatiu,etc)
            }
        }
        
        //Calcular estadistiques i mostrar-les (Recopilar els comptadors i mostrar-los)
        mostrarEstadistiques();
     }
    private static double calcProbSpams(){//p(SPAM) Respecte les mostres i la K que tenim.
        
        int nMissSpam = 7999; //7999 ja que en aquest cas analitzem sobre els 7999 primers missatges.
        int nMissHam = 7999; //7999 ja que en aquest cas analitzem sobre els 7999 primers missatges.
        
        double pbSpam = ((nMissSpam+K)/(nMissSpam+nMissHam+K*2));
        //p(spam)= (nMissatgesSpam+k/totalMissatges+(k*2))
        
        return pbSpam;
    }

    private static double calcProbHams(){//p(HAM) Respecte les mostres i la K que tenim.
        
        int nMissSpam = 7999; //7999 ja que en aquest cas analitzem sobre els 7999 primers missatges.
        int nMissHam = 7999; //7999 ja que en aquest cas analitzem sobre els 7999 primers missatges.
        
        double pbHam = ((nMissHam+K)/(nMissHam+nMissSpam+K*2));
        //p(ham)= (nMissatgesHam+k/totalMissatges+(k*2))
        
        return pbHam;
    }
    
    private static Double calcularProbSpam(File f) throws FileNotFoundException{
        double prob=0;
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
    
    private static Double calcularProbHam(File f) throws FileNotFoundException{
        double prob=0;
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
            prob=probHam(paraulesMissatge); 
        }
        return prob;
    }
    
    private static Double probSpam(ArrayList<String> missatge){
        double prob=0;//prob Spam del missatge enter
        Missatge aux=bagOfWordsSPAM.get(0);
        for(int i=0; i<missatge.size(); i++){
            //Per cada paraula del missatge fer la formula amb k=1 demoment:
            //p("paraula"/spam)= (aparicionsParaula+k)/paraulesSpam+NParaulesVocabulari
            
            String paraula = missatge.get(i);
            int nAparicions = -1;
            //si la paraula existeix nAparicions!=-1
            if(aux._paraules.get(paraula)!=null){
                nAparicions=aux._paraules.get(paraula);
            }
            int nParaulesSpam = aux._paraules.size();
            
            if(nAparicions!=-1){//si em trobat la paraula fem calcul, sino la ignorem.
                double result = ((nAparicions+K)/(nParaulesSpam+vocabulari.size()));
                prob=prob + Math.log(result);//Sumem, el logaritme de la probabilitat d'aquesta paraula, a la resta.
            }
        }
        //prob=algoritme de Bayes per totes les paraules p(SPAM/"Paraula1","Paraula2",etc)
        return prob;
    }
    
    private static Double probHam(ArrayList<String> missatge){
        double prob=0;//prob Spam del missatge enter
        Missatge aux=bagOfWordsHAM.get(0);
        for(int i=0; i<missatge.size(); i++){
            //Per cada paraula del missatge fer la formula amb k=1 demoment:
            //p("paraula"/ham)= (aparicionsParaula+k)/paraulesHam+NParaulesVocabulari
            
            String paraula = missatge.get(i);
            int nAparicions = -1;
            //si la paraula existeix nAparicions!=-1
            if(aux._paraules.get(paraula)!=null){
                nAparicions=aux._paraules.get(paraula);
            }
            int nParaulesHam = aux._paraules.size();
            
            if(nAparicions!=-1){//si em trobat la paraula fem calcul, sino la ignorem.
                double result = ((nAparicions+K)/(nParaulesHam+vocabulari.size()));
                prob=prob + Math.log(result);//Sumem, el logaritme de la probabilitat d'aquesta paraula, a la resta.
            }
        }
        //prob=algoritme de Bayes per totes les paraules p(SPAM/"Paraula1","Paraula2",etc)
        return prob;
    }
    
    private static void mostrarEstadistiques(){
        
    }	
}