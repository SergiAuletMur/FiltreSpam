/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtrespam;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Oulet
 */
public class Missatge {
    //Conte el map amb les paraules de HAM/SPAM i les aparicions d'aquestes en aquest grup.
    public HashMap<String, Integer> _paraules = new HashMap<String, Integer>();
 
    public Missatge(){
    }
 
    public void afegirTotal(String p){
        if(!_paraules.containsKey(p)){
            _paraules.put(p,1);
        }
        else{
            int aux = _paraules.get(p);
            aux=aux+1;
            _paraules.put(p,aux);
        } 
    }
    
    public void printMap() {
        for (Map.Entry<String, Integer> entry : _paraules.entrySet()) {
            System.out.println(entry.getKey()+ " = " + entry.getValue());
        }
    }
}