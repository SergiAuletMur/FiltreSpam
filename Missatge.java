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
 public HashMap<String, Paraula> _paraules = new HashMap<String, Paraula>();
 
 public Missatge(){
     
 }
 
    public void afegirTotal(String p){
        Paraula z = new Paraula();
        if(!_paraules.containsKey(p)){
            _paraules.put(p,z);
        }
        else{
            Paraula aux = _paraules.get(p);
            aux._nAparacions++;
            _paraules.put(p,aux);
        }
            
    }
    
    public void printMap() {
                for (Map.Entry<String, Paraula> entry : _paraules.entrySet()) {
		    System.out.println(entry.getKey()+ " = " + entry.getValue()._nAparacions);
		}
    
    }
}
